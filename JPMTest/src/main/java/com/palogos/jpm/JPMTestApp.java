package com.palogos.jpm;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.palogos.jpm.model.CommonStock;
import com.palogos.jpm.model.Demand;
import com.palogos.jpm.model.Instruction;
import com.palogos.jpm.model.Offer;
import com.palogos.jpm.model.PreferredStock;
import com.palogos.jpm.model.Stock;
import com.palogos.jpm.model.Trade;
import com.palogos.jpm.service.StockService;
import com.palogos.jpm.service.TradeService;

@Configuration
@ComponentScan(basePackages = { "com.palogos.jpm" })
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
public class JPMTestApp implements CommandLineRunner, AsyncConfigurer {

	@Autowired
	private StockService stockService;

	@Autowired
	private TradeService tradeService;

	@Override
	public void run(String... args) throws Exception {
		stockService.printIndex("gbce");
		// tradeService.placeOffer(new Offer());
		tradeService.placeDemand(tradeService.createDemand("Apostolos",
				stockService.getStockBySymbol("TEA.gbce"), new BigDecimal(
						100.01), 100));

		tradeService.placeDemand(tradeService.createDemand("Apostolos",
				stockService.getStockBySymbol("POP.gbce"), new BigDecimal(
						100.01), 1000));

		tradeService.placeOffer(tradeService.createOffer("Apostolos",
				stockService.getStockBySymbol("POP.gbce"), new BigDecimal(
						100.01), 1000));
	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
		threadPoolExecutor.setCorePoolSize(15);
		threadPoolExecutor.initialize();
		ConcurrentTaskExecutor taskExecutor = new ConcurrentTaskExecutor(
				threadPoolExecutor);
		return taskExecutor;
	}

	@Bean
	public ConcurrentHashMap<String, Stock> gbce() {
		ConcurrentHashMap<String, Stock> gbce = new ConcurrentHashMap<String, Stock>(
				5);
		gbce.put("TEA", new CommonStock("TEA", "Common", 0D, null, 100D));
		gbce.put("POP", new CommonStock("POP", "Common", 8D, null, 100D));
		gbce.put("ALE", new CommonStock("ALE", "Common", 23D, null, 60D));
		gbce.put("GIN", new PreferredStock("GIN", "Preferred", null, 2D, 100D));
		gbce.put("JOE", new CommonStock("JOE", "Common", 13D, null, 250D));
		return gbce;
	}

	@Bean
	LinkedHashMap<UUID, Instruction> offerTable() {
		return new LinkedHashMap<UUID, Instruction>(5);
	}

	@Bean
	LinkedHashMap<UUID, Instruction> demandTable() {
		return new LinkedHashMap<UUID, Instruction>(5);
	}

	@Bean
	@Scope("prototype")
	public Demand newDemand() {
		return new Demand();
	}

	@Bean
	@Scope("prototype")
	public Offer newOffer() {
		return new Offer();
	}

	@Bean
	public ConcurrentLinkedQueue<Trade> tradeQueue() {
		return new ConcurrentLinkedQueue<Trade>();
	}

	public static void main(String[] args) {
		SpringApplication.run(JPMTestApp.class, args);
	}

}
