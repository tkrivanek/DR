package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuctionBid implements Serializable{

	private static final long serialVersionUID = -7928464707791326857L;
	private Long id;
	private Long userId;
	private Long warehouseAuctionId;
	private String bidPrice;
}
