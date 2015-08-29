package com.palogos.jpm.model.events;

import org.springframework.context.ApplicationEvent;

import com.palogos.jpm.model.Offer;

public class OfferPlacedCustomEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5303373848519094415L;

	private Offer offer;

	public OfferPlacedCustomEvent(Offer offer) {
		super(offer);
		this.offer = offer;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		if (offer == null) {
			this.offer = offer;
		} else {
			throw new IllegalArgumentException(
					"Offer cannot be set on an event that already has a offer.");
		}
	}

}
