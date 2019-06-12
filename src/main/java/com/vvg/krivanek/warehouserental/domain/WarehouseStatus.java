package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class WarehouseStatus implements Serializable{

	private static final long serialVersionUID = -836994809164211556L;
	private String id;
	private String name;
	private String code;
}
