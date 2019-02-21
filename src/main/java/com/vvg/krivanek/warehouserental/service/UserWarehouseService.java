package com.vvg.krivanek.warehouserental.service;

import java.util.List;

import com.vvg.krivanek.warehouserental.domain.UserWarehouse;

public interface UserWarehouseService {

	List<UserWarehouse> getUserWarehouses (Long userId);
 }
