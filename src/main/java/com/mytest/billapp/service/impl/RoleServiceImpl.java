package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Role;
import com.mytest.billapp.repsitory.RoleRepository;
import com.mytest.billapp.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository; 
	
	public Role save(Role entity) {
		return roleRepository.save(entity);
	}
	
	public List<Role> saveAll(List<Role> entities) {
		return roleRepository.save(entities);
	}
	
	public void flush() {
		roleRepository.flush();
	}
	
	public void deleteById(Long id) {
		roleRepository.delete(id);
	}
	
	public void delete(Role entity) {
		roleRepository.delete(entity);
	}
	
	public void deleteAll(List<Role> entities) {
		roleRepository.delete(entities);
	}
	
	public void deleteInBatch(List<Role> entities) {
		roleRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		roleRepository.deleteAll();
	}
	
	public Role getOne(Long id) {
		return roleRepository.getOne(id);
	}
	public Role findById(Long id) {
		return roleRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return roleRepository.exists(id);
	}
	
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	public List<Role> findAll(Sort sort) {
		return roleRepository.findAll(sort);
	}
	
	public long count() {
		return roleRepository.count();
	}
	
	
	
	public Role saveAndFlush(Role entity) {
		return roleRepository.saveAndFlush(entity);
	}
	
}
