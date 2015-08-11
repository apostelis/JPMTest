package com.palogos.jpm.test.model;

public class PreferredStock extends Stock {

	@Override
	public Double getDividendTield() {
		return this.getFixedDivident() * this.getParValue() / this.getPrice();
	}

	@Override
	public Double getPERatio() {
		return this.getPrice() / this.getLastDivident();
	}

}
