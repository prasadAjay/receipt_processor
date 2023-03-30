package com.fetch.receipt.processor.controller;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fetch.receipt.processor.model.Item;
import com.fetch.receipt.processor.model.Receipt;
import com.fetch.receipt.processor.service.ReceiptService;
import com.fetch.receipt.processor.utils.Constants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

	@Autowired
	ReceiptService receiptService;

	@GetMapping("/{id}/points")
	public ResponseEntity<String> getPoints(@PathVariable String id) {
		Receipt receiptObj = receiptService.findReceiptById(id);
		if (receiptObj == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(Constants.RECEIPT_NOT_FOUND, id));
		}
		int points = calculatePoints(receiptObj);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.valueOf(points));
	}

	@PostMapping("/process")
	public ResponseEntity<Map<String, String>> submitReceipt(@Valid @RequestBody Receipt receipt) {
		Map<String, String> response = new HashMap<>();
		try {
			Receipt receiptObj = receiptService.saveReceipt(receipt);
			String receiptId = receiptObj.getReceiptId();
			response.put("receiptId", receiptId);
			return ResponseEntity.ok(response);
		} catch (TransactionSystemException ex) {
			response.put("message", ex.getOriginalException().getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
	}

	private int calculatePoints(Receipt receipt) {
		int totalPoints = 0;

		// One point for every alphanumeric character in the retailer name
		String retailerName = receipt.getRetailer();
		String alphanumeric = retailerName.replaceAll(" ", "");
		int alphanumericCount = 0;
		for(int i=0; i<alphanumeric.length(); i++) {
			Character character = alphanumeric.charAt(i);
			if ((character >= '0' & character <= '9') || (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z')) {
				alphanumericCount++;
			}
		}
		totalPoints += alphanumericCount;

		// 50 points if the total is a round dollar amount with no cents
		String roundDollarRegexExp = Constants.ROUND_DOLLAR_REGEX;
		if(receipt.getTotal().matches(roundDollarRegexExp)) {
			totalPoints += 50;
		}

		// 25 points if the total is a multiple of 0.25
		if (Double.parseDouble(receipt.getTotal()) % 0.25 == 0) {
			totalPoints += 25;
		}

		// 5 points for every two items on the receipt
		int itemCount = receipt.getItems().size();
		int itemPoints = (itemCount / 2) * 5;
		totalPoints += itemPoints;

		// If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
		for(Item item : receipt.getItems()) {
			if (item.getShortDescription().strip().length() % 3 == 0) {
				totalPoints += Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
			}
		}

		// 6 points if the day in the purchase date is odd
		int dateLength = receipt.getPurchaseDate().length();
		String day = receipt.getPurchaseDate().substring(dateLength-2, dateLength);
		if (Integer.parseInt(day) % 2 != 0) {
			totalPoints += 6;
		}

		// 10 points if the time of purchase is after 2:00pm and before 4:00pm
		LocalTime purchaseTime = LocalTime.parse(receipt.getPurchaseTime());
		LocalTime start = LocalTime.of(14, 0);
		LocalTime end = LocalTime.of(16, 0);
		if (purchaseTime.isAfter(start) && purchaseTime.isBefore(end)) {
			totalPoints += 10;
		}

		return totalPoints;
	}
}
