package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Permissions;
import com.mytest.billapp.repsitory.PermissionsRepository;
import com.mytest.billapp.service.PermissionService;

public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	PermissionsRepository permissionsRepository; 
	
	public Permissions save(Permissions entity) {
		return permissionsRepository.save(entity);
	}
	
	public List<Permissions> saveAll(List<Permissions> entities) {
		return permissionsRepository.save(entities);
	}
	
	public void flush() {
		permissionsRepository.flush();
	}
	
	public void deleteById(Long id) {
		permissionsRepository.delete(id);
	}
	
	public void delete(Permissions entity) {
		permissionsRepository.delete(entity);
	}
	
	public void deleteAll(List<Permissions> entities) {
		permissionsRepository.delete(entities);
	}
	
	public void deleteInBatch(List<Permissions> entities) {
		permissionsRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		permissionsRepository.deleteAll();
	}
	
	public Permissions getOne(Long id) {
		return permissionsRepository.getOne(id);
	}
	public Permissions findById(Long id) {
		return permissionsRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return permissionsRepository.exists(id);
	}
	
	public List<Permissions> findAll() {
		return permissionsRepository.findAll();
	}
	
	public List<Permissions> findAll(Sort sort) {
		return permissionsRepository.findAll(sort);
	}
	
	public long count() {
		return permissionsRepository.count();
	}
	
	public Permissions saveAndFlush(Permissions entity) {
		return permissionsRepository.saveAndFlush(entity);
	}
	
}
