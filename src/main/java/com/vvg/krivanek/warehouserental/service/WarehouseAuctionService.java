package com.vvg.krivanek.warehouserental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.WarehouseAuctionDao;

@Service
public class WarehouseAuctionService {
	
	@Autowired
	WarehouseAuctionDao warehouseAuctionDao;
	
	@Scheduled(cron = "0 0 23 * * ?")
	public void warehouseAuctionScheduler() {
		//TODO
	}
	
	
}
