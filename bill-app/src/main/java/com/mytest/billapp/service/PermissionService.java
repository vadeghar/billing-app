package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Permissions;

public interface PermissionService {
	public Permissions save(Permissions entity);
	public List<Permissions> saveAll(List<Permissions> entities);
	public Permissions saveAndFlush(Permissions entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Permissions entity);
	public void deleteAll(List<Permissions> entities);
	public void deleteInBatch(List<Permissions> entities);
	public void deleteAll();
	public Permissions getOne(Long id);
	public Permissions findById(Long id);
	public boolean existsById(Long id);
	public List<Permissions> findAll();
	public List<Permissions> findAll(Sort sort);
	public long count();
}
