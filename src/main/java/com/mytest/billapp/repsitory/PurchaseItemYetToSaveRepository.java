package com.mytest.billapp.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.PurchaseItemYetToSave;

@Repository
public interface PurchaseItemYetToSaveRepository extends JpaRepository<PurchaseItemYetToSave, Long> {

}
