package com.vvg.krivanek.warehouserental.dao.service;

import java.util.List;

import com.vvg.krivanek.warehouserental.domain.WarehouseAuction;

public interface WarehouseAuctionDaoService {

	List<WarehouseAuction> getWarehousesOnAuction();
}
