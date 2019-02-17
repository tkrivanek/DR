package com.vvg.krivanek.warehouserental.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseAuctionDao {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	
}
