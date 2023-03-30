package com.fetch.receipt.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fetch.receipt.processor.model.Receipt;
import com.fetch.receipt.processor.repository.ReceiptRepository;

@Service
public class ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;
	
	public Receipt saveReceipt(Receipt receipt) {
		return receiptRepository.save(receipt);
	}
	
	public Receipt findReceiptById(String id) {
		return receiptRepository.findById(id).orElse(null);
	}
}
