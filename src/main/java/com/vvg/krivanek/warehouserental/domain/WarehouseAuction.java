package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class WarehouseAuction implements Serializable{

	private static final long serialVersionUID = -8042777685560849099L;
	private Long id;
	private String name;
	private String startPrice;
	private Date startDate;
	private Date endDate;
	private String bidPrice;
	private String endPrice;
	private Warehouse warehouse;
	private String status;
	private String username;
}
