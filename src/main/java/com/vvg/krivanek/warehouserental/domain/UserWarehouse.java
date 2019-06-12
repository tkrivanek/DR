package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class UserWarehouse implements Serializable{


	private static final long serialVersionUID = 4220829512901618399L;
	private Long id;
	private Date dateFrom;
	private Date dateTo;
	private Warehouse warehouse;
}
