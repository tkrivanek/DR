package com.vvg.krivanek.warehouserental.service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseAuctionDaoService;
import com.vvg.krivanek.warehouserental.dao.service.WarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.AuctionBid;
import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.domain.WarehouseAuction;
import com.vvg.krivanek.warehouserental.service.WarehouseAuctionService;

@Service
public class WarehouseAuctionService {

	@Autowired
	WarehouseAuctionDaoService warehouseAuctionDaoService;

	@Autowired
	WarehouseDaoService warehouseDaoService;

	private static final String WAREHOUSE_STATUS_READY_FOR_CONTROL = "5";
	private static final String WAREHOUSE_STATUS_AUCTION = "4";
	private static final String WAREHOUSE_STATUS_NOT_RENTED = "2";

//	@Scheduled(cron = "0 0 1 * * ?")
//	@Scheduled(fixedDelay = 100000)
	public void warehouseAuctionScheduler() {

		List<Warehouse> rentFinishedWarehouses = warehouseDaoService.getRentFinishedWarehouses();
		if (!CollectionUtils.isEmpty(rentFinishedWarehouses)) {
			for (Warehouse warehouse : rentFinishedWarehouses) {
				warehouse.setWarehouseStatusId(WAREHOUSE_STATUS_READY_FOR_CONTROL);
				warehouseDaoService.updateWarehouse(warehouse);
			}
		}
		List<Warehouse> auctionReadyWarehouses = warehouseDaoService.getAuctionReadyWarehouses();
		if (!CollectionUtils.isEmpty(auctionReadyWarehouses)) {
			for (Warehouse warehouse : auctionReadyWarehouses) {
				if (warehouse.getFull()) {
					warehouse.setWarehouseStatusId(WAREHOUSE_STATUS_AUCTION);
					warehouseAuctionDaoService.insertWarehouseAuction(createWarehouseAuction(warehouse));
				} else {
					warehouse.setWarehouseStatusId(WAREHOUSE_STATUS_NOT_RENTED);
				}
				warehouseDaoService.updateWarehouse(warehouse);
			}
		}
	}

	private WarehouseAuction createWarehouseAuction(Warehouse warehouse) {
		String username = "";
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			username = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		// auctionDuration
		LocalDate localDate = LocalDate.now().plusDays(7);

		WarehouseAuction warehouseAuction = new WarehouseAuction();
		warehouseAuction.setWarehouseId(warehouse.getId());
		warehouseAuction.setStartPrice(warehouse.getAuctionStartPrice());
		warehouseAuction.setName(warehouse.getName());
		warehouseAuction.setStartDate(new Date());
		warehouseAuction.setEndDate(Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC)));
		warehouseAuction.setEndPrice("");
		warehouseAuction.setBidPrice(warehouse.getAuctionStartPrice());
		warehouseAuction.setUsername(username);
		return warehouseAuction;
	}

	public Page<WarehouseAuction> getAuctionWarehouses(Pageable pageable) {
		return warehouseAuctionDaoService.getWarehousesOnAuction(pageable);
	}

	public WarehouseAuction getWarehouseAuctionById(String warehouseAuctionId) {
		return warehouseAuctionDaoService.getWarehouseAuctionById(warehouseAuctionId);
	}

	public void warehouseAuctionBid(AuctionBid auctionBid) {
		warehouseAuctionDaoService.insertAuctionBid(auctionBid);
		warehouseAuctionDaoService.updateBidPrice(auctionBid.getWarehouseAuctionId(), auctionBid.getBidPrice());
	}
}
