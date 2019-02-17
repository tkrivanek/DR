package com.vvg.krivanek.warehouserental.domain;

//@Data
public class Warehouse {
	private Long id;
	private String dailyPrice;
	private String name;
	private String adress;
	private String volume;
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
	public String getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(String dailyPrice) {
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
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
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
