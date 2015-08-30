package com.palogos.jpm.model;

import java.util.Date;

public class Demand extends Instruction {

	public Demand() {
		super();
		this.setCaptureDateTimeStamp(new Date());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Demand [Instruction=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
