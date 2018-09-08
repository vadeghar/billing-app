package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
