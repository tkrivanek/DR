package com.vvg.krivanek.warehouserental.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseAuctionDaoService;
import com.vvg.krivanek.warehouserental.dao.service.WarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.service.WarehouseAuctionService;

@Service
public class WarehouseAuctionServiceProvider implements WarehouseAuctionService {

	@Autowired
	WarehouseAuctionDaoService warehouseAuctionDaoService;

	@Autowired
	WarehouseDaoService warehouseDaoService;

	private static final String WAREHOUSE_STATUS_ID_CONTROLL_READY = "5";

	@Override
	@Scheduled(cron = "0 0 1 * * ?")
	public void warehouseAuctionScheduler() {

		List<Warehouse> warehouses = warehouseDaoService.getRentFinishedWarehouses();
		if (!CollectionUtils.isEmpty(warehouses)) {
			for (Warehouse warehouse : warehouses) {
				if (!WAREHOUSE_STATUS_ID_CONTROLL_READY.equals(warehouse.getWarehouseStatusId())) {
					warehouse.setWarehouseStatusId(WAREHOUSE_STATUS_ID_CONTROLL_READY);
					warehouseDaoService.updateWarehouse(warehouse);
				}
			}
		}
	}
}
