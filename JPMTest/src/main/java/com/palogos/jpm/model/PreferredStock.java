package com.palogos.jpm.model;


public class PreferredStock extends Stock {

	public PreferredStock(String symbol, String type, Double lastDividend,
			double fixedDividend, double parValue) {
		this.setSymbol(symbol);
		this.setType(type);
		this.setLastDivident(lastDividend);
		this.setFixedDivident(fixedDividend);
		this.setParValue(parValue);
	}

	@Override
	public Double getDividendYield() {
		return this.getPrice() > 0 ? this.getFixedDivident()
				* this.getParValue() / this.getPrice() : 0;
	}

	@Override
	public Double getPERatio() {
		return this.getLastDivident() > 0 ? this.getPrice()
				/ this.getLastDivident() : 0;
	}

}
