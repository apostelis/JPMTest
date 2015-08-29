package com.palogos.jpm.model.listeners;

import java.util.LinkedHashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.palogos.jpm.model.Demand;
import com.palogos.jpm.model.Instruction;
import com.palogos.jpm.model.events.DemandPlacedCustomEvent;

@Component
public class DemandCustomEventListener implements
		ApplicationListener<DemandPlacedCustomEvent> {

	@Autowired
	private LinkedHashMap<UUID, Instruction> demandTable;

	@Override
	public void onApplicationEvent(DemandPlacedCustomEvent event) {
		// TODO Record Event
		UUID uuid = event.getDemand().getUuid();
		Demand demand = event.getDemand();
		demandTable.put(uuid, demand);
		// TODO Initiate matching
		// TODO Initiate periodic matching tries
	}

}
