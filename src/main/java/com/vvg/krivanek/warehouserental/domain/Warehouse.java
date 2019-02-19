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
	private Long typeId;
	private Long statusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(Long dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Boolean getFull() {
		return full;
	}

	public void setFull(Boolean full) {
		this.full = full;
	}

	public String getAuctionStartPrice() {
		return auctionStartPrice;
	}

	public void setAuctionStartPrice(String auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

}
