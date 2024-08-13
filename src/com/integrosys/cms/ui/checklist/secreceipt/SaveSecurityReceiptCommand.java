/**
 * Copyright Integro Technologies Pte Ltd
 * $Header:
 */
package com.integrosys.cms.ui.checklist.secreceipt;

import java.util.HashMap;

import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.cms.app.checklist.bus.ICheckList;
import com.integrosys.cms.app.checklist.bus.ICheckListItem;
import com.integrosys.cms.app.checklist.proxy.CheckListProxyManagerFactory;
import com.integrosys.cms.app.checklist.proxy.ICheckListProxyManager;
import com.integrosys.cms.app.checklist.trx.ICheckListTrxValue;
import com.integrosys.cms.app.transaction.OBTrxContext;
import com.integrosys.cms.ui.checklist.CheckListUtil;

/**
 * @author $Author: visveswari $<br>
 * @version $Revision: 1.5 $
 * @since $Date: 2004/05/10 02:29:16 $ Tag: $Name: $
 */
public class SaveSecurityReceiptCommand extends AbstractCommand implements ICommonEventConstant {
	/**
	 * Default Constructor
	 */
	public SaveSecurityReceiptCommand() {
	}

	/**
	 * Defines an two dimensional array with the result list to be expected as a
	 * result from the doExecute method using a HashMap syntax for the array is
	 * (HashMapkey,classname,scope) The scope may be request,form or service
	 * 
	 * @return the two dimensional String array
	 */
	public String[][] getParameterDescriptor() {
		return (new String[][] {
				{ "checkListTrxVal", "com.integrosys.cms.app.checklist.trx.ICheckListTrxValue", SERVICE_SCOPE },
				{ "checkListForm", "com.integrosys.cms.app.checklist.bus.OBCheckList" ,FORM_SCOPE},
				{ "theOBTrxContext", "com.integrosys.cms.app.transaction.OBTrxContext", FORM_SCOPE } });
	}

	/**
	 * Defines an two dimensional array with the result list to be expected as a
	 * result from the doExecute method using a HashMap syntax for the array is
	 * (HashMapkey,classname,scope) The scope may be request,form or service
	 * 
	 * @return the two dimensional String array
	 */
	public String[][] getResultDescriptor() {
		return (new String[][] {
				{ "checkListTrxVal", "com.integrosys.cms.app.checklist.trx.ICheckListTrxValue", SERVICE_SCOPE },
				{ "request.ITrxValue", "com.integrosys.cms.app.transaction.ICMSTrxValue", REQUEST_SCOPE } });
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
		DefaultLogger.debug(this, "Inside doExecute()");
		try {
			ICheckListTrxValue checkListTrxVal = (ICheckListTrxValue) map.get("checkListTrxVal");
			ICheckList checkList = (ICheckList) map.get("checkListForm");
			
			String secImageTagNames = "";
			String checklistItemRefNumber = "";
			String docDesc = "";
			String facilityId = "";
			for(ICheckListItem item : checkList.getCheckListItemList()) {
//				if(item.getSecImageTagUntagImgName() != null && !"".equals(item.getSecImageTagUntagImgName())) {
//					secImageTagNames = item.getSecImageTagUntagImgName();
//					checklistItemRefNumber = String.valueOf(item.getCheckListItemRef());
//					break;
//				}
				
				if(item.getSecImageTagUntagStatus() != null && !"".equals(item.getSecImageTagUntagStatus())) {
					secImageTagNames = item.getSecImageTagUntagImgName();
					checklistItemRefNumber = String.valueOf(item.getCheckListItemRef());
					docDesc = item.getSecImageTagUntagId();
					item.setSecImageTagUntagId(null);
					item.setSecImageTagUntagStatus(null);
				}
				
			}
			
			DefaultLogger.debug(this, "checkList in submit --->" + checkList);
			OBTrxContext ctx = (OBTrxContext) map.get("theOBTrxContext");
//			ctx.setTrxCountryOrigin(CheckListUtil.getColTrxCountry(checkListTrxVal.getStagingCheckList()));
			ICheckListProxyManager proxy = CheckListProxyManagerFactory.getCheckListProxyManager();
			DefaultLogger.debug(this, ">>>>>>>>>>>>>>>>>>>>>Updating" + checkListTrxVal);
			checkListTrxVal = proxy.makerSaveCheckList(ctx, checkListTrxVal, checkList);
			resultMap.put("checkListTrxVal", checkListTrxVal);
			resultMap.put("request.ITrxValue", checkListTrxVal);
		}
		catch (Exception e) {
			DefaultLogger.debug(this, "got exception in doExecute" + e);
			e.printStackTrace();
			throw (new CommandProcessingException(e.getMessage()));
		}
		DefaultLogger.debug(this, "Going out of doExecute()");
		returnMap.put(ICommonEventConstant.COMMAND_RESULT_MAP, resultMap);
		return returnMap;
	}

}
