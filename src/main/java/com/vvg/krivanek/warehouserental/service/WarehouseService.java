package com.vvg.krivanek.warehouserental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.service.UserWarehouseDaoService;
import com.vvg.krivanek.warehouserental.dao.service.WarehouseAuctionDaoService;
import com.vvg.krivanek.warehouserental.dao.service.WarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.service.WarehouseService;

@Service
public class WarehouseService {

	@Autowired
	WarehouseDaoService warehouseDaoService;

	@Autowired
	UserWarehouseDaoService userWarehouseDaoService;

	@Autowired
	WarehouseAuctionDaoService warehouseAuctionDaoService;

	private static final String WAREHOUSE_STATUS_READY_FOR_AUCTION = "4";

////	@Scheduled(cron = "0 0 23 * * ?")
//	@Scheduled(fixedDelay = 1000)
//	public void warehouseScheduler() {
//		
//	}

	public Page<Warehouse> getWarehouses(boolean notRented, boolean auction, Pageable pageable) {
		return warehouseDaoService.getPagedWarehouses(notRented, auction, pageable);

	}

	public Warehouse getWarehouse(String warehouseId) {
		return warehouseDaoService.getWarehouseById(warehouseId);
	}

	public void saveWarehouse(Warehouse warehouse) {
		warehouseDaoService.saveWarehouse(warehouse);
	}

	public void updateWarehouse(Warehouse warehouse, boolean control) {
		if (control) {
			warehouse.setWarehouseStatusId(WAREHOUSE_STATUS_READY_FOR_AUCTION);
		}
		warehouseDaoService.updateWarehouse(warehouse);

	}

	public void deleteWarehouse(String warehouseId) {
		warehouseDaoService.deleteWarehouse(warehouseId);

	}

	public Page<Warehouse> getControllReadyWarehouses(Pageable pageable) {
		return warehouseDaoService.getControllReadyWarehouses(pageable);
	}

}
