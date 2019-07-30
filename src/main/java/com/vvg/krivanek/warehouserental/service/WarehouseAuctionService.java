package com.vvg.krivanek.warehouserental.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vvg.krivanek.warehouserental.domain.AuctionBid;
import com.vvg.krivanek.warehouserental.domain.WarehouseAuction;

public interface WarehouseAuctionService {

	void warehouseAuctionScheduler();

	Page<WarehouseAuction> getAuctionWarehouses(Pageable pageable);

	WarehouseAuction getWarehouseAuctionById(String warehouseAuctionId);

	void warehouseAuctionBid(AuctionBid auctionBid);
}
