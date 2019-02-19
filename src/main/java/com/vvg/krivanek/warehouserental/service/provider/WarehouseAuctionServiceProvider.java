package com.vvg.krivanek.warehouserental.service.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseAuctionDaoService;
import com.vvg.krivanek.warehouserental.service.WarehouseAuctionService;

@Service
public class WarehouseAuctionServiceProvider implements WarehouseAuctionService{
	
	@Autowired
	WarehouseAuctionDaoService warehouseAuctionDaoService;
	
	@Override
	@Scheduled(cron = "0 0 23 * * ?")
	public void warehouseAuctionScheduler() {
		//TODO
	}
	
	
}
