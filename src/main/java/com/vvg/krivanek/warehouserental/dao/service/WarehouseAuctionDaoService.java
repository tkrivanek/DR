package com.vvg.krivanek.warehouserental.dao.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vvg.krivanek.warehouserental.domain.AuctionBid;
import com.vvg.krivanek.warehouserental.domain.WarehouseAuction;

public interface WarehouseAuctionDaoService {

	Page<WarehouseAuction> getWarehousesOnAuction(Pageable pageable);
		
	void insertWarehouseAuction (WarehouseAuction warehouseAuction);

	WarehouseAuction getWarehouseAuctionById (String auctionWarehouseId);
	
	void insertAuctionBid (AuctionBid auctionBid);
	
	void updateBidPrice(Long warehouseAuctionId, String bidPrice);
}
