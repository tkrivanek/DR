package com.vvg.krivanek.warehouserental.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.service.UserWarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.UserWarehouse;
import com.vvg.krivanek.warehouserental.service.UserWarehouseService;

@Service
public class UserWarehouseServiceProvider implements UserWarehouseService {

	@Autowired
	UserWarehouseDaoService userWarehouseDaoService;

	@Override
	public List<UserWarehouse> getUserWarehouses(Long userId) {
		return userWarehouseDaoService.getUserWarehouses(userId);

	}

}
