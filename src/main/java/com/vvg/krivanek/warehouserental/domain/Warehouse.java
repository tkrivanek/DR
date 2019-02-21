package com.vvg.krivanek.warehouserental.domain;

import lombok.Data;

@Data
public class Warehouse {
	private Long id;
	private Long dailyPrice;
	private String name;
	private String adress;
	private Long volume;
	private Boolean full;
	private String auctionStartPrice;
	private String status;
	private String type;

}
