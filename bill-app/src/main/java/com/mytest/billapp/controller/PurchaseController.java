package com.mytest.billapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mytest.billapp.dto.PurchaseDTO;
import com.mytest.billapp.dto.PurchaseItemDTO;
import com.mytest.billapp.exceptions.ResourceNotFoundException;
import com.mytest.billapp.model.Purchase;
import com.mytest.billapp.model.PurchaseItem;
import com.mytest.billapp.service.ProductItemsService;
import com.mytest.billapp.service.ProductService;
import com.mytest.billapp.service.PurchaseItemService;
import com.mytest.billapp.service.PurchaseService;
import com.mytest.billapp.service.StockService;
import com.mytest.billapp.service.VendorService;
import com.mytest.billapp.utils.AppUtils;
import com.mytest.billapp.view.PurchaseView;

@PropertySource("classpath:application.properties")
@Controller
@RequestMapping(value="admin/purchase")
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	PurchaseItemService purchaseItemService;
	
	@Autowired
	ProductItemsService productItemsService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	StockService stockService;
	
	@Value("${spring.default.brandId}")
	  private Long defaultBrandId;
	
	private List<PurchaseItemDTO> purchaseItems;
	private List<PurchaseItemDTO> deletedPurchaseItems;
	
	@RequestMapping(value = "purchaseList", method = RequestMethod.POST)
	public String purchaseList(Model model) {
		model.addAttribute("PurchaseList", purchaseService.findAll());
		model.addAttribute("purchase", new Purchase());
		model.addAttribute("selectedId","");
	    return "purchaseList";
	}
	
	
	
	@RequestMapping(value = "addPurchaseItem", method = RequestMethod.POST)
	public String addPurchaseItem(@ModelAttribute PurchaseDTO purchaseDTO, Model model) {
		PurchaseItemDTO purchaseItemDTO = new PurchaseItemDTO(purchaseDTO.getPurchaseItemDTO());
		if(CollectionUtils.isEmpty(purchaseItems))
			purchaseItems = new ArrayList<PurchaseItemDTO>();
		purchaseItems.add(purchaseItemDTO);
		PurchaseItemDTO nextPurchaseItemDTO = new PurchaseItemDTO();
		nextPurchaseItemDTO.setSrNo((purchaseItems.size()+1)+"");
		purchaseDTO.setPurchaseItemDTO(nextPurchaseItemDTO);
		setTotals(purchaseDTO, purchaseItems);
		model.addAttribute("purchase", purchaseDTO);
		model.addAttribute("purchaseItems", purchaseItems);
		addDataToModel(model);
		return "purchase";
	}
	
	
	private void setTotals(PurchaseDTO purchaseDTO, List<PurchaseItemDTO> purchaseItems) {
		Double total = new Double(0.0);
		Double discount = new Double(0.0);
		for(PurchaseItemDTO purchaseItemDTO : purchaseItems) {
			total = total + purchaseItemDTO.getTotal();
		}
		purchaseDTO.setBillTotal(AppUtils.formatDecimals(total));
		if(purchaseDTO.getId() != null && purchaseDTO.getId() > 0) {
			purchaseDTO.setDiscount(discount);
			purchaseDTO.setNetTotal(discount);
		}else {
			if(!StringUtils.isEmpty(purchaseDTO.getDiscountType())){
				if(purchaseDTO.getDiscountType().equals("%")){
					discount = total - (total * purchaseDTO.getDiscount() / 100);
				}else {
					discount = total - purchaseDTO.getDiscount();
				}
				purchaseDTO.setDiscount(AppUtils.formatDecimals(discount));
				purchaseDTO.setNetTotal(AppUtils.formatDecimals(total - discount));
			}
		}
	}



	private void addDataToModel(Model model) {
		model.addAttribute("purchaseList", purchaseService.findAll());
		if(defaultBrandId > 0) {
			model.addAttribute("productList", productService.getProductsByBrand(defaultBrandId));
		} else {
			model.addAttribute("productTypeList", productService.getProductTypes());
		}
		model.addAttribute("vendorList", vendorService.findAll());
		model.addAttribute("selectedId","");
		model.addAttribute("message", "");
	}


	@Deprecated
	@RequestMapping(value = "savePurchase", method = RequestMethod.POST)
	public String savePurchase(@ModelAttribute PurchaseDTO purchaseDTO, Model model) {
		try {
			if(purchaseItems == null || purchaseItems.size() == 0)
			{
				model.addAttribute("message", "No items found to save this purchase");
			}else {
				purchaseDTO.setPurchaseItems(purchaseItems);
				
				PurchaseDTO dto = purchaseService.saveOrUpatePurchase(purchaseDTO);
				stockService.addToSrock(purchaseItems);
				purchaseDTO = new PurchaseDTO();
				purchaseDTO.setPurchaseItemDTO(new PurchaseItemDTO());
				purchaseItems = new ArrayList<PurchaseItemDTO>();
				model.addAttribute("PurchaseList", purchaseService.findAll());
				model.addAttribute("productItemsList", new ArrayList());
				//model.addAttribute("purchase", new Purchase());
				model.addAttribute("selectedId","");
				model.addAttribute("message", "Succesfully Saved.");
				model.addAttribute("selectedId","");
			}
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "purchaseList";
		}
	    return "purchaseList";
	}
	
	


	private PurchaseDTO getPurchaseDTOById(Long purchaseID) {
		PurchaseDTO purchaseDTO = null;
		if(purchaseID == null || purchaseID.longValue() == 0) {
			purchaseDTO = new PurchaseDTO();
			purchaseDTO.setPurchaseItemDTO(new PurchaseItemDTO());
			purchaseDTO.getPurchaseItemDTO().setSrNo("1");
		}else {
			purchaseDTO = purchaseService.findById(purchaseID);
		}
		
		return purchaseDTO;
	}
	
	
	@RequestMapping(value = "loadPurchase", method = RequestMethod.POST)
	public String loadPurchase(@ModelAttribute("selectedId") Long selectedId, Model model) {
		PurchaseView purchaseView = new PurchaseView(); 
		purchaseView.setSelectedId(selectedId);
		if(purchaseView.getSelectedId() == null || purchaseView.getSelectedId() == 0) {
			purchaseView.setPurchase(new PurchaseDTO());
		} else {
			purchaseView.setPurchase(getPurchaseDTOById(purchaseView.getSelectedId()));
		}
		purchaseView.setSelectedId(0l);
		purchaseView.setSelectedPurchaseItemId(0l);
		purchaseView.setVendorList(vendorService.findAll());
		purchaseView.setProductList(productService.getProductsByBrand(defaultBrandId));
		if(purchaseView.getPurchaseItemDTO() != null && purchaseView.getPurchaseItemDTO().getProductId() != null &&
				purchaseView.getPurchaseItemDTO().getProductId().intValue() > 0)
			purchaseView.setProductItemsList(productItemsService.findAllByProductId(purchaseView.getPurchaseItemDTO().getProductId()));
		model.addAttribute("purchaseView", purchaseView);
		return "purchase";
	}
	
	
	
	@RequestMapping(value = "savePurhcaseItem", method = RequestMethod.POST)
	public String savePurhcaseItem(@ModelAttribute PurchaseView purchaseView, Model model) {
		
		PurchaseDTO purchaseDTO = purchaseService.saveOrUpatePurchase(purchaseView.getPurchase());
		purchaseService.savePurchaseItemAndStock(purchaseView.getPurchaseItemDTO(), purchaseDTO.getId());
		purchaseView.getPurchase().setId(purchaseDTO.getId());
		purchaseService.updatePurchaseTotals(purchaseView.getPurchase());
		
		purchaseView.setSelectedId(0l);
		purchaseView.setSelectedPurchaseItemId(0l);
		purchaseView.setVendorList(vendorService.findAll());
		purchaseView.setProductList(productService.getProductsByBrand(defaultBrandId));
		purchaseView.setPurchaseItemDTO(new PurchaseItemDTO());
		purchaseView.setPurchase( purchaseService.findById(purchaseDTO.getId()));
		
		purchaseView.setMessage("Succesfully saved");
		model.addAttribute("purchaseView", purchaseView);
		
		
		
		return  "purchase";
	}
	
	@RequestMapping(value = "loadPurhcaseItem", method = RequestMethod.POST)
	public String loadPurhcaseItem(@ModelAttribute("selectedPurchaseItemId") Long selectedPurchaseItemId, @ModelAttribute PurchaseView purchaseView, Model model) {
		PurchaseItem purchaseItem = purchaseItemService.getOne(selectedPurchaseItemId);
		
		
		purchaseView.setSelectedId(0l);
		purchaseView.setSelectedPurchaseItemId(0l);
		purchaseView.setVendorList(vendorService.findAll());
		purchaseView.setProductList(productService.getProductsByBrand(defaultBrandId));
		
		
		purchaseView.setPurchaseItemDTO(purchaseService.convertModelToView(purchaseItem));
		purchaseView.setPurchase( purchaseService.findById(purchaseItem.getPurchaseId()));
		purchaseView.setProductItemsList(productItemsService.findAllByProductId(purchaseView.getPurchaseItemDTO().getProductId()));
		model.addAttribute("purchaseView", purchaseView);
		return  "purchase";
	}
	
	@RequestMapping(value = "deletePurchaseItem", method = RequestMethod.POST)
	public String deletePurhcaseItem(@ModelAttribute("selectedPurchaseItemId") Long selectedPurchaseItemId, @ModelAttribute PurchaseView purchaseView, Model model) {
		PurchaseItem purchaseItem = purchaseItemService.getOne(selectedPurchaseItemId);
		Long purchaseItemId = purchaseItem.getPurchaseId();
		purchaseService.deletePurchaseItemUpdateStock(purchaseItem);
		purchaseView.setMessage("Succesfully deleted");
		purchaseView.setPurchase(purchaseService.findById(purchaseItemId));
		purchaseView.setSelectedId(0l);
		purchaseView.setSelectedPurchaseItemId(0l);
		purchaseView.setVendorList(vendorService.findAll());
		purchaseView.setProductList(productService.getProductsByBrand(defaultBrandId));
		if(purchaseView.getPurchaseItemDTO() != null && purchaseView.getPurchaseItemDTO().getProductId() != null &&
				purchaseView.getPurchaseItemDTO().getProductId().intValue() > 0)
		purchaseView.setProductItemsList(productItemsService.findAllByProductId(purchaseView.getPurchaseItemDTO().getProductId()));
		model.addAttribute("purchaseView", purchaseView);
		return  "purchase";
	}
	
	@RequestMapping(value = "purchase", method = RequestMethod.POST)
	public String addPurchase(@ModelAttribute("selectedId") Long selectedId,  Model model) {
		
		try {
			PurchaseDTO purchaseDTO = getPurchaseDTOById(selectedId);
			model.addAttribute("purchase", purchaseDTO);
			purchaseItems = new ArrayList<>(purchaseDTO.getPurchaseItems());
			model.addAttribute("purchaseItems", purchaseItems);
			if(defaultBrandId > 0) {
				model.addAttribute("productList", productService.getProductsByBrand(defaultBrandId));
			} else {
				model.addAttribute("productTypeList", productService.getProductTypes());
			}
			
			model.addAttribute("vendorList", vendorService.findAll());
			model.addAttribute("selectedId","");
			model.addAttribute("message", "");
		} catch (Exception e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "purchase";
		}
		return "purchase";
	}
	
	
	@RequestMapping(value = "deletePurchase", method = RequestMethod.POST)
	public String deletePurchase(@ModelAttribute("selectedId") Long selectedId, Model model) {
	    try {
			/*Purchase purchase = purchaseService.findById(selectedId)
			        .orElseThrow(() -> new ResourceNotFoundException("Note", "selectedId", selectedId));
			purchaseService.delete(purchase);*/
	    	model.addAttribute("vendorList", vendorService.findAll());
	    	if(defaultBrandId > 0) {
				model.addAttribute("productList", productService.getProductsByBrand(defaultBrandId));
			} else {
				model.addAttribute("productTypeList", productService.getProductTypes());
			}
			model.addAttribute("purchaseList", purchaseService.findAll());
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return purchaseList(model);
		}
	    return addPurchase(0l, model);
	}
	
	@Deprecated
	@RequestMapping(value = "deletePurchaseItemssss", method = RequestMethod.POST)
	public String deletePurchaseItem(@ModelAttribute("selectedId") String selectedId, Model model) {
	    try {
	    	Long purchaseItemId = 0l;
	    	String itemCode = "";
	    	Long purhcaseId = 0l;
	    	if(selectedId != null && selectedId.contains("#")) {
	    		String[] vals = selectedId.split("#");
	    		purhcaseId = Long.parseLong(vals[0]);
	    		purchaseItemId = Long.parseLong(vals[1]);
	    		itemCode = vals[2];
	    	}
	    	List<PurchaseItemDTO> purchaseItemsTemp = new ArrayList<PurchaseItemDTO>();
	    	//if(purchaseItemId == 0) {
	    		for(PurchaseItemDTO purchaseItemDTO : purchaseItems) {
	    			if(purchaseItemDTO.getItemCode().equals(itemCode)) {
	    				if(deletedPurchaseItems == null) 
	    					deletedPurchaseItems = new ArrayList<>();
	    				deletedPurchaseItems.add(purchaseItemDTO);
	    				continue;
	    			}
	    			purchaseItemsTemp.add(purchaseItemDTO);
	    		}
	    		purchaseItems = new ArrayList<PurchaseItemDTO>(purchaseItemsTemp);
	    		purchaseService.deletePurchaseItems(deletedPurchaseItems);
	    		stockService.removeFromSrock(deletedPurchaseItems);
	    	//}
	    	deletedPurchaseItems = new ArrayList<>();
	    	model.addAttribute("purchaseItems", purchaseItems);
	    	model.addAttribute("purchase", getPurchaseDTOById(purhcaseId));
	    	addDataToModel(model);
		} catch (ResourceNotFoundException e) {
			model.addAttribute("message", "Error: Something went wrong, please check logs \n Detail: "+e.getClass().toString());
			e.printStackTrace();
			return "purchase";
		}
	    return "purchase";
	}

	public List<PurchaseItemDTO> getDeletedPurchaseItems() {
		return deletedPurchaseItems;
	}

	public void setDeletedPurchaseItems(List<PurchaseItemDTO> deletedPurchaseItems) {
		this.deletedPurchaseItems = deletedPurchaseItems;
	}
	
	
	
}
