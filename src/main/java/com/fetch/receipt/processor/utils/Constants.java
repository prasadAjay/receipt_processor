package com.fetch.receipt.processor.utils;

public class Constants {

	public final static String RECEIPT_NOT_FOUND = "Receipt not found for id: %s";
	
	public final static String STRING_REGEX = "^\\S+$";
	
	public final static String PRICE_REGEX = "^\\d+\\.\\d{2}$";
	
	public final static String ITEM_SHORT_DESCRIPTION = "^[\\w\\s\\-]+$";
	
	public final static String ROUND_DOLLAR_REGEX = "^\\d+\\.00$";
	
	public final static String SHORT_DESC_ERROR = "Item Short Description is not proper";
	
	public final static String PRICE_ERROR = "Price is not proper";
}
