package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	
	public Stock findByItemCode(String itemCode);

}
