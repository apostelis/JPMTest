package com.palogos.jpm.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Trade {
	@NotNull
	private Stock stock;
	@NotNull
	private Date timestamp;
	@NotNull
	@Min(value = 1)
	private Integer quantity;
	private boolean buy;
	@NotNull
	@Digits(fraction = 3, integer = 11)
	@DecimalMin(value = "0.001")
	private BigDecimal price;

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public boolean isBuy() {
		return buy;
	}

	public void setBuy(boolean buy) {
		this.buy = buy;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
