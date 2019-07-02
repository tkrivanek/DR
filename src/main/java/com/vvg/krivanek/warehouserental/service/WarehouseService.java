package com.vvg.krivanek.warehouserental.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vvg.krivanek.warehouserental.domain.Warehouse;

public interface WarehouseService {

	void warehouseScheduler();

	Page<Warehouse> getWarehouses(boolean notRented, boolean auction, Pageable pageable);

	Warehouse getWarehouse(String warehouseId);

	void saveWarehouse(Warehouse warehouse);

	void updateWarehouse(Warehouse warehouse);
	
	void deleteWarehouse (String warehouseId);
}
