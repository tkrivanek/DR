package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Warehouse implements Serializable{

	private static final long serialVersionUID = 1839934995078209411L;
	private Long id;
	private Long dailyPrice;
	private String name;
	private String address;
	private Long volume;
	private Boolean full;
	private String auctionStartPrice;
//	private WarehouseStatus warehouseStatus;
//	private WarehouseType warehouseType;
	private String warehouseTypeId;
	private String warehouseStatusId;
	private String warehouseTypeName;
	private String warehouseStatusName;
}
