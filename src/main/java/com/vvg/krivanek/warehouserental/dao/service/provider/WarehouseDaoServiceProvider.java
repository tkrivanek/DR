package com.vvg.krivanek.warehouserental.dao.service.provider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.Warehouse;

@Repository
public class WarehouseDaoServiceProvider implements WarehouseDaoService{

	@Autowired
	JdbcTemplate jdbc;

	@Override
	public List<Warehouse> getWarehouses() {
		StringBuilder query = new StringBuilder("SELECT * FROM WAREHOUSE");
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
				warehouse.setStatusId(rs.getLong("STATUS_ID"));
				warehouse.setTypeId(rs.getLong("TYPE_ID"));
				warehouse.setVolume(rs.getLong("VOLUME"));
			}
			return warehouse;
		}
	}

}
