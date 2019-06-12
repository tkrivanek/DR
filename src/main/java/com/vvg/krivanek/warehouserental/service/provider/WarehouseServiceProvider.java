package com.vvg.krivanek.warehouserental.service.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public  Page<Warehouse> getWarehouses( boolean notRented, boolean rented, boolean auction, Pageable pageable ) {
		return warehouseDaoService.getPagedWarehouses(notRented, rented, auction, pageable);
		
	}

	@Override
	public Warehouse getWarehouse(String warehouseId) {
		return warehouseDaoService.getWarehouseById(warehouseId);
	}

	@Override
	public void saveWarehouse(Warehouse warehouse) {
		warehouseDaoService.saveWarehouse(warehouse);
		
	}

	@Override
	public void updateWarehouse(Warehouse warehouse) {
		warehouseDaoService.updateWarehouse(warehouse);
		
	}

	@Override
	public void deleteWarehouse(String warehouseId) {
		warehouseDaoService.deleteWarehouse(warehouseId);
		
	}

}
