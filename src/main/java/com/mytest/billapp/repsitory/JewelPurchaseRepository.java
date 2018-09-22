package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.JewelPurchase;

@Repository
public interface JewelPurchaseRepository extends JpaRepository<JewelPurchase, Long> {

}
