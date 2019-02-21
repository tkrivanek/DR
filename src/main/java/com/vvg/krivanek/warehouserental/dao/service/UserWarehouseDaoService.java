package com.vvg.krivanek.warehouserental.dao.service;

import java.util.List;

import com.vvg.krivanek.warehouserental.domain.UserWarehouse;

public interface UserWarehouseDaoService {

	List<UserWarehouse> getUserWarehouses(Long userId);
}
