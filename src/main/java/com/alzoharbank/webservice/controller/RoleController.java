package com.alzoharbank.webservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alzoharbank.webservice.dao.RoleDao;
import com.alzoharbank.webservice.exception.RoleNotFound;
import com.alzoharbank.webservice.model.Role;

@RestController
public class RoleController {

	@Autowired
	RoleDao roleDao;

	@GetMapping("/role")
	public Role getRoleByName(@RequestParam("name") String name) {
		Role role = roleDao.findRoleByName(name);
		if (role != null) {
			return role;
		}
		throw new RoleNotFound("Role Not Found With Given Name " + name);
	}

	@GetMapping("/roles")
	public List<Role> getRoles() {
		List<Role> list = roleDao.findAllRole();
		if (list.isEmpty()) {
			throw new RoleNotFound("Role is empty ,Zero Records Found !");
		}
		return list;
	}

	@GetMapping("roles/{id}")
	public Role getRoleById(@PathVariable("id") int id) {
		Role role = roleDao.findRoleById(id);
		if (role != null) {
			return role;
		}
		throw new RoleNotFound("Role Not Found With Given Id " + id);
	}

	@PostMapping("/roles")
	public Map<String, String> addRole(@RequestBody Role role) {
		int rowsAffected = roleDao.insert(role);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Role created Successfully !");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;

	}

	@PutMapping("/roles/{id}")
	public Map<String, String> updateRoleById(@PathVariable("id") int id, @RequestBody Role role) {
		int rowAffected = roleDao.update(role);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Role is updated successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;
	}

	@DeleteMapping("/role/{id}")
	public Map<String, String> deleteRoleById(@PathVariable("id") int id) {
		int rowAffected = roleDao.delete(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Role is deleted successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;

	}

}
