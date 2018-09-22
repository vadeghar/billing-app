package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.JewelPurchaseDetails;

@Repository
public interface JewelPurchaseDetailsRepository extends JpaRepository<JewelPurchaseDetails, Long> {

}
