package com.vvg.krivanek.warehouserental.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseTypeDaoService;
import com.vvg.krivanek.warehouserental.domain.WarehouseType;
import com.vvg.krivanek.warehouserental.service.WarehouseTypeService;

@Service
public class WarehouseTypeServiceProvider implements WarehouseTypeService{

	@Autowired
	WarehouseTypeDaoService warehouseTypeDaoService;
	@Override
	public List<WarehouseType> getWarehouseTypes() {
		return warehouseTypeDaoService.getWarehouseType();
	}

}
