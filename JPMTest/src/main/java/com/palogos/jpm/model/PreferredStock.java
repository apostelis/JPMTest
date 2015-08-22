package com.palogos.jpm.model;

public class PreferredStock extends Stock {

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
