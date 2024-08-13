package com.integrosys.cms.ui.caseBranch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.context.BeanHouse;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.techinfra.propertyfile.PropertyManager;
import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.cms.app.caseBranch.bus.CaseBranchException;
import com.integrosys.cms.app.caseBranch.bus.ICaseBranch;
import com.integrosys.cms.app.caseBranch.bus.OBCaseBranch;
import com.integrosys.cms.app.caseBranch.proxy.ICaseBranchProxyManager;
import com.integrosys.cms.app.geography.city.proxy.ICityProxyManager;
import com.integrosys.cms.app.geography.country.bus.ICountry;
import com.integrosys.cms.app.geography.region.bus.IRegion;
import com.integrosys.cms.app.geography.state.bus.IState;
import com.integrosys.cms.app.relationshipmgr.proxy.IRelationshipMgrProxyManager;
import com.integrosys.cms.app.systemBank.bus.ISystemBank;
import com.integrosys.cms.app.systemBank.bus.SystemBankException;
import com.integrosys.cms.app.systemBankBranch.bus.ISystemBankBranch;
import com.integrosys.cms.app.systemBankBranch.proxy.ISystemBankBranchProxyManager;
import com.integrosys.cms.app.systemBankBranch.proxy.SystemBankBranchProxyManagerImpl;
import com.integrosys.cms.app.systemBankBranch.trx.OBSystemBankBranchTrxValue;
import com.integrosys.cms.ui.collateral.CategoryCodeConstant;
import com.integrosys.cms.ui.common.CommonCodeList;
import com.integrosys.cms.ui.common.constant.IGlobalConstant;
import com.integrosys.cms.ui.login.CMSGlobalSessionConstant;
import com.integrosys.cms.ui.manualinput.CommonUtil;
import com.integrosys.component.commondata.app.CommonDataSingleton;

/**
 $Author: Abhijit R $
 
 */
public class ListOptionsCmd extends AbstractCommand implements ICommonEventConstant {

	/**
	 * Default Constructor
	 */
	
	private ISystemBankBranchProxyManager systemBankBranchProxy;

	public ISystemBankBranchProxyManager getSystemBankBranchProxy() {
		return systemBankBranchProxy;
	}

	public void setSystemBankBranchProxy(ISystemBankBranchProxyManager systemBankBranchProxy) {
		this.systemBankBranchProxy = systemBankBranchProxy;
	}

	
	public ListOptionsCmd() {
		
	}
	
	public String[][] getParameterDescriptor() {
		return new String[][] {
				
				{"event", "java.lang.String", REQUEST_SCOPE},
				
			};
	}
	public String[][] getResultDescriptor() {
		return (new String[][] { 
				
				{ "branchList", "java.util.List", SERVICE_SCOPE },
				
			});
	}

	/**
	 * This method does the Business operations with the HashMap and put the
	 * results back into the HashMap.Here creation for Company Borrower is done.
	 * 
	 * @param map is of type HashMap
	 * @return HashMap with the Result
	 */
	public HashMap doExecute(HashMap map) throws CommandProcessingException, CommandValidationException {
		
		HashMap returnMap = new HashMap();
		HashMap resultMap = new HashMap();
		List systemBankList= null;
		ISystemBank systemBank= null;
		String event = (String) map.get("event");
		
		if(event.equalsIgnoreCase("maker_prepare_create_caseBranch")){
			try {
			
			ICaseBranchProxyManager caseBranchProxy =(ICaseBranchProxyManager) BeanHouse.get("caseBranchProxy");
			SearchResult caseBranchList = new SearchResult();
        	HashMap caseBranchesMap=new HashMap();
        	
            
				caseBranchList= (SearchResult)  caseBranchProxy.getAllActualCaseBranch();
			
				
			if(caseBranchList!=null){
				if(caseBranchList.getResultList()!=null){
					ArrayList arrayList=(ArrayList)caseBranchList.getResultList();
				for(int i=0;i<arrayList.size();i++){
					OBCaseBranch caseBranch=(OBCaseBranch)arrayList.get(i);
					caseBranchesMap.put(caseBranch.getBranchCode(), caseBranch);
					
				}
				
				}
			}
			
			resultMap.put("branchList", getBranchList(caseBranchesMap));
            } catch (CaseBranchException e) {
				e.printStackTrace();
			} catch (TrxParameterException e) {
				e.printStackTrace();
			} catch (TransactionException e) {
				e.printStackTrace();
			}
			
			
		}else{
		resultMap.put("branchList", getBranchList());
		}
		returnMap.put(ICommonEventConstant.COMMAND_RESULT_MAP, resultMap);
		return returnMap;
	}
	
	
	private List getBranchList() {
		List lbValList = new ArrayList();
		try {
			
			 SearchResult searchResult = getSystemBankBranchProxy().getAllActualBranch();
			 List idList = (List) searchResult.getResultList();
			for (int i = 0; i < idList.size(); i++) {
				ISystemBankBranch bankBranch = (ISystemBankBranch) idList.get(i);
				String id = bankBranch.getSystemBankBranchCode();
				String val = bankBranch.getSystemBankBranchName();
				String value=id+" ("+val + ")";
				LabelValueBean lvBean = new LabelValueBean(value, id);
				lbValList.add(lvBean);
			}
		} catch (Exception ex) {
		}
		return (List) CommonUtil.sortDropdown(lbValList);
	}
	
	private List getBranchList(HashMap caseBranchesMap) {
		List lbValList = new ArrayList();
		try {
			
			 SearchResult searchResult = getSystemBankBranchProxy().getAllActualBranch();
			 List idList = (List) searchResult.getResultList();
			for (int i = 0; i < idList.size(); i++) {
				
				ISystemBankBranch bankBranch = (ISystemBankBranch) idList.get(i);
				if(!caseBranchesMap.containsKey(bankBranch.getSystemBankBranchCode())){
				String id = bankBranch.getSystemBankBranchCode();
				String val = bankBranch.getSystemBankBranchName();
				String value=id+" ("+val + ")";
				LabelValueBean lvBean = new LabelValueBean(value, id);
				lbValList.add(lvBean);
				}
			}
		} catch (Exception ex) {
		}
		return (List) CommonUtil.sortDropdown(lbValList);
	}
	
}