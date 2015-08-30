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
	@NotNull
	@Digits(fraction = 3, integer = 11)
	@DecimalMin(value = "0.001")
	private BigDecimal price;
	@NotNull
	private Offer offer;
	@NotNull
	private Demand demand;

	public Trade(Offer offer, Demand demand) {
		this.stock = demand.getStock();
		this.timestamp = new Date();
		this.quantity = demand.getQuantity();
		this.price = demand.getPrice();
		this.offer = offer;
		this.demand = demand;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade [stock=");
		builder.append(stock);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", price=");
		builder.append(price);
		builder.append(", offer=");
		builder.append(offer);
		builder.append(", demand=");
		builder.append(demand);
		builder.append("]\r\n");
		return builder.toString();
	}

}
