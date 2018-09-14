package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.JewelCategory;

@Repository
public interface JewelCategoryRepository extends JpaRepository<JewelCategory, Long> {

}
