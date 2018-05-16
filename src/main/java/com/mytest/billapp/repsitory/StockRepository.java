package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mytest.billapp.model.Stock;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Long> {
	
	public Stock findByItemCode(String itemCode);

	//public void deleteByItemCode(String itemCode);
	
	@Modifying
	@Query("delete from Stock stock where stock.itemCode = :itemCode")
	public void removeByItemCode(@Param("itemCode") String itemCode);

}
