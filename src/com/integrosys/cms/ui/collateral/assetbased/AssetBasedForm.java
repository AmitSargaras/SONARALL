//GENERATED FILE... ANYMODIFICATION WILL BE LOST. ASK SATHISH FOR ANY CLARIFICATIONpackage com.integrosys.cms.ui.collateral.assetbased;import java.io.Serializable;import com.integrosys.cms.ui.collateral.CollateralForm;/** * Created by IntelliJ IDEA. User: ssathish Date: Jun 20, 2003 Time: 2:56:38 PM * To change this template use Options | File Templates. */public abstract class AssetBasedForm extends CollateralForm implements Serializable {	private String[] deleteItem;	private String physInspFreq = "";	private String physInspFreqUOM = "";	private String datePhyInspec = "";	private String nextPhysInspDate = "";	private String nominalValue = "";	private String assetType = "";	private String description = "";	private String[] deleteInsItem;	private String typeOfInvoice = "";	// moved to CollateralForm	// private String secEnvRisky = "";	// private String dateSecurityEnv = "";	private String remarkEnvRisk = "";	private String isPhysInsp = "";	private String svsInsurName;	private String svsFlagReg;	private String assetTypeHostRef;		private String chattelSoldDate = "";		private String rlSerialNumber = "";		private String pubTransport = "";	private String repossessionDate;	private String repossessionAge;		private String chequeDate = "";		private String chequeRefNumber = "";		private String interestRate = "";		private String priCaveatGuaranteeDate = "";	private String remarks = "";		private String brand = "";	private String modelNo = "";		private String goodStatus;		private String scrapValue;		private String assetValue;		public String getScrapValue() {		return scrapValue;	}	public void setScrapValue(String scrapValue) {		this.scrapValue = scrapValue;	}		public String getBrand() {		return brand;	}	public void setBrand(String brand) {		this.brand = brand;	}	public String getModelNo() {		return modelNo;	}	public void setModelNo(String modelNo) {		this.modelNo = modelNo;	}	public String getRemarks() {		return remarks;	}	public void setRemarks(String remarks) {		this.remarks = remarks;	}	public String getPriCaveatGuaranteeDate() {		return priCaveatGuaranteeDate;	}	public void setPriCaveatGuaranteeDate(String priCaveatGuaranteeDate) {		this.priCaveatGuaranteeDate = priCaveatGuaranteeDate;	}	public String getChequeDate() {		return chequeDate;	}	public void setChequeDate(String chequeDate) {		this.chequeDate = chequeDate;	}	public String getChequeRefNumber() {		return chequeRefNumber;	}	public void setChequeRefNumber(String chequeRefNumber) {		this.chequeRefNumber = chequeRefNumber;	}	public String getInterestRate() {		return interestRate;	}	public void setInterestRate(String interestRate) {		this.interestRate = interestRate;	}	public String getPubTransport() {		return pubTransport;	}	public void setPubTransport(String pubTransport) {		this.pubTransport = pubTransport;	}	public String getSvsInsurName() {		return svsInsurName;	}	public void setSvsInsurName(String svsInsurName) {		this.svsInsurName = svsInsurName;	}	public String getSvsFlagReg() {		return svsFlagReg;	}	public void setSvsFlagReg(String svsFlagReg) {		this.svsFlagReg = svsFlagReg;	}	public String[] getDeleteItem() {		return this.deleteItem;	}	public void setDeleteItem(String[] deleteItem) {		this.deleteItem = deleteItem;	}	public String[] getDeleteInsItem() {		return this.deleteInsItem;	}	public void setDeleteInsItem(String[] deleteInsItem) {		this.deleteInsItem = deleteInsItem;	}	public String getTypeOfInvoice() {		return typeOfInvoice;	}	public void setTypeOfInvoice(String typeOfInvoice) {		this.typeOfInvoice = typeOfInvoice;	}	/*	 * public String getSecEnvRisky() { return this.secEnvRisky; }	 * 	 * public void setSecEnvRisky(String secEnvRisky) { this.secEnvRisky =	 * secEnvRisky; }	 */	public String getRemarkEnvRisk() {		return this.remarkEnvRisk;	}	public void setRemarkEnvRisk(String remarkEnvRisk) {		this.remarkEnvRisk = remarkEnvRisk;	}	/*	 * public String getDateSecurityEnv() { return this.dateSecurityEnv; }	 * 	 * public void setDateSecurityEnv(String dateSecurityEnv) {	 * this.dateSecurityEnv = dateSecurityEnv; }	 */	public String getIsPhysInsp() {		return this.isPhysInsp;	}	public void setIsPhysInsp(String isPhysInsp) {		this.isPhysInsp = isPhysInsp;	}	public String getPhysInspFreq() {		return this.physInspFreq;	}	public void setPhysInspFreq(String physInspFreq) {		this.physInspFreq = physInspFreq;	}	public String getPhysInspFreqUOM() {		return this.physInspFreqUOM;	}	public void setPhysInspFreqUOM(String physInspFreqUOM) {		if (null != physInspFreqUOM) {			physInspFreqUOM = physInspFreqUOM.trim();		}		this.physInspFreqUOM = physInspFreqUOM;	}	public String getDatePhyInspec() {		return this.datePhyInspec;	}	public void setDatePhyInspec(String datePhyInspec) {		this.datePhyInspec = datePhyInspec;	}	public String getNextPhysInspDate() {		return this.nextPhysInspDate;	}	public void setNextPhysInspDate(String nextPhysInspDate) {		this.nextPhysInspDate = nextPhysInspDate;	}	public String getNominalValue() {		return this.nominalValue;	}	public void setNominalValue(String nominalValue) {		this.nominalValue = nominalValue;	}	public String getAssetType() {		return this.assetType;	}	public void setAssetType(String assetType) {		this.assetType = assetType;	}	public String getAssetTypeHostRef() {		return this.assetTypeHostRef;	}	public void setAssetTypeHostRef(String assetTypeHostRef) {		this.assetTypeHostRef = assetTypeHostRef;	}	public String getDescription() {		return this.description;	}	public void setDescription(String description) {		this.description = description;	}	public String getGoodStatus() {		return goodStatus;	}	public void setGoodStatus(String goodStatus) {		if (null != goodStatus) {			goodStatus = goodStatus.trim();		}		this.goodStatus = goodStatus;	}	public void reset() {	}	public String getChattelSoldDate() {		return chattelSoldDate;	}	public void setChattelSoldDate(String chattelSoldDate) {		this.chattelSoldDate = chattelSoldDate;	}	public String getRlSerialNumber() {		return rlSerialNumber;	}	public void setRlSerialNumber(String rlSerialNumber) {		this.rlSerialNumber = rlSerialNumber;	}	public String getRepossessionDate() {		return repossessionDate;	}	public void setRepossessionDate(String repossessionDate) {		this.repossessionDate = repossessionDate;	}	public String getRepossessionAge() {		return repossessionAge;	}	public void setRepossessionAge(String repossessionAge) {		this.repossessionAge = repossessionAge;	}	 	public String getAssetValue() {		return assetValue;	}	public void setAssetValue(String assetValue) {		this.assetValue = assetValue;	}}