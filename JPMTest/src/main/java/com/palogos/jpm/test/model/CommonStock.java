package com.palogos.jpm.test.model;


public class CommonStock extends Stock {

	@Override
	public Double getDividendTield() {
		return this.getLastDivident() / this.getPrice();
	}

	@Override
	public Double getPERatio() {
		return this.getPrice() / this.getLastDivident();
	}

}
