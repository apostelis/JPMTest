package com.palogos.jpm.service.impl;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.palogos.jpm.exceptions.InvalidTradeException;
import com.palogos.jpm.model.Demand;
import com.palogos.jpm.model.Instruction;
import com.palogos.jpm.model.Offer;
import com.palogos.jpm.model.Trade;
import com.palogos.jpm.service.InstructionsMatcher;
import com.palogos.jpm.service.TradeService;

@Component
public class InstructionsMatcherImpl implements InstructionsMatcher {

	private static Logger logger = Logger
			.getLogger(InstructionsMatcherImpl.class);

	@Autowired
	private TradeService tradeService;

	@Autowired
	private LinkedHashMap<UUID, Instruction> offerTable;

	@Autowired
	private LinkedHashMap<UUID, Instruction> demandTable;

	@Override
	@Scheduled(fixedDelay = 5000)
	public void tradeSearch() {
		logger.trace("tradeSearch invoked");
		// int count = tradeService.matchAndGenerateTrades();
		// logger.info("Trades generated: " + count);
		Set<UUID> demandTableKeySet = demandTable.keySet();
		for (UUID demandUuid : demandTableKeySet) {
			Demand demand = (Demand) demandTable.get(demandUuid);
			Set<UUID> offerTableKeySet = offerTable.keySet();
			for (UUID offerUuid : offerTableKeySet) {
				Offer offer = (Offer) offerTable.get(offerUuid);
				boolean symbolMatches = demand.getStock().getSymbol()
						.equalsIgnoreCase(offer.getStock().getSymbol());
				// Matching strategy must be introduced as one-to-one matching
				// will not be very useful in real life
				if (symbolMatches) {
					boolean quantityAndPriceMatches = demand.getQuantity()
							.compareTo(offer.getQuantity()) == 0
							&& demand.getPrice().compareTo(offer.getPrice()) == 0;
					if (quantityAndPriceMatches) {
						Trade newTrade = new Trade(offer, demand);
						try {
							tradeService.recordTrade(newTrade);
						} catch (InvalidTradeException e) {
							logger.error("Something went terribly wrong with the creation of the trade. Offer: "
									+ offer.getUuid()
									+ " Demand: "
									+ demand.getUuid());
						}
						demandTable.remove(demandUuid);
						offerTable.remove(offerUuid);
					}
				}
			}
		}

	}

	@Override
	public void matchDemand(UUID uuid) {
		// TODO Auto-generated method stub

	}
}
