package com.mytest.billapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.billapp.model.Brand;
import com.mytest.billapp.model.Notes;
import com.mytest.billapp.model.ProductItems;
import com.mytest.billapp.model.Sale;
import com.mytest.billapp.model.SaleItems;
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.repsitory.NotesRepository;
import com.mytest.billapp.service.BrandService;
import com.mytest.billapp.service.ProductService;
import com.mytest.billapp.service.SalesService;
import com.mytest.billapp.service.StockService;
import com.mytest.billapp.service.VendorService;
import com.mytest.billapp.utils.AppConstants;
import com.mytest.billapp.view.InvoiceHeaderView;
import com.mytest.billapp.view.InvoiceItemView;
import com.mytest.billapp.view.InvoicePrintView;
import com.mytest.billapp.view.SaleEntryView;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
	
	@Autowired
	NotesRepository noteRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	StockService stockService;
	
	@Autowired
	SalesService salesService;
	
	@Autowired
	BrandService brandService;
	
	@GetMapping("/brand/all")
	public List<Brand> getAllBrands() {
		return brandService.findAll();
	}
	
	@PostMapping("/brand")
	public Brand getBrand(@RequestBody Brand brand) {
		return brandService.getOne(brand.getId());
	}
	
	@PostMapping("/brand/save")
	public void saveBrand(@RequestBody Brand brand) {
		Brand dbBrand =  new Brand();
		if(brand != null && brand.getId() != 0) 
			dbBrand = brandService.getOne(brand.getId());
		dbBrand.setName(brand.getName());
		brandService.save(dbBrand);
	}
	
	@PostMapping("/brand/delete")
	public void deleteBrand(@RequestBody Brand brand) {
		if(brand != null && brand.getId() != 0) 
			brandService.deleteById(brand.getId());
	}
	
	
	@GetMapping("/notes")
	public List<Notes> getAllNotes() {
	    return noteRepository.findAll();
	}
	
	@PostMapping("/notes")
	public Notes createNote(@Valid @RequestBody Notes note) {
	    return noteRepository.save(note);
	}
	
	@GetMapping("/vendor/{id}")
	public Vendor getVendorById(@PathVariable(value = "id") Long noteId) {
	    try {
			return vendorService.findById(noteId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/product/{id}")
	public Map<Integer, String> getProductSizeListById(@PathVariable(value = "id") Long productId) {
	    try {
			return productService.getProductSizeList(productId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/product/items/{id}")
	public List<ProductItems> getProductItemListById(@PathVariable(value = "id") Long productId) {
	    try {
			return productService.getProductItemList(productId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	@GetMapping("/notes/{id}")
	public Notes getNoteById(@PathVariable(value = "id") Long noteId) {
	    return noteRepository.findOne(noteId);
	            //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}
	
	@PutMapping("/notes/{id}")
	public Notes updateNote(@PathVariable(value = "id") Long noteId,  @Valid @RequestBody Notes noteDetails) {

	    Notes note = noteRepository.findOne(noteId);
	          //  .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	    note.setTitle(noteDetails.getTitle());
	    note.setContent(noteDetails.getContent());
	    Notes updatedNote = noteRepository.save(note);
	    return updatedNote;
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
	    Notes note = noteRepository.findOne(noteId);
	           // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	    noteRepository.delete(note);

	    return ResponseEntity.ok().build();
	}
	
	@GetMapping("/stock/itemCode/{itemCode}")
	public SaleEntryView getSaleEntry(@PathVariable(value = "itemCode") String itemCode) {
		System.out.println("@@@@@@@@@@@@  "+itemCode);
		SaleEntryView saleEntryView = stockService.getStockEntryByItemCode(itemCode);
	    return saleEntryView;
	}
	
	@PostMapping("sale/saveSale")
	public InvoicePrintView saveSale(@RequestBody List<SaleEntryView> saleEntryViewList) {
		String invoiceNo = StringUtils.EMPTY;
		try {
			System.out.println("############## "+saleEntryViewList);
			invoiceNo = salesService.saveSales(saleEntryViewList);
			return generateInvoiceDetails(invoiceNo);
		} catch (Exception e) {
			e.printStackTrace();
			return new InvoicePrintView();
		}
	}
	
	private InvoicePrintView generateInvoiceDetails(String invoiceNo) {
		InvoicePrintView invoicePrintView = new InvoicePrintView();
		invoicePrintView.setInvoiceHeader(getBusinessDetails());
		Sale sale = salesService.findByInvoiceNo(invoiceNo);
		if(sale == null) return invoicePrintView;
		invoicePrintView.setInvoiceDate(DateFormatUtils.format(sale.getInvoiceDate(), AppConstants.DATE_TIME));
		invoicePrintView.setInvoiceDiscount(sale.getDiscount() != null ? sale.getDiscount().toString() : "0.00");
		invoicePrintView.setInvoiceItemTotal(sale.getInvoiceTotal().toString());
		invoicePrintView.setInvoiceNetAmount(sale.getNetTotal().toString());
		invoicePrintView.setInvoiceNumber(invoiceNo);
		invoicePrintView.setInvoiceItems(getInvoiceItems(sale.getId()));
		invoicePrintView.setItemCount(invoicePrintView.getInvoiceItems().size()+"");
		return invoicePrintView;
	}
	
	private List<InvoiceItemView> getInvoiceItems(Long saleId) {
		List<InvoiceItemView> invoiceItems = new ArrayList<>();
		List<SaleItems> saleItems = salesService.findBySaleId(saleId);
		if(CollectionUtils.isEmpty(saleItems)) return invoiceItems;
		Collections.sort(saleItems, new SaleItemComparator());
		int index = 1;
		for(SaleItems saleItem : saleItems) {
			InvoiceItemView invoiceItemView = new InvoiceItemView();
			invoiceItemView.setSrNo(index);
			invoiceItemView.setDesc(saleItem.getItemDesc());
			invoiceItemView.setQuantity(saleItem.getItemQty().toString());
			invoiceItemView.setRate(saleItem.getItemRate().toString());
			invoiceItemView.setTotalItemCost(saleItem.getItemTotal().toString());
			invoiceItems.add(invoiceItemView);
			index++;
		}
		return invoiceItems;
	}
	
	private class SaleItemComparator implements Comparator<SaleItems> {
		public int compare(SaleItems arg0, SaleItems arg1) {
			// TODO Auto-generated method stub
			return arg0.getItemDesc().compareTo(arg1.getItemDesc());
		}
		
	}
	
	private InvoiceHeaderView getBusinessDetails() {
		InvoiceHeaderView invoiceHeaderView = new InvoiceHeaderView();
		invoiceHeaderView.setBusinessName("ABC Pvt Ltd.");
		invoiceHeaderView.setAddressLine1("Gandhi Nagar, Hyderabad");
		invoiceHeaderView.setAddressLine2("Telangana");
		invoiceHeaderView.setPhoneNo("040-524685454, 9789854587");
		invoiceHeaderView.setEmail("abcd@testgmail.com");
		return invoiceHeaderView;
	}

}
