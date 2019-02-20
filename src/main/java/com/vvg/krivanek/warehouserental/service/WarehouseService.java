package com.vvg.krivanek.warehouserental.service;

import java.util.List;

import com.vvg.krivanek.warehouserental.domain.Warehouse;

public interface WarehouseService {

	void warehouseScheduler();

	List<Warehouse> getWarehouses(String id);
}
