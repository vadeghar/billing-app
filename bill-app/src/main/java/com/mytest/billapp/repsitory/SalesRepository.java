package com.mytest.billapp.repsitory;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mytest.billapp.model.Sale;

@Repository
public interface  SalesRepository extends JpaRepository<Sale, Long> {
	
	public List<Sale> findByInvoiceDate(Date invoiceDate);
	
	@Query("select s1.invoiceNo from Sale s1 where s1.id = (select max(s2.id) from Sale s2) and date(s1.entryDate) = date(current_date()) ")
	public String findLatesetInvoiceNo();

}
