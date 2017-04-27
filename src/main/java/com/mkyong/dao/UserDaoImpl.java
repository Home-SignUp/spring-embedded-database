package com.mkyong.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mkyong.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sqlFindByName = "SELECT * FROM users WHERE name=:name";
    private final String    sqlFindAll = "SELECT * FROM users";

    @Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public User findByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        User result = namedParameterJdbcTemplate.queryForObject(sqlFindByName, params, new UserMapper());
        //new BeanPropertyRowMapper(Customer.class));

        return result;
	}

	@Override
	public List<User> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
        List<User> result = namedParameterJdbcTemplate.query(sqlFindAll, params, new UserMapper());

        return result;
	}

	private static final class UserMapper implements RowMapper<User> {
        @Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));

			return user;
		}
	}
}