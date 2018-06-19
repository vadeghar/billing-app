package com.mytest.billapp.repsitory;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Sale;

@Repository
public interface  SalesRepository extends JpaRepository<Sale, Long> {
	
	public List<Sale> findByInvoiceDate(Date invoiceDate);

}
