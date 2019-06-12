package com.vvg.krivanek.warehouserental.dao.service.provider;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvg.krivanek.warehouserental.dao.service.UserDaoService;
import com.vvg.krivanek.warehouserental.domain.User;

@Repository
public class UserDaoServiceProvider implements UserDaoService {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public User getUserByUsername(String username) {
		String query = "SELECT USER.id, USER.name, USER.surname, USER.email, USER.phone, USER.username, USER.address, ROLE.code "
				+ "FROM warehouse_db.user USER, warehouse_db.user_role ROLE WHERE USER.username = ROLE.username AND USER.username = :username";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username", username);

		return jdbcTemplate.queryForObject(query.toString(), parameters, new UserMapper());
	}

	class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			if (!rs.wasNull()) {
				user.setId(rs.getLong("ID"));
				user.setName(rs.getString("NAME"));
				user.setSurname(rs.getString("SURNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setAddress(rs.getString("ADDRESS"));
				user.setUserRole(rs.getString("CODE"));
			}
			return user;
		}

	}
}
