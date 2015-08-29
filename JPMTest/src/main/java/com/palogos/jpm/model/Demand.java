package com.palogos.jpm.model;

import java.util.Date;

public class Demand extends Instruction {

	public Demand() {
		super();
		this.setCaptureDateTimeStamp(new Date());
	}

}
