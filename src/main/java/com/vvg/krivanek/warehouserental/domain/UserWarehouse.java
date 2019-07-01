package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserWarehouse implements Serializable{

	private static final long serialVersionUID = 4220829512901618399L;
	private Long id;
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date dateFrom;
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date dateTo;
	private Warehouse warehouse;
	private User user;
	private String warehouseId;
	private String userId;
}
