package com.vvg.krivanek.warehouserental.dao.service.provider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.dao.service.UserWarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.UserWarehouse;
import com.vvg.krivanek.warehouserental.domain.Warehouse;

@Repository
public class UserWarehouseDaoServiceProvider implements UserWarehouseDaoService {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<UserWarehouse> getUserWarehouses(Long userId) {
		StringBuilder query = new StringBuilder(
				"SELECT user_warehouse.date_from, user_warehouse.date_to, warehouse.id, warehouse.daily_price, warehouse.name, warehouse.address, warehouse.volume, warehouse_type.name "
						+ "FROM user_warehouse, warehouse, warehouse_type "
						+ "WHERE user_warehouse.warehouse_id = warehouse.id AND user_warehouse.user_id =:userId "
						+ "AND warehouse.type_id = warehouse_type.id;");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);

		return jdbcTemplate.query(query.toString(), parameters, new UserWarehousesMapper());
	}

	class UserWarehousesMapper implements RowMapper<UserWarehouse> {

		@Override
		public UserWarehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserWarehouse userWarehouse = new UserWarehouse();
			if (!rs.wasNull()) {
				userWarehouse.setDateFrom(rs.getDate("date_from"));
				userWarehouse.setDateTo(rs.getDate("date_to"));
				Warehouse warehouse = new Warehouse();
				warehouse.setId(rs.getLong("id"));
				warehouse.setDailyPrice(rs.getLong("daily_price"));
				warehouse.setName(rs.getString("name"));
				warehouse.setAdress(rs.getString("address"));
				warehouse.setVolume(rs.getLong("volume"));
				warehouse.setType(rs.getString("warehouse_type.name"));
				userWarehouse.setWarehouse(warehouse);
			}
			return userWarehouse;
		}
	}
}
