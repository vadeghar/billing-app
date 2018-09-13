package com.mytest.billapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mytest.billapp.model.Customer;
import com.mytest.billapp.repsitory.CustomerRepository;
import com.mytest.billapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository; 
	
	public Customer save(Customer entity) {
		return customerRepository.save(entity);
	}
	
	public List<Customer> saveAll(List<Customer> entities) {
		return customerRepository.save(entities);
	}
	
	public void flush() {
		customerRepository.flush();
	}
	
	public void deleteById(Long id) {
		customerRepository.delete(id);
	}
	
	public void delete(Customer entity) {
		customerRepository.delete(entity);
	}
	
	public void deleteAll(List<Customer> entities) {
		customerRepository.delete(entities);
	}
	
	public void deleteInBatch(List<Customer> entities) {
		customerRepository.deleteInBatch(entities);
	}
	
	public void deleteAll() {
		customerRepository.deleteAll();
	}
	
	public Customer getOne(Long id) {
		return customerRepository.getOne(id);
	}
	public Customer findById(Long id) {
		return customerRepository.findOne(id);
	}
	
	public boolean existsById(Long id) {
		return customerRepository.exists(id);
	}
	
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	public List<Customer> findAll(Sort sort) {
		return customerRepository.findAll(sort);
	}
	
	public long count() {
		return customerRepository.count();
	}
	
	public Customer saveAndFlush(Customer entity) {
		return customerRepository.saveAndFlush(entity);
	}
	
}
