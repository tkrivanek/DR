package com.vvg.krivanek.warehouserental.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vvg.krivanek.warehouserental.domain.UserWarehouse;

public interface UserWarehouseService {

	Page<UserWarehouse> getUserWarehouses (Long userId, Pageable pageable);
	
	void saveWarehouseRent (UserWarehouse userWarehouse);
	
	void cancelWarehouseRent (String userId, String warehouseId);
	
	Page<UserWarehouse> getAllUserWarehouses (Pageable pageable);
	
 }
