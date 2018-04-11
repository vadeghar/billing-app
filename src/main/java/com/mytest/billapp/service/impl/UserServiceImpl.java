package com.mytest.billapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.User;
import com.mytest.billapp.repsitory.UserRepository;
import com.mytest.billapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository; 
	
	public User save(User entity) {
		return userRepository.save(entity);
	}
	
	public List<User> saveAll(List<User> entities) {
		return userRepository.saveAll(entities);
	}
	
	public void flush() {
		userRepository.flush();
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public void delete(User entity) {
		userRepository.delete(entity);
	}
	
	public void deleteAll(List<User> entities) {
		userRepository.deleteAll(entities);
	}
	
	public void deleteInBatch(List<User> entities) {
		userRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	public User getOne(Long id) {
		return userRepository.getOne(id);
	}
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public List<User> findAll(Sort sort) {
		return userRepository.findAll(sort);
	}
	
	public long count() {
		return userRepository.count();
	}
	
	
	
	public User saveAndFlush(User entity) {
		return userRepository.saveAndFlush(entity);
	}
	
}
