package com.palogos.jpm.model.listeners;

import java.util.LinkedHashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.palogos.jpm.model.Instruction;
import com.palogos.jpm.model.Offer;
import com.palogos.jpm.model.events.OfferPlacedCustomEvent;

@Component
public class OfferCustomEventListener implements
		ApplicationListener<OfferPlacedCustomEvent> {

	@Autowired
	private LinkedHashMap<UUID, Instruction> offerTable;

	@Override
	public void onApplicationEvent(OfferPlacedCustomEvent event) {
		UUID uuid = event.getOffer().getUuid();
		Offer offer = event.getOffer();
		offerTable.put(uuid, offer);
	}

}
