package com.vvg.krivanek.warehouserental.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vvg.krivanek.warehouserental.domain.Warehouse;

public interface WarehouseDaoService {

	Page<Warehouse> getPagedWarehouses(boolean notRented, boolean rented, boolean auction, Pageable pageable);

	Warehouse getWarehouseById(String warehouseId);

	void saveWarehouse(Warehouse warehouse);
	
	void updateWarehouse (Warehouse warehouse);
	
	void deleteWarehouse(String warehouseId);
}
