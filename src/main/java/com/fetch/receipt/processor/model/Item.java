package com.fetch.receipt.processor.model;

import com.fetch.receipt.processor.utils.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "itemId")
	private String itemId;
	
	@Column(name = "shortDescription", updatable = true)
	@Pattern(regexp = Constants.ITEM_SHORT_DESCRIPTION, message = Constants.SHORT_DESC_ERROR)
	private String shortDescription;
	
	@Column(name = "price", updatable = true)
	@Pattern(regexp = Constants.PRICE_REGEX, message = Constants.PRICE_ERROR)
	private String price;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
