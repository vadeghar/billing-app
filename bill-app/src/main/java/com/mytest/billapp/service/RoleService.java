package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Role;

public interface RoleService {
	
	public Role save(Role entity);
	public List<Role> saveAll(List<Role> entities);
	public Role saveAndFlush(Role entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Role entity);
	public void deleteAll(List<Role> entities);
	public void deleteInBatch(List<Role> entities);
	public void deleteAll();
	public Role getOne(Long id);
	public Role findById(Long id);
	public boolean existsById(Long id);
	public List<Role> findAll();
	public List<Role> findAll(Sort sort);
	public long count();

}
