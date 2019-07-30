package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WarehouseAuction implements Serializable{

	private static final long serialVersionUID = -8042777685560849099L;
	private Long id;
	private String name;
	private String startPrice;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;
	private String bidPrice;
	private String endPrice;
	private Warehouse warehouse;
	private String status;
	private String username;
	private Long warehouseId;
}
