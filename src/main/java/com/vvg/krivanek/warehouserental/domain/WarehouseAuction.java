package com.vvg.krivanek.warehouserental.domain;

import java.util.Date;

import lombok.Data;

@Data
public class WarehouseAuction {
	private Long id;
	private String name;
	private String startPrice;
	private Date startDate;
	private Date endDate;
	private String bidPrice;
	private String endPrice;

	private Long warehouseId;
	private Long statusId;
	private Long userId;
	
}