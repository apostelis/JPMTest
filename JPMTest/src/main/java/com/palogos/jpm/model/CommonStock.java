package com.palogos.jpm.model;

public class CommonStock extends Stock {

	public CommonStock(String symbol, String type, Double lastDividend,
			Double fixedDividend, Double parValue) {
		this.setSymbol(symbol);
		this.setType(type);
		this.setLastDivident(lastDividend);
		this.setFixedDivident(fixedDividend);
		this.setParValue(parValue);
	}

	@Override
	public Double getDividendYield() {
		return this.getPrice() > 0 ? this.getLastDivident() / this.getPrice()
				: 0;
	}

	@Override
	public Double getPERatio() {
		return this.getLastDivident() != null && this.getLastDivident() > 0 ? this
				.getPrice() / this.getLastDivident()
				: 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonStock [toString()=");
		builder.append(super.toString());
		builder.append(", getDividendTield()=");
		builder.append(getDividendYield());
		builder.append(", getPERatio()=");
		builder.append(getPERatio());
		builder.append("]");
		return builder.toString();
	}

}
