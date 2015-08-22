package com.palogos.jpm.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public abstract class Stock {
	@NotNull
	@Size(min = 3, max = 3)
	private String symbol;
	@NotNull
	@Digits(fraction = 3, integer = 11)
	@DecimalMin(value = "0.001")
	private Double parValue;
	// TODO this may not be needed
	@NotNull
	@Pattern(regexp = "^Common|Preferred$")
	private String type;
	@NotNull
	@Digits(fraction = 3, integer = 11)
	@DecimalMin(value = "0.001")
	private Double lastDividend;
	@Digits(fraction = 3, integer = 11)
	@DecimalMin(value = "0.001")
	private Double fixedDividend;
	@NotNull
	@Digits(fraction = 3, integer = 11)
	@DecimalMin(value = "0D")
	private Double price = 0D;

	public abstract Double getDividendYield();

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
		return lastDividend;
	}

	public void setLastDivident(Double lastDivident) {
		this.lastDividend = lastDivident;
	}

	public Double getFixedDivident() {
		return fixedDividend;
	}

	public void setFixedDivident(Double fixedDivident) {
		this.fixedDividend = fixedDivident;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [symbol=");
		builder.append(symbol);
		builder.append(", parValue=");
		builder.append(parValue);
		builder.append(", type=");
		builder.append(type);
		builder.append(", lastDividend=");
		builder.append(lastDividend);
		builder.append(", fixedDividend=");
		builder.append(fixedDividend);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

}
