package com.mytest.billapp.repsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.ProductItems;

@Repository
public interface ProductItemsRepository extends JpaRepository<ProductItems, Long> {
	public List<ProductItems> findByProductId(Long productId);
}
