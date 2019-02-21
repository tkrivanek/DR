package com.vvg.krivanek.warehouserental.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.service.UserWarehouseDaoService;
import com.vvg.krivanek.warehouserental.dao.service.WarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.service.WarehouseService;

@Service
public class WarehouseServiceProvider implements WarehouseService {

	@Autowired
	WarehouseDaoService warehouseDaoService;
	@Autowired
	UserWarehouseDaoService userWarehouseDaoService;

	@Override
	@Scheduled(cron = "0 0 23 * * ?")
	public void warehouseScheduler() {
		// TODO
	}

	@Override
	public List<Warehouse> getWarehouses(String id) {
		return warehouseDaoService.getWarehouses(id);
		
	}

}
