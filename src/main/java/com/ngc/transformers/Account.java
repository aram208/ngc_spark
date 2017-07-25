package com.ngc.transformers;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private String couch_id;
	private String couch_rev;
	private String company;
	private String customerCode;
	private String accountType;
	private String sortIndex;
	private String primaryContactPerson;
	private String termsCode;
	private String creditLimit;
	private String creditBalance;
	private String taxId;
	private String statementCycleCode;
	private String accountCreateDate;
	private String lastOrderDate;
	private String lastPaymentDate;
	private String ictaFreeApplicable;
	private String divisionCode;
	private String hasSignedMasterAgreement;
	private String defaultPriceTableCode;
	private String faxNumber;
	private String holdCode;
	private String preferredShippingMethod;
	private String salesCommissionPayType;
	private String international;
	private String accountTypeModifiedDate;
	private String totalInvoices;
	private String averageDaysPastDue;
	private String ictaRate;
	private String includeInDealerParticipation;
	private String purchaseOrderRequired;
	private String chargeShipping;
	private String accountOwner;
	private String includeInWebParticipation;
	private String statementAgingType;
	private String websiteUrl;
	private String dealerPassword;
	private String ngcNumber;
	private String holdDate;
	private String isIndividual;
	private String discountTable;
	private String currentDiscount;
	private String earnedDiscount;
	private String discountStart;
	private String discountExpiration;
	private String contact_name;
	private String contact_first_name;
	private String contact_last_name;
	private String contact_phone;
	private String contact_email;
	private String contact_address_address1;
	private String contact_address_address2;
	private String contact_address_address3;
	private String contact_address_city;
	private String contact_address_state;
	private String contact_address_zip;
	private String contact_address_country;
	private String notes_note1;
	private String notes_note2;
	private String remarks_invoice;
	private String remarks_order;
	private String remarks_pickTicket;
	private String remarks_statements;
	private String ls_date;
	private String ls_balance;
	private String ls_printFunction;
	private String ls_printDate;
	private String ls_printReportOption;
	private String pref_emailedPreferredLanguage;
	private String pref_ncsOption;
	private String pref_holderAll;
	private String pref_scratch;
	private String pref_prong;
	private String pref_statementType;
	private String pref_imaging;
	private String pref_recap_payment;
	private String pref_recap_grade;
	private String pref_recap_invoice;
	private String pref_recap_shipping;
	private String pref_recap_receiving;
	
	
	public String getCouch_id() {
		return couch_id;
	}
	public void setCouch_id(String couch_id) {
		this.couch_id = couch_id;
	}
	public String getCouch_rev() {
		return couch_rev;
	}
	public void setCouch_rev(String couch_rev) {
		this.couch_rev = couch_rev;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}
	public String getPrimaryContactPerson() {
		return primaryContactPerson;
	}
	public void setPrimaryContactPerson(String primaryContactPerson) {
		this.primaryContactPerson = primaryContactPerson;
	}
	public String getTermsCode() {
		return termsCode;
	}
	public void setTermsCode(String termsCode) {
		this.termsCode = termsCode;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getCreditBalance() {
		return creditBalance;
	}
	public void setCreditBalance(String creditBalance) {
		this.creditBalance = creditBalance;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getStatementCycleCode() {
		return statementCycleCode;
	}
	public void setStatementCycleCode(String statementCycleCode) {
		this.statementCycleCode = statementCycleCode;
	}
	public String getAccountCreateDate() {
		return accountCreateDate;
	}
	public void setAccountCreateDate(String accountCreateDate) {
		this.accountCreateDate = accountCreateDate;
	}
	public String getLastOrderDate() {
		return lastOrderDate;
	}
	public void setLastOrderDate(String lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
	}
	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	public String getIctaFreeApplicable() {
		return ictaFreeApplicable;
	}
	public void setIctaFreeApplicable(String ictaFreeApplicable) {
		this.ictaFreeApplicable = ictaFreeApplicable;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getHasSignedMasterAgreement() {
		return hasSignedMasterAgreement;
	}
	public void setHasSignedMasterAgreement(String hasSignedMasterAgreement) {
		this.hasSignedMasterAgreement = hasSignedMasterAgreement;
	}
	public String getDefaultPriceTableCode() {
		return defaultPriceTableCode;
	}
	public void setDefaultPriceTableCode(String defaultPriceTableCode) {
		this.defaultPriceTableCode = defaultPriceTableCode;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getHoldCode() {
		return holdCode;
	}
	public void setHoldCode(String holdCode) {
		this.holdCode = holdCode;
	}
	public String getPreferredShippingMethod() {
		return preferredShippingMethod;
	}
	public void setPreferredShippingMethod(String preferredShippingMethod) {
		this.preferredShippingMethod = preferredShippingMethod;
	}
	public String getSalesCommissionPayType() {
		return salesCommissionPayType;
	}
	public void setSalesCommissionPayType(String salesCommissionPayType) {
		this.salesCommissionPayType = salesCommissionPayType;
	}
	public String getInternational() {
		return international;
	}
	public void setInternational(String international) {
		this.international = international;
	}
	public String getAccountTypeModifiedDate() {
		return accountTypeModifiedDate;
	}
	public void setAccountTypeModifiedDate(String accountTypeModifiedDate) {
		this.accountTypeModifiedDate = accountTypeModifiedDate;
	}
	public String getTotalInvoices() {
		return totalInvoices;
	}
	public void setTotalInvoices(String totalInvoices) {
		this.totalInvoices = totalInvoices;
	}
	public String getAverageDaysPastDue() {
		return averageDaysPastDue;
	}
	public void setAverageDaysPastDue(String averageDaysPastDue) {
		this.averageDaysPastDue = averageDaysPastDue;
	}
	public String getIctaRate() {
		return ictaRate;
	}
	public void setIctaRate(String ictaRate) {
		this.ictaRate = ictaRate;
	}
	public String getIncludeInDealerParticipation() {
		return includeInDealerParticipation;
	}
	public void setIncludeInDealerParticipation(String includeInDealerParticipation) {
		this.includeInDealerParticipation = includeInDealerParticipation;
	}
	public String getPurchaseOrderRequired() {
		return purchaseOrderRequired;
	}
	public void setPurchaseOrderRequired(String purchaseOrderRequired) {
		this.purchaseOrderRequired = purchaseOrderRequired;
	}
	public String getChargeShipping() {
		return chargeShipping;
	}
	public void setChargeShipping(String chargeShipping) {
		this.chargeShipping = chargeShipping;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	public String getIncludeInWebParticipation() {
		return includeInWebParticipation;
	}
	public void setIncludeInWebParticipation(String includeInWebParticipation) {
		this.includeInWebParticipation = includeInWebParticipation;
	}
	public String getStatementAgingType() {
		return statementAgingType;
	}
	public void setStatementAgingType(String statementAgingType) {
		this.statementAgingType = statementAgingType;
	}
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	public String getDealerPassword() {
		return dealerPassword;
	}
	public void setDealerPassword(String dealerPassword) {
		this.dealerPassword = dealerPassword;
	}
	public String getNgcNumber() {
		return ngcNumber;
	}
	public void setNgcNumber(String ngcNumber) {
		this.ngcNumber = ngcNumber;
	}
	public String getHoldDate() {
		return holdDate;
	}
	public void setHoldDate(String holdDate) {
		this.holdDate = holdDate;
	}
	public String getIsIndividual() {
		return isIndividual;
	}
	public void setIsIndividual(String isIndividual) {
		this.isIndividual = isIndividual;
	}
	public String getDiscountTable() {
		return discountTable;
	}
	public void setDiscountTable(String discountTable) {
		this.discountTable = discountTable;
	}
	public String getCurrentDiscount() {
		return currentDiscount;
	}
	public void setCurrentDiscount(String currentDiscount) {
		this.currentDiscount = currentDiscount;
	}
	public String getEarnedDiscount() {
		return earnedDiscount;
	}
	public void setEarnedDiscount(String earnedDiscount) {
		this.earnedDiscount = earnedDiscount;
	}
	public String getDiscountStart() {
		return discountStart;
	}
	public void setDiscountStart(String discountStart) {
		this.discountStart = discountStart;
	}
	public String getDiscountExpiration() {
		return discountExpiration;
	}
	public void setDiscountExpiration(String discountExpiration) {
		this.discountExpiration = discountExpiration;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getContact_first_name() {
		return contact_first_name;
	}
	public void setContact_first_name(String contact_first_name) {
		this.contact_first_name = contact_first_name;
	}
	public String getContact_last_name() {
		return contact_last_name;
	}
	public void setContact_last_name(String contact_last_name) {
		this.contact_last_name = contact_last_name;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getContact_address_address1() {
		return contact_address_address1;
	}
	public void setContact_address_address1(String contact_address_address1) {
		this.contact_address_address1 = contact_address_address1;
	}
	public String getContact_address_address2() {
		return contact_address_address2;
	}
	public void setContact_address_address2(String contact_address_address2) {
		this.contact_address_address2 = contact_address_address2;
	}
	public String getContact_address_address3() {
		return contact_address_address3;
	}
	public void setContact_address_address3(String contact_address_address3) {
		this.contact_address_address3 = contact_address_address3;
	}
	public String getContact_address_city() {
		return contact_address_city;
	}
	public void setContact_address_city(String contact_address_city) {
		this.contact_address_city = contact_address_city;
	}
	public String getContact_address_state() {
		return contact_address_state;
	}
	public void setContact_address_state(String contact_address_state) {
		this.contact_address_state = contact_address_state;
	}
	public String getContact_address_zip() {
		return contact_address_zip;
	}
	public void setContact_address_zip(String contact_address_zip) {
		this.contact_address_zip = contact_address_zip;
	}
	public String getContact_address_country() {
		return contact_address_country;
	}
	public void setContact_address_country(String contact_address_country) {
		this.contact_address_country = contact_address_country;
	}
	public String getNotes_note1() {
		return notes_note1;
	}
	public void setNotes_note1(String notes_note1) {
		this.notes_note1 = notes_note1;
	}
	public String getNotes_note2() {
		return notes_note2;
	}
	public void setNotes_note2(String notes_note2) {
		this.notes_note2 = notes_note2;
	}
	public String getRemarks_invoice() {
		return remarks_invoice;
	}
	public void setRemarks_invoice(String remarks_invoice) {
		this.remarks_invoice = remarks_invoice;
	}
	public String getRemarks_order() {
		return remarks_order;
	}
	public void setRemarks_order(String remarks_order) {
		this.remarks_order = remarks_order;
	}
	public String getRemarks_pickTicket() {
		return remarks_pickTicket;
	}
	public void setRemarks_pickTicket(String remarks_pickTicket) {
		this.remarks_pickTicket = remarks_pickTicket;
	}
	public String getRemarks_statements() {
		return remarks_statements;
	}
	public void setRemarks_statements(String remarks_statements) {
		this.remarks_statements = remarks_statements;
	}
	public String getLs_date() {
		return ls_date;
	}
	public void setLs_date(String ls_date) {
		this.ls_date = ls_date;
	}
	public String getLs_balance() {
		return ls_balance;
	}
	public void setLs_balance(String ls_balance) {
		this.ls_balance = ls_balance;
	}
	public String getLs_printFunction() {
		return ls_printFunction;
	}
	public void setLs_printFunction(String ls_printFunction) {
		this.ls_printFunction = ls_printFunction;
	}
	public String getLs_printDate() {
		return ls_printDate;
	}
	public void setLs_printDate(String ls_printDate) {
		this.ls_printDate = ls_printDate;
	}
	public String getLs_printReportOption() {
		return ls_printReportOption;
	}
	public void setLs_printReportOption(String ls_printReportOption) {
		this.ls_printReportOption = ls_printReportOption;
	}
	public String getPref_emailedPreferredLanguage() {
		return pref_emailedPreferredLanguage;
	}
	public void setPref_emailedPreferredLanguage(String pref_emailedPreferredLanguage) {
		this.pref_emailedPreferredLanguage = pref_emailedPreferredLanguage;
	}
	public String getPref_ncsOption() {
		return pref_ncsOption;
	}
	public void setPref_ncsOption(String pref_ncsOption) {
		this.pref_ncsOption = pref_ncsOption;
	}
	public String getPref_holderAll() {
		return pref_holderAll;
	}
	public void setPref_holderAll(String pref_holderAll) {
		this.pref_holderAll = pref_holderAll;
	}
	public String getPref_scratch() {
		return pref_scratch;
	}
	public void setPref_scratch(String pref_scratch) {
		this.pref_scratch = pref_scratch;
	}
	public String getPref_prong() {
		return pref_prong;
	}
	public void setPref_prong(String pref_prong) {
		this.pref_prong = pref_prong;
	}
	public String getPref_statementType() {
		return pref_statementType;
	}
	public void setPref_statementType(String pref_statementType) {
		this.pref_statementType = pref_statementType;
	}
	public String getPref_imaging() {
		return pref_imaging;
	}
	public void setPref_imaging(String pref_imaging) {
		this.pref_imaging = pref_imaging;
	}
	public String getPref_recap_payment() {
		return pref_recap_payment;
	}
	public void setPref_recap_payment(String pref_recap_payment) {
		this.pref_recap_payment = pref_recap_payment;
	}
	public String getPref_recap_grade() {
		return pref_recap_grade;
	}
	public void setPref_recap_grade(String pref_recap_grade) {
		this.pref_recap_grade = pref_recap_grade;
	}
	public String getPref_recap_invoice() {
		return pref_recap_invoice;
	}
	public void setPref_recap_invoice(String pref_recap_invoice) {
		this.pref_recap_invoice = pref_recap_invoice;
	}
	public String getPref_recap_shipping() {
		return pref_recap_shipping;
	}
	public void setPref_recap_shipping(String pref_recap_shipping) {
		this.pref_recap_shipping = pref_recap_shipping;
	}
	public String getPref_recap_receiving() {
		return pref_recap_receiving;
	}
	public void setPref_recap_receiving(String pref_recap_receiving) {
		this.pref_recap_receiving = pref_recap_receiving;
	}

}
