package com.palogos.jpm.model;

import java.util.Date;

public class Offer extends Instruction {

	public Offer() {
		super();
		this.setCaptureDateTimeStamp(new Date());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Offer [Instruction=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
