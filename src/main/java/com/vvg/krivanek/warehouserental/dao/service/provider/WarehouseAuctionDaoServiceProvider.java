package com.vvg.krivanek.warehouserental.dao.service.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseAuctionDaoService;

@Repository
public class WarehouseAuctionDaoServiceProvider implements WarehouseAuctionDaoService {

	@Autowired
	JdbcTemplate jdbcTemplate;

}
