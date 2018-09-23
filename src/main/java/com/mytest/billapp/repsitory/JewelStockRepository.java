package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.JewelStock;

@Repository
public interface JewelStockRepository extends JpaRepository<JewelStock, Long> {
	public JewelStock findByItemCode(String itemCode);
}
