package com.vvg.krivanek.warehouserental.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class User implements Serializable{

	private static final long serialVersionUID = -1222007145042549953L;
	private Long id;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String address;
	private String phone;
	private String userRole;
	private List<UserWarehouse> userWarehouses;
	
}
