package com.mytest.billapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mytest.billapp.model.Brand;
import com.mytest.billapp.model.Customer;
import com.mytest.billapp.model.JewelCategory;
import com.mytest.billapp.model.JewelPurchase;
import com.mytest.billapp.model.JewelPurchaseDetails;
import com.mytest.billapp.model.Notes;
import com.mytest.billapp.model.Permissions;
import com.mytest.billapp.model.ProductItems;
import com.mytest.billapp.model.Role;
import com.mytest.billapp.model.Sale;
import com.mytest.billapp.model.SaleItems;
import com.mytest.billapp.model.Supplier;
import com.mytest.billapp.model.User;
import com.mytest.billapp.model.Vendor;
import com.mytest.billapp.repsitory.NotesRepository;
import com.mytest.billapp.service.BrandService;
import com.mytest.billapp.service.CustomerService;
import com.mytest.billapp.service.JewelCategoryService;
import com.mytest.billapp.service.JewelPurchaseDetailsService;
import com.mytest.billapp.service.JewelPurchaseService;
import com.mytest.billapp.service.LivePriceService;
import com.mytest.billapp.service.PermissionsService;
import com.mytest.billapp.service.ProductService;
import com.mytest.billapp.service.RoleService;
import com.mytest.billapp.service.SalesService;
import com.mytest.billapp.service.StockService;
import com.mytest.billapp.service.SupplierService;
import com.mytest.billapp.service.UserService;
import com.mytest.billapp.service.VendorService;
import com.mytest.billapp.utils.AppConstants;
import com.mytest.billapp.utils.AppUtils;
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
	SupplierService supplierService;
	
	@Autowired
	StockService stockService;
	
	@Autowired
	SalesService salesService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PermissionsService permissionsService;
	
	@Autowired
	UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	JewelCategoryService jewelCategoryService;
	
	@Autowired
	LivePriceService livePriceService;
	
	@Autowired
	JewelPurchaseService jewelPurchaseService; 
	
	@Autowired
	JewelPurchaseDetailsService JewelPurchaseDetailsService; 
	
	@PostMapping("/jewelPurchase/save")
	public void saveJewelPurchase(@RequestBody JewelPurchase jewelPurchase) {
		JewelPurchase dbJewelPurchase =  new JewelPurchase();
		List<JewelPurchaseDetails> dbJewelPurchaseDetailsList = new ArrayList<>();
		if(jewelPurchase != null && AppUtils.isValidNonZeroLong(jewelPurchase.getId())) 
			dbJewelPurchase = jewelPurchaseService.getOne(jewelPurchase.getId());
		BigDecimal totalAmount = new BigDecimal(0.0);
		dbJewelPurchase.setPurchaseDate(jewelPurchase.getPurchaseDate());
		dbJewelPurchase.setSupplierId(jewelPurchase.getSupplierId());
		dbJewelPurchase.setTotalAmount(totalAmount);
		if(CollectionUtils.isNotEmpty(jewelPurchase.getJewelPurchaseDetails())) {
			for(JewelPurchaseDetails jewelPurchaseDetails : jewelPurchase.getJewelPurchaseDetails() ) {
				JewelPurchaseDetails dbJewelPurchaseDetails = new JewelPurchaseDetails();
				if(AppUtils.isValidNonZeroLong(jewelPurchaseDetails.getId())) 
					dbJewelPurchaseDetails = JewelPurchaseDetailsService.findById(jewelPurchaseDetails.getId());
				dbJewelPurchaseDetails.setAvgWieght(jewelPurchaseDetails.getAvgWieght());
				dbJewelPurchaseDetails.setCategoryId(jewelPurchaseDetails.getCategoryId());
				dbJewelPurchaseDetails.setJewelPurchase(dbJewelPurchase);
				dbJewelPurchaseDetails.setMakingChargePerPc(jewelPurchaseDetails.getMakingChargePerPc());
				dbJewelPurchaseDetails.setQuality(jewelPurchaseDetails.getQuality());
				dbJewelPurchaseDetails.setQuantity(jewelPurchaseDetails.getQuantity());
				dbJewelPurchaseDetails.setRateCutAt(jewelPurchaseDetails.getRateCutAt());
				dbJewelPurchaseDetails.setRateCutDate(jewelPurchaseDetails.getRateCutDate());
				dbJewelPurchaseDetails.setTaxRate(jewelPurchaseDetails.getTaxRate());
				dbJewelPurchaseDetails.setTotalWieght(jewelPurchaseDetails.getTotalWieght());
				dbJewelPurchaseDetails.setWastagePerPc(jewelPurchaseDetails.getWastagePerPc());
				dbJewelPurchaseDetailsList.add(dbJewelPurchaseDetails);
			}
		}
		dbJewelPurchase.setJewelPurchaseDetails(dbJewelPurchaseDetailsList);
		jewelPurchaseService.save(dbJewelPurchase);
	}
	
	
	@GetMapping("liveprice")
	public String getLivePrice() {
		return livePriceService.getLivePriceObject();
	}
	
	
	@GetMapping("/jewelCategory/all")
	public List<JewelCategory> getAllJewelCategorys() {
		return jewelCategoryService.findAll();
	}
	
	@PostMapping("/jewelCategory")
	public JewelCategory getJewelCategory(@RequestBody JewelCategory jewelCategory) {
		return jewelCategoryService.getOne(jewelCategory.getId());
	}
	
	@PostMapping("/jewelCategory/save")
	public void saveJewelCategory(@RequestBody JewelCategory jewelCategory) {
		JewelCategory dbJewelCategory =  new JewelCategory();
		if(jewelCategory != null && AppUtils.isValidNonZeroLong(jewelCategory.getId())) 
			dbJewelCategory = jewelCategoryService.getOne(jewelCategory.getId());
		dbJewelCategory.setName(jewelCategory.getName());
		jewelCategoryService.save(dbJewelCategory);
	}
	
	@PostMapping("/jewelCategory/delete")
	public void deleteJewelCategory(@RequestBody JewelCategory jewelCategory) {
		if(jewelCategory != null && AppUtils.isValidNonZeroLong(jewelCategory.getId())) 
			jewelCategoryService.deleteById(jewelCategory.getId());
	}
	
	
	@GetMapping("/customer/all")
	public List<Customer> getAllCustomers() {
		return customerService.findAll();
	}

	@PostMapping("/customer")
	public Customer getCustomer(@RequestBody Customer customer) {
		return customerService.getOne(customer.getId());
	}

	@PostMapping("/customer/save")
	public void saveCustomer(@RequestBody Customer customer) {
		Customer dbCustomer =  new Customer();
		if(customer != null && AppUtils.isValidNonZeroLong(customer.getId())) 
			dbCustomer = customerService.getOne(customer.getId());
		dbCustomer.setName(customer.getName());
		dbCustomer.setAddress(customer.getAddress());
		dbCustomer.setEmail(customer.getEmail());
		dbCustomer.setMobileNo(customer.getMobileNo());
		if(dbCustomer.getEntryDate() == null)
			dbCustomer.setEntryDate(new Date());
		customerService.save(dbCustomer);
	}

	@PostMapping("/customer/delete")
	public void deleteCustomer(@RequestBody Customer customer) {
		if(customer != null && AppUtils.isValidNonZeroLong(customer.getId())) 
			customerService.deleteById(customer.getId());
	}
	
	
	@GetMapping("/user/all")
	public List<User> getAllUser() {
		return userService.findAll();
	}

	@PostMapping("/user")
	public User getUser(@RequestBody User user) {
		return userService.getOne(user.getId());
	}

	@PostMapping("/user/save")
	public void saveUser(@RequestBody User user) {
		User dbUser =  new User();
		if(user != null && AppUtils.isValidNonZeroLong(user.getId())) 
			dbUser = userService.getOne(user.getId());
		dbUser.setEmail(user.getEmail());
		dbUser.setFirstName(user.getFirstName());
		dbUser.setLastName(user.getLastName());
		dbUser.setMobile(user.getMobile());
		dbUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		dbUser.setUserName(user.getUserName());
		dbUser.setRoles(new HashSet<>());
		Set<Role> roleList = new HashSet<>();
		if(!CollectionUtils.isEmpty(user.getRoles())) {
			for(Role uiRole : user.getRoles())
				roleList.add(roleService.getOne(uiRole.getId()));
		}
		dbUser.setRoles(roleList);
		userService.save(dbUser);
	}

	@PostMapping("/user/delete")
	public void deleteUser(@RequestBody User user) {
		if(user != null && AppUtils.isValidNonZeroLong(user.getId())) 
			userService.deleteById(user.getId());
	}
	
	
	
	
	
	@GetMapping("/role/all")
	public List<Role> getAllRole() {
		return roleService.findAll();
	}

	@PostMapping("/role")
	public Role getRole(@RequestBody Role role) {
		return roleService.getOne(role.getId());
	}

	@PostMapping("/role/save")
	public void saveRole(@RequestBody Role role) {
		Role dbRole =  new Role();
		if(role != null && AppUtils.isValidNonZeroLong(role.getId())) 
			dbRole = roleService.getOne(role.getId());
		dbRole.setRole(role.getRole());
		dbRole.setPermissions(new HashSet<>());
		Set<Permissions> permissionsList = new HashSet<>();
		if(!CollectionUtils.isEmpty(role.getPermissions())) {
			for(Permissions uiPerm : role.getPermissions())
				permissionsList.add(permissionsService.getOne(uiPerm.getId()));
		}
		dbRole.setPermissions(permissionsList);
		roleService.save(dbRole);
	}

	@PostMapping("/role/delete")
	public void deleteRole(@RequestBody Role role) {
		if(role != null && AppUtils.isValidNonZeroLong(role.getId())) 
			roleService.deleteById(role.getId());
	}
	
	
	@GetMapping("/permissions/all")
	public List<Permissions> getAllPermissionss() {
		ServletUriComponentsBuilder.fromCurrentRequest() ;
		List<Permissions> allPermissions = permissionsService.findAll();
		allPermissions.sort((Permissions o1, Permissions o2)->o1.getName().compareTo(o2.getName()));
		return allPermissions;
	}

	@PostMapping("/permissions")
	public Permissions getPermissions(@RequestBody Permissions permissions) {
		return permissionsService.getOne(permissions.getId());
	}

	@PostMapping("/permissions/save")
	public void savePermissions(@RequestBody Permissions permissions) {
		Permissions dbPermissions =  new Permissions();
		if(permissions != null && AppUtils.isValidNonZeroLong(permissions.getId())) 
			dbPermissions = permissionsService.getOne(permissions.getId());
		dbPermissions.setName(permissions.getName());
		dbPermissions.setLink(permissions.getLink());
		permissionsService.save(dbPermissions);
	}

	@PostMapping("/permissions/delete")
	public void deletePermissions(@RequestBody Permissions permissions) {
		if(permissions != null && AppUtils.isValidNonZeroLong(permissions.getId())) 
			permissionsService.deleteById(permissions.getId());
	}
	
	
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
		if(brand != null && AppUtils.isValidNonZeroLong(brand.getId())) 
			dbBrand = brandService.getOne(brand.getId());
		dbBrand.setName(brand.getName());
		brandService.save(dbBrand);
	}
	
	@PostMapping("/brand/delete")
	public void deleteBrand(@RequestBody Brand brand) {
		if(brand != null && AppUtils.isValidNonZeroLong(brand.getId())) 
			brandService.deleteById(brand.getId());
	}
	
	@GetMapping("/supplier/all")
	public List<Supplier> getAllSupplier() {
		return supplierService.findAll();
	}
	
	@PostMapping("/supplier")
	public Supplier getSupplier(@RequestBody Supplier supplier) {
		return supplierService.getOne(supplier.getId());
	}
	
	@PostMapping("/supplier/save")
	public void saveSupplier(@RequestBody Supplier supplier) {
		Supplier dbSupplier =  new Supplier();
		if(supplier != null && AppUtils.isValidNonZeroLong(supplier.getId())) 
			dbSupplier = supplierService.getOne(supplier.getId());
		dbSupplier.setName(supplier.getName());
		dbSupplier.setAddressLine1(supplier.getAddressLine1());
		dbSupplier.setAddressLine2(supplier.getAddressLine2());
		dbSupplier.setBankAccNo(supplier.getBankAccNo());
		dbSupplier.setBankBranch(supplier.getBankBranch());
		dbSupplier.setBankCity(supplier.getBankCity());
		dbSupplier.setBankIfsc(supplier.getBankIfsc());
		dbSupplier.setBankName(supplier.getBankName());
		dbSupplier.setBankState(supplier.getBankState());
		dbSupplier.setCity(supplier.getCity());
		dbSupplier.setCompany(supplier.getCompany());
		dbSupplier.setEmail(supplier.getEmail());
		dbSupplier.setFax(supplier.getFax());
		dbSupplier.setGstNo(supplier.getGstNo());
		dbSupplier.setMobile(supplier.getMobile());
		dbSupplier.setPhone(supplier.getPhone());
		dbSupplier.setState(supplier.getState());
		dbSupplier.setTinNo(supplier.getTinNo());
		dbSupplier.setZip(supplier.getZip());
		supplierService.save(dbSupplier);
	}
	
	@PostMapping("/supplier/delete")
	public void deleteSupplier(@RequestBody Supplier supplier) {
		if(supplier != null && supplier.getId() != 0) 
			supplierService.deleteById(supplier.getId());
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
