package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class WarehouseType implements Serializable {

	private static final long serialVersionUID = 5827407204917664848L;
	private String id;
	private String code;
	private String name;

}
