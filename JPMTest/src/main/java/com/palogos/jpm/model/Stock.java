package com.palogos.jpm.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public abstract class Stock {
	@NotNull
	@Size(min = 3, max = 3)
	private String symbol;
	@NotNull
	private Double parValue;
	// TODO this may not be needed
	@NotNull
	@Pattern(regexp = "^Common|Preferred$")
	private String type;
	@NotNull
	private Double lastDivident;
	private Double fixedDivident;
	@NotNull
	private Double price;

	public abstract Double getDividendTield();

	public abstract Double getPERatio();

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getLastDivident() {
		return lastDivident;
	}

	public void setLastDivident(Double lastDivident) {
		this.lastDivident = lastDivident;
	}

	public Double getFixedDivident() {
		return fixedDivident;
	}

	public void setFixedDivident(Double fixedDivident) {
		this.fixedDivident = fixedDivident;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
