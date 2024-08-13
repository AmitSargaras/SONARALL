//STUB GENERATED....CHANGE THIS FILE AS YOU FEEL
package com.integrosys.cms.ui.collateral.assetbased.assetrecspecagent;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import com.integrosys.base.businfra.currency.Amount;
import com.integrosys.base.businfra.currency.CurrencyManager;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.techinfra.util.DateUtil;
import com.integrosys.base.uiinfra.common.AbstractCommonMapper;
import com.integrosys.base.uiinfra.common.CommonForm;
import com.integrosys.base.uiinfra.exception.MapperException;
import com.integrosys.cms.app.collateral.bus.type.asset.subtype.recspecagent.IReceivableSpecificAgent;
import com.integrosys.cms.app.collateral.trx.ICollateralTrxValue;
import com.integrosys.cms.ui.collateral.CollateralMapper;

/**
 * Created by IntelliJ IDEA. User: ssathish Date: Jun 22, 2003 Time: 4:45:05 PM
 * To change this template use Options | File Templates.
 */
public class AssetRecSpecAgentMapperHelper {

	public static Object mapFormToOB(CommonForm cForm, HashMap inputs, Object obj) throws MapperException {
		AssetRecSpecAgentForm aForm = (AssetRecSpecAgentForm) cForm;
		IReceivableSpecificAgent iObj = (IReceivableSpecificAgent) obj;
		Locale locale = (Locale) inputs.get(com.integrosys.base.uiinfra.common.Constants.GLOBAL_LOCALE_KEY);
		DefaultLogger.debug("AssetRecSpecGenMapperHelper - mapFormToOB", "Locale is: " + locale);
		Date stageDate;
		try {
			iObj.setAgentName((aForm.getAgentName()));
			iObj.setAgentLocation((aForm.getLocAgent().trim()));
			iObj.setOwnAccNo(aForm.getProceedRecToBank());
			if (!AbstractCommonMapper.isEmptyOrNull(aForm.getProceedRecControlledBank())) {
				// fix for CMSSP-480: previously Boolean.getBoolean(), please
				// refer to Java API.
				iObj.setIsOwnProceedsOfReceivables((new Boolean(aForm.getProceedRecControlledBank())).booleanValue());
			}
			iObj.setOwnAccNoLocation((aForm.getBankACLoc().trim()));
			iObj.setAgentBankReceivables((aForm.getProceedRecControlledAgent()));
			iObj.setAgentBankLocation((aForm.getLocAgentBank().trim()));
			iObj.setApprovedBuyer(aForm.getApprovedBuyer());
			iObj.setApprovedBuyerLocation(aForm.getLocApprBuyer().trim());
			if (aForm.getNominalValue().equals("")) {
				iObj.setNominalValue(null);
			}
			else {
				iObj.setNominalValue(CurrencyManager.convertToAmount(locale, iObj.getCurrencyCode(), aForm
						.getNominalValue()));
			}

			iObj.setInvoiceType(aForm.getTypeOfInvoice());

			// new addition for gcms
			iObj.setProjectName(((aForm.getProjectName())));
			iObj.setBlanketAssignment(((aForm.getBlanketAssignment())));

			if (!AbstractCommonMapper.isEmptyOrNull(aForm.getDateAwarded())) {
				stageDate = CollateralMapper.compareDate(locale, iObj.getDateAwarded(), aForm.getDateAwarded());
				iObj.setDateAwarded(stageDate);
			}
			else {
				iObj.setDateAwarded(null);
			}

			if (!AbstractCommonMapper.isEmptyOrNull(aForm.getLetterInstructFlag())) {
				iObj.setLetterInstructFlag((new Boolean(aForm.getLetterInstructFlag())).booleanValue());
			}

			if (!AbstractCommonMapper.isEmptyOrNull(aForm.getLetterUndertakeFlag())) {
				iObj.setLetterUndertakeFlag((new Boolean(aForm.getLetterUndertakeFlag())).booleanValue());
			}
			
			iObj.setRemarks(aForm.getRemarks());
		}
		catch (Exception e) {
			DefaultLogger.debug("----com.integrosys.cms.ui.collateral.assetbased.AssetRecSpecAgentMapperHelper",
					"error is :" + e.toString());
			throw new MapperException(e.getMessage());
		}

		return iObj;
	}

	public static CommonForm mapOBToForm(CommonForm cForm, Object obj, HashMap inputs) throws MapperException {
		AssetRecSpecAgentForm aForm = (AssetRecSpecAgentForm) cForm;
		IReceivableSpecificAgent iObj = (IReceivableSpecificAgent) obj;
		Locale locale = (Locale) inputs.get(com.integrosys.base.uiinfra.common.Constants.GLOBAL_LOCALE_KEY);
		DefaultLogger.debug("AssetRecspecAgentMapperHelper - mapOBToForm", "Locale is: " + locale);
		Amount amt;
		try {
			if (iObj.getIsOwnProceedsOfReceivables()) {
				aForm.setProceedRecControlledBank("true");
			}
			else {
				aForm.setProceedRecControlledBank("false");
			}

			aForm.setProceedRecToBank(iObj.getOwnAccNo());
			if (iObj.getOwnAccNoLocation() != null) {
				aForm.setBankACLoc(iObj.getOwnAccNoLocation().trim());
			}
			aForm.setAgentName(iObj.getAgentName());
			if (iObj.getAgentLocation() != null) {
				aForm.setLocAgent(iObj.getAgentLocation().trim());
			}
			aForm.setApprovedBuyer(iObj.getApprovedBuyer());
			if (iObj.getApprovedBuyerLocation() != null) {
				aForm.setLocApprBuyer(iObj.getApprovedBuyerLocation().trim());
			}
			if (iObj.getAgentBankLocation() != null) {
				aForm.setLocAgentBank(iObj.getAgentBankLocation().trim());
			}
			if (iObj.getAgentBankReceivables() != null) {
				aForm.setProceedRecControlledAgent(iObj.getAgentBankReceivables());
			}
			aForm.setTypeOfInvoice(iObj.getInvoiceType());
			amt = iObj.getNominalValue();
			if ((amt != null) && (amt.getCurrencyCode() != null)) {
				aForm.setNominalValue(CurrencyManager.convertToString(locale, amt));
			}

			// new addition for gcms
			aForm.setProjectName(((iObj.getProjectName())));
			aForm.setBlanketAssignment(((iObj.getBlanketAssignment())));
			aForm.setDateAwarded(DateUtil.formatDate(locale, iObj.getDateAwarded()));

			if (iObj.getLetterInstructFlag()) {
				aForm.setLetterInstructFlag("true");
			}
			else {
				aForm.setProceedRecControlledBank("false");
			}

			if (iObj.getLetterUndertakeFlag()) {
				aForm.setLetterUndertakeFlag("true");
			}
			else {
				aForm.setLetterUndertakeFlag("false");
			}
			
			aForm.setRemarks(iObj.getRemarks());
		}
		catch (Exception e) {
			DefaultLogger.debug("com.integrosys.cms.ui.collateral.assetbased.AssetRecSpecAgentMapperHelper",
					"error is :" + e.toString());
			throw new MapperException(e.getMessage());
		}
		return aForm;
	}

	public static Object getObject(HashMap inputs) {
		return ((IReceivableSpecificAgent) ((ICollateralTrxValue) inputs.get("serviceColObj")).getStagingCollateral());

	}

}
