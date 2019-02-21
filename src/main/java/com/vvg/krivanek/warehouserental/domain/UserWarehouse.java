package com.vvg.krivanek.warehouserental.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class UserWarehouse {

	private Long id;
	private Date dateFrom;
	private Date dateTo;
	private Warehouse warehouse;
}
