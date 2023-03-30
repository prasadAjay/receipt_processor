package com.fetch.receipt.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fetch.receipt.processor.controller.ReceiptController;
import com.fetch.receipt.processor.model.Item;
import com.fetch.receipt.processor.model.Receipt;
import com.fetch.receipt.processor.service.ReceiptService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReceiptControllerTest {

    @Mock
    private ReceiptService receiptService;

    @InjectMocks
    private ReceiptController receiptController;

    @Test
    public void testGetPoints() {
        String receiptId = "3699e0e0-d0d6-4d14-a57a-01116be02e1e";
        Receipt receipt = new Receipt();
        receipt.setRetailer("M&M Corner Market");
        receipt.setTotal("9.00");
        receipt.setPurchaseDate("2022-03-20");
        receipt.setPurchaseTime("14:33");
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setShortDescription("Gatorade");
        item1.setPrice("2.25");
        Item item2 = new Item();
        item2.setShortDescription("Gatorade");
        item2.setPrice("2.25");
        Item item3 = new Item();
        item3.setShortDescription("Gatorade");
        item3.setPrice("2.25");
        Item item4 = new Item();
        item4.setShortDescription("Gatorade");
        item4.setPrice("2.25");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        receipt.setItems(items);
        Mockito.when(receiptService.findReceiptById(receiptId)).thenReturn(receipt);

        // Test
        ResponseEntity<String> responseEntity = receiptController.getPoints(receiptId);

        // Verify
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        Assert.assertEquals("109", responseEntity.getBody());
    }
    
    @Test
    public void testSubmitReceipt() {
    	Receipt receipt = new Receipt();
        receipt.setRetailer("M&M Corner Market");
        receipt.setTotal("9.00");
        receipt.setPurchaseDate("2022-03-20");
        receipt.setPurchaseTime("14:33");
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setShortDescription("Gatorade");
        item1.setPrice("2.25");
        Item item2 = new Item();
        item2.setShortDescription("Gatorade");
        item2.setPrice("2.25");
        Item item3 = new Item();
        item3.setShortDescription("Gatorade");
        item3.setPrice("2.25");
        Item item4 = new Item();
        item4.setShortDescription("Gatorade");
        item4.setPrice("2.25");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        receipt.setItems(items);
        Receipt savedReceipt = new Receipt();
        savedReceipt.setReceiptId("3699e0e0-d0d6-4d14-a57a-01116be02e1e");
        Mockito.when(receiptService.saveReceipt(receipt)).thenReturn(savedReceipt);

        // Test
        ResponseEntity<Map<String, String>> responseEntity = receiptController.submitReceipt(receipt);

        // Verify
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals("3699e0e0-d0d6-4d14-a57a-01116be02e1e", responseEntity.getBody().get("receiptId"));
    }
}

