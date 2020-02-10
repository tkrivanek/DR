package com.vvg.krivanek.warehouserental.dao.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseStatusDaoService;
import com.vvg.krivanek.warehouserental.domain.WarehouseStatus;

@Repository
public class WarehouseStatusDaoService {

	@Autowired
	NamedParameterJdbcTemplate jdbc;

	public List<WarehouseStatus> getStatuses() {
		StringBuilder queryForList = new StringBuilder("SELECT * FROM WAREHOUSE_DB.WAREHOUSE_STATUS");

		return jdbc.query(queryForList.toString(), new WarehouseMapper());
	}

	class WarehouseMapper implements RowMapper<WarehouseStatus> {

		@Override
		public WarehouseStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
			WarehouseStatus status = new WarehouseStatus();
			if (!rs.wasNull()) {
				status.setId(rs.getString("ID"));
				status.setName(rs.getString("NAME"));
				status.setCode(rs.getString("CODE"));
			}
			return status;
		}
	}
}
