package com.vvg.krivanek.warehouserental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.WarehouseDao;
import com.vvg.krivanek.warehouserental.domain.Warehouse;

@Service
public class WarehouseService {
	
	@Autowired
	WarehouseDao warehouseDao;
	
	@Scheduled(cron = "0 0 23 * * ?")
	public void warehouseScheduler() {
		//TODO
	}
	
	public List<Warehouse> getWarehouses() {
		return warehouseDao.getWarehouses();
	}
	
}
