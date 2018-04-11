package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
