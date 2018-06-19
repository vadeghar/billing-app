package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.SaleItems;

@Repository
public interface  SalesItemsRepository extends JpaRepository<SaleItems, Long> {

}
