package com.vvg.krivanek.warehouserental.service.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.service.UserDaoService;
import com.vvg.krivanek.warehouserental.dao.service.UserWarehouseDaoService;
import com.vvg.krivanek.warehouserental.dao.service.WarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.UserWarehouse;
import com.vvg.krivanek.warehouserental.service.UserWarehouseService;

@Service
public class UserWarehouseServiceProvider implements UserWarehouseService {

	@Autowired
	UserWarehouseDaoService userWarehouseDaoService;

	@Autowired
	WarehouseDaoService warehouseDaoService;

	@Autowired
	UserDaoService userDaoService;

	@Override
	public Page<UserWarehouse> getUserWarehouses(Long userId, Pageable pageable) {
		return userWarehouseDaoService.getUserWarehouses(userId, pageable);

	}

	@Override
	public void saveWarehouseRent(UserWarehouse userWarehouse) {
		userWarehouseDaoService.saveWarehouseRent(userWarehouse);

	}

	@Override
	public void cancelWarehouseRent(String userId, String warehouseId) {
		userWarehouseDaoService.cancelWarehouseRent(userId, warehouseId);

	}

	@Override
	public Page<UserWarehouse> getAllUserWarehouses(Pageable pageable) {
		return userWarehouseDaoService.getAllUserWarehouses(pageable);
		
	}

}
