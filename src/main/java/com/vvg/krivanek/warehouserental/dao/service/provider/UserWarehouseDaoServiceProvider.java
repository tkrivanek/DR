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
				"SELECT USER_WAREHOUSE.DATE_FROM, USER_WAREHOUSE.DATE_TO, WAREHOUSE.ID, WAREHOUSE.DAILY_PRICE, WAREHOUSE.NAME, WAREHOUSE.ADDRESS, WAREHOUSE.VOLUME, WAREHOUSE_TYPE.NAME "
						+ "FROM USER_WAREHOUSE, WAREHOUSE, WAREHOUSE_TYPE "
						+ "WHERE USER_WAREHOUSE.WAREHOUSE_ID = WAREHOUSE.ID AND USER_WAREHOUSE.USER_ID =:userId "
						+ "AND WAREHOUSE.TYPE_ID = WAREHOUSE_TYPE.ID;");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);

		return jdbcTemplate.query(query.toString(), parameters, new UserWarehousesMapper());
	}

	class UserWarehousesMapper implements RowMapper<UserWarehouse> {

		@Override
		public UserWarehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserWarehouse userWarehouse = new UserWarehouse();
			if (!rs.wasNull()) {
				userWarehouse.setDateFrom(rs.getDate("DATE_FROM"));
				userWarehouse.setDateTo(rs.getDate("DATE_TO"));
				Warehouse warehouse = new Warehouse();
				warehouse.setId(rs.getLong("ID"));
				warehouse.setDailyPrice(rs.getLong("DAILY_PRICE"));
				warehouse.setName(rs.getString("NAME"));
				warehouse.setAdress(rs.getString("ADDRESS"));
				warehouse.setVolume(rs.getLong("VOLUME"));
				warehouse.setType(rs.getString("WAREHOUSE_TYPE.NAME"));
				userWarehouse.setWarehouse(warehouse);
			}
			return userWarehouse;
		}
	}
}
