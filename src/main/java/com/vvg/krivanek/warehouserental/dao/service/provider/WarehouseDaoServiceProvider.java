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

import com.vvg.krivanek.warehouserental.dao.service.WarehouseDaoService;
import com.vvg.krivanek.warehouserental.domain.Warehouse;

@Repository
public class WarehouseDaoServiceProvider implements WarehouseDaoService {

	@Autowired
	NamedParameterJdbcTemplate jdbc;

	@Autowired
	JdbcTemplate template;

	class WarehouseMapper implements RowMapper<Warehouse> {
		@Override
		public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
			Warehouse warehouse = new Warehouse();
			if (!rs.wasNull()) {
				warehouse.setId(rs.getLong("ID"));
				warehouse.setAddress(rs.getString("ADDRESS"));
				warehouse.setAuctionStartPrice(rs.getString("AUCTION_START_PRICE"));
				warehouse.setDailyPrice(rs.getLong("DAILY_PRICE"));
				warehouse.setFull(rs.getBoolean("FULL"));
				warehouse.setName(rs.getString("NAME"));

//				WarehouseStatus warehouseStatus = new WarehouseStatus();
//				warehouseStatus.setId(rs.getString("WAREHOUSE_STATUS.ID"));
//				warehouseStatus.setName(rs.getString("WAREHOUSE_STATUS.NAME"));
//				warehouseStatus.setCode(rs.getString("WAREHOUSE_STATUS.CODE"));
//				warehouse.setWarehouseStatus(warehouseStatus);
				warehouse.setWarehouseStatusId(rs.getString("WAREHOUSE_STATUS.ID"));
				warehouse.setWarehouseStatusName(rs.getString("WAREHOUSE_STATUS.NAME"));
//				WarehouseType warehouseType = new WarehouseType();
//				warehouseType.setId(rs.getString("WAREHOUSE_TYPE.ID"));
//				warehouseType.setName(rs.getString("WAREHOUSE_TYPE.NAME"));
//				warehouseType.setCode(rs.getString("WAREHOUSE_TYPE.CODE"));
//				warehouse.setWarehouseType(warehouseType);
				warehouse.setWarehouseTypeId(rs.getString("WAREHOUSE_TYPE.ID"));
				warehouse.setWarehouseTypeName(rs.getString("WAREHOUSE_TYPE.NAME"));

				warehouse.setVolume(rs.getLong("VOLUME"));
			}
			return warehouse;
		}
	}

	@Override
	public Page<Warehouse> getPagedWarehouses(boolean notRented, boolean rented, boolean auction, Pageable pageable) {
		StringBuilder countQuery = new StringBuilder(
				"SELECT count(1) AS row_count FROM WAREHOUSE, WAREHOUSE_TYPE, WAREHOUSE_STATUS "
						+ "WHERE WAREHOUSE.STATUS_ID=WAREHOUSE_STATUS.ID AND WAREHOUSE.TYPE_ID=WAREHOUSE_TYPE.ID AND WAREHOUSE.ACTIVE");

		StringBuilder queryForList = new StringBuilder(
				"SELECT WAREHOUSE.*, WAREHOUSE_TYPE.ID, WAREHOUSE_TYPE.NAME, WAREHOUSE_TYPE.CODE, WAREHOUSE_STATUS.ID, WAREHOUSE_STATUS.NAME, WAREHOUSE_STATUS.CODE "
						+ "FROM WAREHOUSE, WAREHOUSE_TYPE, WAREHOUSE_STATUS "
						+ "WHERE WAREHOUSE.STATUS_ID=WAREHOUSE_STATUS.ID AND WAREHOUSE.TYPE_ID=WAREHOUSE_TYPE.ID AND WAREHOUSE.ACTIVE");
		if (notRented) {
			countQuery.append(" AND WAREHOUSE_STATUS.CODE='notRented'");
			queryForList.append(" AND WAREHOUSE_STATUS.CODE='notRented'");
		}
		if (rented) {
			countQuery.append(" AND WAREHOUSE_STATUS.CODE='rented'");
			queryForList.append(" AND WAREHOUSE_STATUS.CODE='rented'");
		}
		if (auction) {
			countQuery.append(" AND WAREHOUSE_STATUS.CODE='auction'");
			queryForList.append(" AND WAREHOUSE_STATUS.CODE='auction'");
		}
		queryForList.append(" LIMIT " + pageable.getPageSize() + " " + "OFFSET " + pageable.getOffset());
		int totalCount = template.queryForObject(countQuery.toString(), Integer.class);

		List<Warehouse> warehouses = jdbc.query(queryForList.toString(), new WarehouseMapper());

		return new PageImpl<>(warehouses, pageable, totalCount);
	}

	@Override
	public Warehouse getWarehouseById(String warehouseId) {
		StringBuilder query = new StringBuilder(
				"SELECT WAREHOUSE.*, WAREHOUSE_TYPE.ID, WAREHOUSE_TYPE.NAME, WAREHOUSE_TYPE.CODE,  WAREHOUSE_STATUS.ID, WAREHOUSE_STATUS.NAME, WAREHOUSE_STATUS.CODE "
						+ "FROM WAREHOUSE, WAREHOUSE_TYPE, WAREHOUSE_STATUS "
						+ "WHERE WAREHOUSE.STATUS_ID=WAREHOUSE_STATUS.ID AND WAREHOUSE.TYPE_ID=WAREHOUSE_TYPE.ID AND WAREHOUSE.ID = :warehouseId AND WAREHOUSE.ACTIVE");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("warehouseId", warehouseId);

		return jdbc.queryForObject(query.toString(), parameters, new WarehouseMapper());
	}

	@Override
	public void saveWarehouse(Warehouse warehouse) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO WAREHOUSE (DAILY_PRICE, TYPE_ID, STATUS_ID, FULL, NAME, VOLUME, AUCTION_START_PRICE, ADDRESS, ACTIVE) VALUES "
						+ "(:dailyPrice, :typeId, :statusId, :full, :name, :volume, :auctionStartPrice, :address, :active)");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("dailyPrice", warehouse.getDailyPrice());
		parameters.addValue("typeId", warehouse.getWarehouseTypeId());
		parameters.addValue("statusId", warehouse.getWarehouseStatusId());
		parameters.addValue("full", warehouse.getFull());
		parameters.addValue("name", warehouse.getName());
		parameters.addValue("volume", warehouse.getVolume());
		parameters.addValue("auctionStartPrice", warehouse.getAuctionStartPrice());
		parameters.addValue("address", warehouse.getAddress());
		parameters.addValue("active", Boolean.TRUE);

		jdbc.update(query.toString(), parameters);
	}

	@Override
	public void updateWarehouse(Warehouse warehouse) {
		StringBuilder query = new StringBuilder(
				"UPDATE WAREHOUSE SET DAILY_PRICE = :dailyPrice, TYPE_ID = :typeId, STATUS_ID = :statusId, FULL = :full, NAME = :name, VOLUME = :volume, AUCTION_START_PRICE = :auctionStartPrice, ADDRESS = :address, ACTIVE = :active\r\n"
						+ "WHERE id = :id");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", warehouse.getId());
		parameters.addValue("dailyPrice", warehouse.getDailyPrice());
		parameters.addValue("typeId", warehouse.getWarehouseTypeId());
		parameters.addValue("statusId", warehouse.getWarehouseStatusId());
		parameters.addValue("full", warehouse.getFull());
		parameters.addValue("name", warehouse.getName());
		parameters.addValue("volume", warehouse.getVolume());
		parameters.addValue("auctionStartPrice", warehouse.getAuctionStartPrice());
		parameters.addValue("address", warehouse.getAddress());
		parameters.addValue("active", Boolean.TRUE);

		jdbc.update(query.toString(), parameters);
	}

	@Override
	public void deleteWarehouse(String warehouseId) {
		StringBuilder query = new StringBuilder("UPDATE WAREHOUSE SET ACTIVE = 0 WHERE id = :id");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", warehouseId);

		jdbc.update(query.toString(), parameters);
	}

}
