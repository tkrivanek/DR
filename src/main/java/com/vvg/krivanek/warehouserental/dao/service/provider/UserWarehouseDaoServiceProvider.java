package com.vvg.krivanek.warehouserental.dao.service.provider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
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

	@Autowired
	JdbcTemplate template;

	@Override
	public Page<UserWarehouse> getUserWarehouses(Long userId, Pageable pageable) {
		StringBuilder countQuery = new StringBuilder(
				"SELECT count(1) AS row_count FROM USER_WAREHOUSE, WAREHOUSE, WAREHOUSE_TYPE "
						+ "WHERE USER_WAREHOUSE.WAREHOUSE_ID = WAREHOUSE.ID AND USER_WAREHOUSE.USER_ID =:userId "
						+ "AND WAREHOUSE.TYPE_ID = WAREHOUSE_TYPE.ID AND WAREHOUSE.ACTIVE");

		StringBuilder query = new StringBuilder(
				"SELECT USER_WAREHOUSE.DATE_FROM, USER_WAREHOUSE.DATE_TO, WAREHOUSE.ID, WAREHOUSE.DAILY_PRICE, WAREHOUSE.NAME, WAREHOUSE.ADDRESS, WAREHOUSE.VOLUME, WAREHOUSE_TYPE.ID, WAREHOUSE_TYPE.NAME, WAREHOUSE_TYPE.CODE "
						+ " FROM USER_WAREHOUSE, WAREHOUSE, WAREHOUSE_TYPE "
						+ " WHERE USER_WAREHOUSE.WAREHOUSE_ID = WAREHOUSE.ID AND USER_WAREHOUSE.USER_ID =:userId "
						+ " AND WAREHOUSE.TYPE_ID = WAREHOUSE_TYPE.ID AND WAREHOUSE.ACTIVE");
		query.append(" LIMIT " + pageable.getPageSize() + " " + "OFFSET " + pageable.getOffset());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);

		int totalCount = jdbcTemplate.queryForObject(countQuery.toString(), parameters, Integer.class);
//		(countQuery.toString(), parameters, new UserWarehousesCountMapper());

		List<UserWarehouse> warehouses = jdbcTemplate.query(query.toString(), parameters, new UserWarehousesMapper());
		return new PageImpl<>(warehouses, pageable, totalCount);

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
				warehouse.setAddress(rs.getString("ADDRESS"));
				warehouse.setVolume(rs.getLong("VOLUME"));

//				WarehouseType warehouseType = new WarehouseType();
//				warehouseType.setId(rs.getString("WAREHOUSE_TYPE.ID"));
//				warehouseType.setName(rs.getString("WAREHOUSE_TYPE.NAME"));
//				warehouseType.setCode(rs.getString("WAREHOUSE_TYPE.CODE"));
//				warehouse.setWarehouseType(warehouseType);
				warehouse.setWarehouseTypeId(rs.getString("WAREHOUSE_TYPE.ID"));
				warehouse.setWarehouseTypeName(rs.getString("WAREHOUSE_TYPE.NAME"));
				userWarehouse.setWarehouse(warehouse);
			}
			return userWarehouse;
		}
	}
	class UserWarehousesCountMapper implements RowMapper<Integer> {

		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("ROW_COUNT");
		}
		
	}
}
