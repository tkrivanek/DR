package com.vvg.krivanek.warehouserental.dao.service.provider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.Warehouse;

@Repository
public class WarehouseDaoServiceProvider implements WarehouseDaoService {

	@Autowired
	NamedParameterJdbcTemplate jdbc;

	@Override
	public List<Warehouse> getWarehouses(String id, boolean notRented) {
		StringBuilder query = new StringBuilder(
				"SELECT WAREHOUSE.*, WAREHOUSE_TYPE.NAME, WAREHOUSE_STATUS.NAME "
				+ "FROM WAREHOUSE, WAREHOUSE_TYPE, WAREHOUSE_STATUS "
				+ "WHERE WAREHOUSE.STATUS_ID=WAREHOUSE_STATUS.ID AND WAREHOUSE.TYPE_ID=WAREHOUSE_TYPE.ID");
		if(notRented) {
			query.append(" AND WAREHOUSE_STATUS.CODE='notRented'");
		}
		return jdbc.query(query.toString(), new WarehouseMapper());
	}

	class WarehouseMapper implements RowMapper<Warehouse> {
		@Override
		public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
			Warehouse warehouse = new Warehouse();
			if (!rs.wasNull()) {
				warehouse.setId(rs.getLong("ID"));
				warehouse.setAdress(rs.getString("ADDRESS"));
				warehouse.setAuctionStartPrice(rs.getString("AUCTION_START_PRICE"));
				warehouse.setDailyPrice(rs.getLong("DAILY_PRICE"));
				warehouse.setFull(rs.getBoolean("FULL"));
				warehouse.setName(rs.getString("NAME"));
				warehouse.setStatus(rs.getString("WAREHOUSE_STATUS.NAME"));
				warehouse.setType(rs.getString("WAREHOUSE_TYPE.NAME"));
				warehouse.setVolume(rs.getLong("VOLUME"));
			}
			return warehouse;
		}
	}

}
