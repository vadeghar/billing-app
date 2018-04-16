package com.mytest.billapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "PURCHASE")
@EntityListeners(AuditingEntityListener.class)
public class Purchase  extends BEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "BILL_NO")
	private String billNo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BILL_DATE")
	private Date billDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ENTRY_DATE")
	@CreatedDate
	private Date entryDate;
	
	@Column(name="BILL_TOTAL")
	private Double billTotal;
	
	@Column(name="GST")
	private Integer gst;
	
	@Column(name="DISCOUNT_TYPE")
	private String discountType;
	
	@Column(name="DISCOUNT")
	private Double discount;
	
	@Column(name="NET_TOTAL")
	private Double netTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VENDOR_ID")
	private Vendor vendor;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "purchase")
    private Set<PurchaseItem> purchaseItemSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Double getBillTotal() {
		return billTotal;
	}

	public void setBillTotal(Double billTotal) {
		this.billTotal = billTotal;
	}

	public Integer getGst() {
		return gst;
	}

	public void setGst(Integer gst) {
		this.gst = gst;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Set<PurchaseItem> getPurchaseItemSet() {
		return purchaseItemSet;
	}

	public void setPurchaseItemSet(Set<PurchaseItem> purchaseItemSet) {
		this.purchaseItemSet = purchaseItemSet;
	}
}
