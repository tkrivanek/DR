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
import com.vvg.krivanek.warehouserental.domain.User;
import com.vvg.krivanek.warehouserental.domain.UserWarehouse;
import com.vvg.krivanek.warehouserental.domain.Warehouse;

@Repository
public class UserWarehouseDaoServiceProvider implements UserWarehouseDaoService {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	JdbcTemplate template;

	@Autowired
	NamedParameterJdbcTemplate jdbc;

	@Override
	public Page<UserWarehouse> getUserWarehouses(Long userId, Pageable pageable) {
		StringBuilder countQuery = new StringBuilder(
				"SELECT count(1) AS row_count FROM USER_WAREHOUSE, WAREHOUSE, WAREHOUSE_TYPE "
						+ "WHERE USER_WAREHOUSE.WAREHOUSE_ID = WAREHOUSE.ID AND USER_WAREHOUSE.USER_ID =:userId "
						+ "AND WAREHOUSE.TYPE_ID = WAREHOUSE_TYPE.ID AND WAREHOUSE.ACTIVE AND USER_WAREHOUSE.ACTIVE");

		StringBuilder query = new StringBuilder(
				"SELECT USER_WAREHOUSE.DATE_FROM, USER_WAREHOUSE.DATE_TO, WAREHOUSE.ID, USER_WAREHOUSE.USER_ID, WAREHOUSE.DAILY_PRICE, "
				+ "WAREHOUSE.NAME, WAREHOUSE.ADDRESS, WAREHOUSE.VOLUME, WAREHOUSE_TYPE.ID, WAREHOUSE_TYPE.NAME, WAREHOUSE_TYPE.CODE, "
				+ "USER.NAME, USER.SURNAME, USER.EMAIL, USER.PHONE, USER.ADDRESS"
						+ " FROM USER_WAREHOUSE, WAREHOUSE, WAREHOUSE_TYPE, USER "
						+ " WHERE USER_WAREHOUSE.WAREHOUSE_ID = WAREHOUSE.ID AND USER_WAREHOUSE.USER_ID =:userId AND USER_WAREHOUSE.USER_ID = USER.ID"
						+ " AND WAREHOUSE.TYPE_ID = WAREHOUSE_TYPE.ID AND WAREHOUSE.ACTIVE AND USER_WAREHOUSE.ACTIVE");
		query.append(" LIMIT " + pageable.getPageSize() + " " + "OFFSET " + pageable.getOffset());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);

		int totalCount = jdbcTemplate.queryForObject(countQuery.toString(), parameters, Integer.class);

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
				User user = new User();
				user.setName(rs.getString("USER.NAME"));
				user.setSurname(rs.getString("SURNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setAddress(rs.getString("USER.ADDRESS"));
				
				Warehouse warehouse = new Warehouse();
				warehouse.setId(rs.getLong("ID"));
				warehouse.setDailyPrice(rs.getLong("DAILY_PRICE"));
				warehouse.setName(rs.getString("WAREHOUSE.NAME"));
				warehouse.setAddress(rs.getString("WAREHOUSE.ADDRESS"));
				warehouse.setVolume(rs.getLong("VOLUME"));

				warehouse.setWarehouseTypeId(rs.getString("WAREHOUSE_TYPE.ID"));
				warehouse.setWarehouseTypeName(rs.getString("WAREHOUSE_TYPE.NAME"));
				userWarehouse.setWarehouse(warehouse);
				userWarehouse.setUser(user);
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

	@Override
	public void saveWarehouseRent(UserWarehouse userWarehouse) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO warehouse_db.user_warehouse (user_id, warehouse_id, date_from, date_to, active) VALUES (:userId, :warehouseId, :dateFrom, :dateTo, :active)");
		StringBuilder updateStatusQuery = new StringBuilder(
				"UPDATE warehouse_db.warehouse SET warehouse_db.warehouse.status_id = 1 WHERE warehouse_db.warehouse.id = :warehouseId");

		MapSqlParameterSource parametersQuery = new MapSqlParameterSource();
		parametersQuery.addValue("userId", Long.valueOf(userWarehouse.getUserId()));
		parametersQuery.addValue("warehouseId", Long.valueOf(userWarehouse.getWarehouseId()));
		parametersQuery.addValue("dateFrom", userWarehouse.getDateFrom());
		parametersQuery.addValue("dateTo", userWarehouse.getDateTo());
		parametersQuery.addValue("active", Boolean.TRUE);

		MapSqlParameterSource parametersUpdate = new MapSqlParameterSource();
		parametersUpdate.addValue("warehouseId", Long.valueOf(userWarehouse.getWarehouseId()));

		jdbc.update(query.toString(), parametersQuery);
		jdbc.update(updateStatusQuery.toString(), parametersUpdate);

	}

	@Override
	public void cancelWarehouseRent(String userId, String warehouseId) {
		StringBuilder query = new StringBuilder(
				"UPDATE warehouse_db.user_warehouse SET USER_WAREHOUSE.ACTIVE = 0 WHERE USER_WAREHOUSE.USER_ID = :userId AND USER_WAREHOUSE.WAREHOUSE_ID = :warehouseId");
		StringBuilder updateStatusQuery = new StringBuilder(
				"UPDATE warehouse_db.warehouse SET warehouse_db.warehouse.status_id = 2 WHERE warehouse_db.warehouse.id = :warehouseId");

		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", Long.valueOf(userId));
		parameters.addValue("warehouseId", Long.valueOf(warehouseId));

		MapSqlParameterSource parametersUpdate = new MapSqlParameterSource();
		parametersUpdate.addValue("warehouseId", Long.valueOf(warehouseId));

		
		jdbc.update(query.toString(), parameters);
		jdbc.update(updateStatusQuery.toString(), parametersUpdate);
	}

	@Override
	public Page<UserWarehouse> getAllUserWarehouses(Pageable pageable) {
		StringBuilder countQuery = new StringBuilder(
				"SELECT count(1) AS row_count FROM USER_WAREHOUSE, WAREHOUSE, WAREHOUSE_TYPE "
						+ "WHERE USER_WAREHOUSE.WAREHOUSE_ID = WAREHOUSE.ID "
						+ "AND WAREHOUSE.TYPE_ID = WAREHOUSE_TYPE.ID AND WAREHOUSE.ACTIVE AND USER_WAREHOUSE.ACTIVE");

		StringBuilder query = new StringBuilder(
				"SELECT USER_WAREHOUSE.DATE_FROM, USER_WAREHOUSE.DATE_TO, WAREHOUSE.ID, USER_WAREHOUSE.USER_ID, WAREHOUSE.DAILY_PRICE, "
				+ "WAREHOUSE.NAME, WAREHOUSE.ADDRESS, WAREHOUSE.VOLUME, WAREHOUSE_TYPE.ID, WAREHOUSE_TYPE.NAME, WAREHOUSE_TYPE.CODE, "
				+ "USER.NAME, USER.SURNAME, USER.EMAIL, USER.PHONE, USER.ADDRESS"
						+ " FROM USER_WAREHOUSE, WAREHOUSE, WAREHOUSE_TYPE, USER "
						+ " WHERE USER_WAREHOUSE.WAREHOUSE_ID = WAREHOUSE.ID AND USER_WAREHOUSE.USER_ID = USER.ID"
						+ " AND WAREHOUSE.TYPE_ID = WAREHOUSE_TYPE.ID AND WAREHOUSE.ACTIVE AND USER_WAREHOUSE.ACTIVE");
		query.append(" LIMIT " + pageable.getPageSize() + " " + "OFFSET " + pageable.getOffset());
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		int totalCount = jdbcTemplate.queryForObject(countQuery.toString(), parameters, Integer.class);

		List<UserWarehouse> warehouses = jdbcTemplate.query(query.toString(), parameters, new UserWarehousesMapper());
		return new PageImpl<>(warehouses, pageable, totalCount);
	}

}
