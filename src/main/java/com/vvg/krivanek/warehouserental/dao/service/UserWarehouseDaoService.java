package com.vvg.krivanek.warehouserental.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vvg.krivanek.warehouserental.domain.UserWarehouse;

public interface UserWarehouseDaoService {

	Page<UserWarehouse> getUserWarehouses(Long userId, Pageable pageable);
	
	void saveWarehouseRent (UserWarehouse userWarehouse);
	
	void cancelWarehouseRent (String userId, String warehouseId);
}
