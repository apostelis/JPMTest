package com.palogos.jpm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.palogos.jpm.model.CommonStock;
import com.palogos.jpm.model.Stock;
import com.palogos.jpm.model.Trade;
import com.palogos.jpm.service.StockService;

@Configuration
@ComponentScan(basePackages = { "com.palogos.jpm" })
@EnableAutoConfiguration
public class JPMTestApp implements CommandLineRunner {

	@Autowired
	private StockService stockService;

	@Override
	public void run(String... args) throws Exception {
		stockService.printIndex("GBCE");
	}

	@Bean
	public ConcurrentHashMap<String, Stock> GBCE() {
		ConcurrentHashMap<String, Stock> gbce = new ConcurrentHashMap<String, Stock>(
				5);
		gbce.put("TEA", new CommonStock("TEA", "Common", 0D, null, 100D));
		gbce.put("POP", new CommonStock("POP", "Common", 8D, null, 100D));
		gbce.put("ALE", new CommonStock("ALE", "Common", 23D, null, 60D));
		gbce.put("GIN", new CommonStock("GIN", "Preferred", null, 2D, 100D));
		gbce.put("JOE", new CommonStock("JOE", "Common", 13D, null, 250D));
		return gbce;
	}

	@Bean
	public ConcurrentLinkedQueue<Trade> tradeQueue() {
		return new ConcurrentLinkedQueue<Trade>();
	}

	public static void main(String[] args) {
		SpringApplication.run(JPMTestApp.class, args);
	}

}
