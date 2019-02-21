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
	public List<Warehouse> getWarehouses(String id) {
		StringBuilder query = new StringBuilder(
				"SELECT warehouse.*, warehouse_type.name, warehouse_status.name "
				+ "FROM warehouse, warehouse_type, warehouse_status "
				+ "WHERE warehouse.status_id=warehouse_status.id AND warehouse.type_id=warehouse_type.id");
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
				warehouse.setStatus(rs.getString("warehouse_status.name"));
				warehouse.setType(rs.getString("warehouse_type.name"));
				warehouse.setVolume(rs.getLong("VOLUME"));
			}
			return warehouse;
		}
	}

}
