package com.fetch.receipt.processor.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receipt")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "receiptId")
	private String receiptId;
	
	@Column(name = "retailer", updatable = true)
	private String retailer;
	
	@Column(name = "purchaseDate", updatable = true)
	private String purchaseDate;
	
	@Column(name = "purchaseTime", updatable = true)
	private String purchaseTime;
	
	@OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("receipt")
	private List<Item> items = new ArrayList<>();
	
	@Column(name = "total", updatable = true)
	private String total;

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}
