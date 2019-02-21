package com.vvg.krivanek.warehouserental.domain;

import java.util.List;

import lombok.Data;

@Data
public class User {
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
