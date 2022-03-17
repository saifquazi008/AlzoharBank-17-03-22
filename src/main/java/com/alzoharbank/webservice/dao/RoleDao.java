package com.alzoharbank.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.model.Account;
import com.alzoharbank.webservice.model.Role;

@Repository
public class RoleDao {

	@Autowired
	JdbcTemplate template;

	class RoleMapper implements RowMapper<Role> {

		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = new Role();
			role.setId(rs.getInt(1));
			role.setName(rs.getString(2));
			role.setDescription(rs.getString(3));
			role.setCreatedAt(rs.getDate(4));
			return role;
		}

	}

	public List<Role> findAllRole() {
		List<Role> role = new LinkedList<Role>();
		role = template.query("select * from roles", new RoleMapper());
		return role;
	}

	public Role findRoleById(int id) {
		return template.queryForObject("select * from roles where id=?", new RoleMapper(), id);

	}

	public Role findRoleByName(String name) {
		return template.queryForObject("select * from roles where name=?", new RoleMapper(), name);
	}

	public int insert(Role role) {
		return template.update("insert into roles(id,name,description)" + "values(?,?,?)",
				new Object[] { role.getId(), role.getName(), role.getDescription() });
	}

	public int update(Role role) {
		return template.update("update roles " + "set name=?, description=? " + " where id=?",
				new Object[] { role.getName(), role.getDescription(), role.getId() });
	}

	public int delete(int id) {
		return template.update("delete from roles where id=?", id);
	}

}
