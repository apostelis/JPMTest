package com.palogos.jpm.model.events;

import org.springframework.context.ApplicationEvent;

import com.palogos.jpm.model.Demand;

public class DemandPlacedCustomEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 678388896624330392L;

	private Demand demand;

	public DemandPlacedCustomEvent(Demand demand) {
		super(demand);
		this.demand = demand;
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		if (demand == null) {
			this.demand = demand;
		} else {
			throw new IllegalArgumentException(
					"Demand cannot be set on an event that already has a demand.");
		}
	}

}
