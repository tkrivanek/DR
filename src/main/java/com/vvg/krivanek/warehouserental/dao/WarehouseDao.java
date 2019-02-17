package com.vvg.krivanek.warehouserental.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.domain.Warehouse;

@Repository
public class WarehouseDao {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public List<Warehouse> getWarehouses() {
		StringBuilder query = new StringBuilder("SELECT * FROM WAREHOUSE");
		SqlRowSet rs = jdbc.queryForRowSet(query.toString());
		
		return jdbc.query(query.toString(), new WarehouseMapper());
	}	
	
	class WarehouseMapper implements RowMapper<Warehouse> {
		@Override
		public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
			Warehouse warehouse = new Warehouse();		
			rs.getLong("ID");warehouse.setId(rs.wasNull() ? null : rs.getLong("ID"));
			
			return warehouse;
		}
	}

}
