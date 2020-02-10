package com.vvg.krivanek.warehouserental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseStatusDaoService;
import com.vvg.krivanek.warehouserental.domain.WarehouseStatus;
import com.vvg.krivanek.warehouserental.service.WarehouseStatusService;

@Service
public class WarehouseStatusService {

	@Autowired
	WarehouseStatusDaoService warehouseStatusDaoService;

	public List<WarehouseStatus> getWarehouseStatuses() {
		return warehouseStatusDaoService.getStatuses();
	}

}
