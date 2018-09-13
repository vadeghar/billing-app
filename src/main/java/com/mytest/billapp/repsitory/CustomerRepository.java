package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
