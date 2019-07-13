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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseAuctionDaoService;
import com.vvg.krivanek.warehouserental.dao.service.provider.WarehouseDaoServiceProvider.WarehouseMapper;
import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.domain.WarehouseAuction;

@Repository
public class WarehouseAuctionDaoServiceProvider implements WarehouseAuctionDaoService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate jdbc;

	@Override
	public List<WarehouseAuction> getWarehousesOnAuction() {
		StringBuilder query = new StringBuilder(
				"SELECT WAREHOUSE.*, WAREHOUSE_TYPE.ID, WAREHOUSE_TYPE.NAME, WAREHOUSE_TYPE.CODE,  WAREHOUSE_STATUS.ID, WAREHOUSE_STATUS.NAME, WAREHOUSE_STATUS.CODE, WAREHOUSE_AUCTION.*, WAREHOUSE_AUCTION_STATUS.NAME, USER.USERNAME "
						+ "FROM WAREHOUSE, WAREHOUSE_TYPE, WAREHOUSE_STATUS, WAREHOUSE_AUCTION, WAREHOUSE_AUCTION_STATUS, USER "
						+ "WHERE WAREHOUSE.STATUS_ID=WAREHOUSE_STATUS.ID AND WAREHOUSE.TYPE_ID=WAREHOUSE_TYPE.ID "
						+ "AND WAREHOUSE_AUCTION.WAREHOUSE_ID= WAREHOUSE.ID AND WAREHOUSE_AUCTION.STATUS_ID=WAREHOUSE_AUCTION_STATUS.ID "
						+ "AND WAREHOUSE_AUCTION.USER_ID=USER.ID AND WAREHOUSE.ACTIVE");

		return jdbcTemplate.query(query.toString(), new WarehouseAuctionMapper());
	}

	class WarehouseAuctionMapper implements RowMapper<WarehouseAuction> {
		@Override
		public WarehouseAuction mapRow(ResultSet rs, int rowNum) throws SQLException {
			WarehouseAuction warehouseAuction = new WarehouseAuction();
			Warehouse warehouse = new Warehouse();
			if (!rs.wasNull()) {
				warehouseAuction.setId(rs.getLong("WAREHOUSE_AUCTION.ID"));
				warehouseAuction.setStartPrice(rs.getString("WAREHOUSE_AUCTION.START_PRICE"));
				warehouseAuction.setName(rs.getString("WAREHOUSE_AUCTION.NAME"));
				warehouseAuction.setStatus(rs.getString("WAREHOUSE_AUCTION_STATUS.NAME"));
				warehouseAuction.setStartDate(rs.getDate("WAREHOUSE_AUCTION.START_DATE"));
				warehouseAuction.setEndDate(rs.getDate("WAREHOUSE_AUCTION.END_DATE"));
				warehouseAuction.setEndPrice(rs.getString("WAREHOUSE_AUCTION.END_PRICE"));
				warehouseAuction.setBidPrice(rs.getString("WAREHOUSE_AUCTION.BID_PRICE"));
				warehouseAuction.setUsername(rs.getString("USER.USERNAME"));

				warehouse.setId(rs.getLong("WAREHOUSE.ID"));
				warehouse.setAddress(rs.getString("WAREHOUSE.ADDRESS"));
				warehouse.setAuctionStartPrice(rs.getString("WAREHOUSE.AUCTION_START_PRICE"));
				warehouse.setDailyPrice(rs.getLong("WAREHOUSE.DAILY_PRICE"));
				warehouse.setFull(rs.getBoolean("WAREHOUSE.FULL"));
				warehouse.setName(rs.getString("WAREHOUSE.NAME"));

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
				
				warehouse.setVolume(rs.getLong("WAREHOUSE.VOLUME"));
				warehouseAuction.setWarehouse(warehouse);
			}
			return warehouseAuction;
		}
	}
	
//	@Override
//	public Page<Warehouse> getRentFinishedWarehouses(Pageable pageable) {
//		
		
//		StringBuilder countQuery = new StringBuilder("SELECT count(1) AS row_count "
//				+" FROM user_warehouse, warehouse, warehouse_status, warehouse_type, user "
//				+" where user_warehouse.warehouse_id = warehouse.id and user_warehouse.active and warehouse.status_id=warehouse_status.id and warehouse.type_id=warehouse_type.id and user_warehouse.user_id=user.id");
//				
//
//		StringBuilder queryForList = new StringBuilder("SELECT user_warehouse.date_from, user_warehouse.date_to, warehouse.name, warehouse.address, warehouse.auction_start_price, warehouse.daily_price, warehouse.full, warehouse.status_id, warehouse.type_id, "
//				+"warehouse_status.name, warehouse_type.name, user.name, user.surname, user.address, user.phone, user.email "
//				+" FROM user_warehouse, warehouse, warehouse_status, warehouse_type, user "
//				+" where user_warehouse.warehouse_id = warehouse.id and user_warehouse.active and warehouse.status_id=warehouse_status.id and warehouse.type_id=warehouse_type.id and user_warehouse.user_id=user.id");
//				
//		
//		queryForList.append(" LIMIT " + pageable.getPageSize() + " " + "OFFSET " + pageable.getOffset());
//		int totalCount = jdbcTemplate.queryForObject(countQuery.toString(), Integer.class);
//
//		List<Warehouse> warehouses = jdbc.query(queryForList.toString(), new WarehouseMapper());
//
//		return new PageImpl<>(warehouses, pageable, totalCount);
//	}

	class WarehouseMapper implements RowMapper<Warehouse> {
		@Override
		public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			
			return null;
			
		}
	}
}
