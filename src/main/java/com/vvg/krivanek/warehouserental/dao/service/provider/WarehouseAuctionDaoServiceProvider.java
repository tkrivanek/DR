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

import com.vvg.krivanek.warehouserental.dao.service.WarehouseAuctionDaoService;
import com.vvg.krivanek.warehouserental.domain.AuctionBid;
import com.vvg.krivanek.warehouserental.domain.Warehouse;
import com.vvg.krivanek.warehouserental.domain.WarehouseAuction;

@Repository
public class WarehouseAuctionDaoServiceProvider implements WarehouseAuctionDaoService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate jdbc;

	@Override
	public Page<WarehouseAuction> getWarehousesOnAuction(Pageable pageable) {
		StringBuilder countQuery = new StringBuilder("SELECT count(1) AS row_count "
				+ "FROM WAREHOUSE, WAREHOUSE_TYPE, WAREHOUSE_STATUS, WAREHOUSE_AUCTION, USER "
				+ "WHERE WAREHOUSE.STATUS_ID=WAREHOUSE_STATUS.ID AND WAREHOUSE.TYPE_ID=WAREHOUSE_TYPE.ID "
				+ "AND WAREHOUSE_AUCTION.WAREHOUSE_ID= WAREHOUSE.ID "
				+ "AND WAREHOUSE_AUCTION.USERNAME=USER.USERNAME ");

		StringBuilder query = new StringBuilder(
				"SELECT WAREHOUSE.*, WAREHOUSE_TYPE.NAME, WAREHOUSE_TYPE.id, WAREHOUSE_AUCTION.*, WAREHOUSE_STATUS.*"
						+ "FROM WAREHOUSE, WAREHOUSE_TYPE, WAREHOUSE_STATUS, WAREHOUSE_AUCTION, USER "
						+ "WHERE WAREHOUSE.STATUS_ID=WAREHOUSE_STATUS.ID AND WAREHOUSE.TYPE_ID=WAREHOUSE_TYPE.ID "
						+ "AND WAREHOUSE_AUCTION.WAREHOUSE_ID= WAREHOUSE.ID "
						+ "AND WAREHOUSE_AUCTION.USERNAME=USER.USERNAME ");

		countQuery.append(" LIMIT " + pageable.getPageSize() + " " + " OFFSET " + pageable.getOffset());
		int totalCount = jdbcTemplate.queryForObject(countQuery.toString(), Integer.class);

		List<WarehouseAuction> warehouseAuctions = jdbcTemplate.query(query.toString(), new WarehouseAuctionMapper());
		return new PageImpl<>(warehouseAuctions, pageable, totalCount);
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
//				warehouseAuction.setStatus(rs.getString("WAREHOUSE_AUCTION_STATUS.NAME"));
				warehouseAuction.setStartDate(rs.getDate("WAREHOUSE_AUCTION.START_DATE"));
				warehouseAuction.setEndDate(rs.getDate("WAREHOUSE_AUCTION.END_DATE"));
				warehouseAuction.setEndPrice(rs.getString("WAREHOUSE_AUCTION.END_PRICE"));
				warehouseAuction.setBidPrice(rs.getString("WAREHOUSE_AUCTION.BID_PRICE"));
				warehouseAuction.setUsername(rs.getString("WAREHOUSE_AUCTION.USERNAME"));

				warehouse.setId(rs.getLong("WAREHOUSE.ID"));
				warehouse.setAddress(rs.getString("WAREHOUSE.ADDRESS"));
				warehouse.setAuctionStartPrice(rs.getString("WAREHOUSE.AUCTION_START_PRICE"));
				warehouse.setDailyPrice(rs.getLong("WAREHOUSE.DAILY_PRICE"));
				warehouse.setFull(rs.getBoolean("WAREHOUSE.FULL"));
				warehouse.setName(rs.getString("WAREHOUSE.NAME"));

				warehouse.setWarehouseStatusId(rs.getString("WAREHOUSE_STATUS.ID"));
				warehouse.setWarehouseStatusName(rs.getString("WAREHOUSE_STATUS.NAME"));

				warehouse.setWarehouseTypeId(rs.getString("WAREHOUSE_TYPE.ID"));
				warehouse.setWarehouseTypeName(rs.getString("WAREHOUSE_TYPE.NAME"));

				warehouse.setVolume(rs.getLong("WAREHOUSE.VOLUME"));
				warehouseAuction.setWarehouse(warehouse);
			}
			return warehouseAuction;
		}
	}

	class WarehouseMapper implements RowMapper<Warehouse> {
		@Override
		public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {

			return null;

		}
	}

	@Override
	public void insertWarehouseAuction(WarehouseAuction warehouseAuction) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO WAREHOUSE_AUCTION (WAREHOUSE_ID, START_PRICE, NAME, START_DATE, END_DATE, END_PRICE, BID_PRICE, USERNAME) "
						+ "VALUES (:warehouseId, :startPrice, :name, :startDate, :endDate, :endPrice, :bidPrice, :username)");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("warehouseId", warehouseAuction.getWarehouseId());
		parameters.addValue("startPrice", warehouseAuction.getStartPrice());
		parameters.addValue("name", warehouseAuction.getName());
		parameters.addValue("startDate", warehouseAuction.getStartDate());
		parameters.addValue("endDate", warehouseAuction.getEndDate());
		parameters.addValue("endPrice", warehouseAuction.getEndPrice());
		parameters.addValue("bidPrice", warehouseAuction.getBidPrice());
		parameters.addValue("username", warehouseAuction.getUsername());
		jdbc.update(query.toString(), parameters);
	}

	@Override
	public WarehouseAuction getWarehouseAuctionById(String auctionWarehouseId) {
		StringBuilder query = new StringBuilder(
				"SELECT WAREHOUSE.*, WAREHOUSE_TYPE.NAME, WAREHOUSE_TYPE.id, WAREHOUSE_AUCTION.*, WAREHOUSE_STATUS.* "
						+ "FROM WAREHOUSE, WAREHOUSE_TYPE, WAREHOUSE_STATUS, WAREHOUSE_AUCTION, USER "
						+ "WHERE WAREHOUSE.STATUS_ID=WAREHOUSE_STATUS.ID AND WAREHOUSE.TYPE_ID=WAREHOUSE_TYPE.ID "
						+ "AND WAREHOUSE_AUCTION.WAREHOUSE_ID= WAREHOUSE.ID "
						+ "AND WAREHOUSE_AUCTION.USERNAME=USER.USERNAME AND WAREHOUSE_AUCTION.ID = :id");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", auctionWarehouseId);

		return jdbc.queryForObject(query.toString(), parameters, new WarehouseAuctionMapper());
	}

	@Override
	public void insertAuctionBid(AuctionBid auctionBid) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO AUCTION_BID (USER_ID, WAREHOUSE_AUCTION_ID, BID_PRICE) VALUES ( :userId, :warehouseAuctionId, :bidPrice)");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", auctionBid.getUserId());
		parameters.addValue("warehouseAuctionId", auctionBid.getWarehouseAuctionId());
		parameters.addValue("bidPrice", auctionBid.getBidPrice());
		jdbc.update(query.toString(), parameters);

	}

	@Override
	public void updateBidPrice(Long warehouseAuctionId, String bidPrice) {
		StringBuilder query = new StringBuilder(
				"UPDATE WAREHOUSE_AUCTION SET BID_PRICE=:bidPrice WHERE ID=:warehouseAuctionId");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("warehouseAuctionId", warehouseAuctionId);
		parameters.addValue("bidPrice", bidPrice);
		jdbc.update(query.toString(), parameters);
		
	}
}
