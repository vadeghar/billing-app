package com.mytest.billapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.User;

public interface UserService {
	
	public User save(User entity);
	public List<User> saveAll(List<User> entities);
	public User saveAndFlush(User entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(User entity);
	public void deleteAll(List<User> entities);
	public void deleteInBatch(List<User> entities);
	public void deleteAll();
	public User getOne(Long id);
	public Optional<User> findById(Long id);
	public boolean existsById(Long id);
	public List<User> findAll();
	public List<User> findAll(Sort sort);
	public long count();

}
