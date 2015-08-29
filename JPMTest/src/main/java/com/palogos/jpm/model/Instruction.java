package com.palogos.jpm.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Instruction {
	@NotNull
	private UUID uuid = UUID.randomUUID();
	@NotNull
	private Stock stock;
	@NotNull
	private Date captureDateTimeStamp;
	@NotNull
	@Min(value = 1)
	private Integer quantity;
	@NotNull
	@Digits(fraction = 3, integer = 11)
	@DecimalMin(value = "0.001")
	private BigDecimal price;
	@NotNull
	private String beneficiary;

	@NotNull
	public Instruction() {
		super();
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getCaptureDateTimeStamp() {
		return captureDateTimeStamp;
	}

	public void setCaptureDateTimeStamp(Date dateTimeStamp) {
		this.captureDateTimeStamp = dateTimeStamp;
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

	public UUID getUuid() {
		return uuid;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

}
