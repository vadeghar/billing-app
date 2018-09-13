package com.mytest.billapp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mytest.billapp.model.Customer;

public interface CustomerService {
	
	public Customer save(Customer entity);
	public List<Customer> saveAll(List<Customer> entities);
	public Customer saveAndFlush(Customer entity);
	public void flush();
	public void deleteById(Long id);
	public void delete(Customer entity);
	public void deleteAll(List<Customer> entities);
	public void deleteInBatch(List<Customer> entities);
	public void deleteAll();
	public Customer getOne(Long id);
	public Customer findById(Long id);
	public boolean existsById(Long id);
	public List<Customer> findAll();
	public List<Customer> findAll(Sort sort);
	public long count();

}
