package com.vvg.krivanek.warehouserental.dao.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.domain.WarehouseAuction;

public interface WarehouseAuctionDaoService {

	List<WarehouseAuction> getWarehousesOnAuction();
	
//	Page<Warehouse> getRentFinishedWarehouses(Pageable pageable);

}
