package com.vvg.krivanek.warehouserental.dao.service.provider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.dao.service.WarehouseTypeDaoService;
import com.vvg.krivanek.warehouserental.domain.WarehouseType;

@Repository
public class WarehouseTypeDaoServiceProvider implements WarehouseTypeDaoService {

	@Autowired
	NamedParameterJdbcTemplate jdbc;

	@Override
	public List<WarehouseType> getWarehouseType() {
		StringBuilder query = new StringBuilder("SELECT * FROM WAREHOUSE_DB.WAREHOUSE_TYPE");

		return jdbc.query(query.toString(), new WarehouseTypeMapper());
	}

	class WarehouseTypeMapper implements RowMapper<WarehouseType> {

		@Override
		public WarehouseType mapRow(ResultSet rs, int rowNum) throws SQLException {
			WarehouseType warehouseType = new WarehouseType();
			warehouseType.setId(rs.getString("id"));
			warehouseType.setCode(rs.getString("code"));
			warehouseType.setName(rs.getString("name"));
			return warehouseType;
		}
	}
}
