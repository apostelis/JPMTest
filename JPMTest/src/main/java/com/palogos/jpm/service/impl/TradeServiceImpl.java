package com.palogos.jpm.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.palogos.jpm.exceptions.InvalidIndexException;
import com.palogos.jpm.exceptions.InvalidTradeException;
import com.palogos.jpm.model.Demand;
import com.palogos.jpm.model.Instruction;
import com.palogos.jpm.model.Offer;
import com.palogos.jpm.model.Stock;
import com.palogos.jpm.model.Trade;
import com.palogos.jpm.model.events.DemandPlacedCustomEvent;
import com.palogos.jpm.model.events.OfferPlacedCustomEvent;
import com.palogos.jpm.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService,
		ApplicationEventPublisherAware {

	private static Logger logger = Logger.getLogger(TradeServiceImpl.class);

	// @Autowired
	// private ConcurrentHashMap<String, Stock> gbce;
	// Autowired the context in order to be able to have more than one indexes
	@Autowired
	private AnnotationConfigApplicationContext annotationConfigApplicationContext;

	@Autowired
	private LinkedHashMap<UUID, Instruction> offerTable;

	@Autowired
	private LinkedHashMap<UUID, Instruction> demandTable;

	// @Autowired
	private ApplicationEventPublisher publisher;

	@Override
	@Async
	public int matchAndGenerateTrades(String index)
			throws InvalidIndexException {
		ConcurrentHashMap<String, Stock> gbce = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
				.getBean(index);
		if (gbce != null) {

		} else {
			throw new InvalidIndexException("FATAL: Index is not available");
		}
		return 0;

	}

	@Override
	public boolean recordTrade(Trade trade) throws InvalidTradeException {

		return false;
	}

	@Override
	public void placeOffer(Offer offer) {
		// offerTable.put(offer.getUuid(), offer);
		publisher.publishEvent(new OfferPlacedCustomEvent(offer));
	}

	@Override
	public void placeDemand(Demand demand) {
		// demandTable.put(demand.getUuid(), demand);
		publisher.publishEvent(new DemandPlacedCustomEvent(demand));
	}

	@Override
	public Offer createOffer(String beneficiary, Stock stock, BigDecimal price,
			Integer quantity) {
		Offer newOffer = annotationConfigApplicationContext.getBean("newOffer",
				Offer.class);
		newOffer.setBeneficiary(beneficiary);
		newOffer.setCaptureDateTimeStamp(new Date());
		newOffer.setStock(stock);
		newOffer.setPrice(price);
		newOffer.setQuantity(quantity);

		return newOffer;
	}

	@Override
	public Demand createDemand(String beneficiary, Stock stock,
			BigDecimal price, Integer quantity) {
		Demand newDemand = annotationConfigApplicationContext.getBean(
				"newDemand", Demand.class);
		newDemand.setBeneficiary(beneficiary);
		newDemand.setStock(stock);
		newDemand.setPrice(price);
		newDemand.setQuantity(quantity);

		return newDemand;
	}

	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;

	}

}
