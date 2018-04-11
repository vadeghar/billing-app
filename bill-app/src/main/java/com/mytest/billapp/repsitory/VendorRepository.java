package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
