/**
 * Copyright Integro Technologies Pte Ltd
 * $Header:
 */
package com.integrosys.cms.ui.checklist.ccreceipt;

import java.util.HashMap;

import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.techinfra.util.DateUtil;
import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.cms.app.checklist.bus.ICheckList;
import com.integrosys.cms.app.checklist.bus.ICheckListItem;
import com.integrosys.cms.app.checklist.proxy.CheckListProxyManagerFactory;
import com.integrosys.cms.app.checklist.proxy.ICheckListProxyManager;

/**
 * @author $Author: vishal $<br>
 * @version $Revision: 1.1 $
 * @since $Date: 2005/09/23 10:24:48 $ Tag: $Name: $
 */
public class SaveUpdateNarrationCheckListItemCommand extends AbstractCommand implements ICommonEventConstant {
	/**
	 * Default Constructor
	 */
	public SaveUpdateNarrationCheckListItemCommand() {
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
				{ "checkListItem", "com.integrosys.cms.app.checklist.bus.ICheckListItem", FORM_SCOPE },
				{ "checkList", "com.integrosys.cms.app.checklist.bus.ICheckList", SERVICE_SCOPE },
				{ "index", "java.lang.String", REQUEST_SCOPE },
				{ "actionTypeName", "java.lang.String", REQUEST_SCOPE },

		});
	}

	/**
	 * Defines an two dimensional array with the result list to be expected as a
	 * result from the doExecute method using a HashMap syntax for the array is
	 * (HashMapkey,classname,scope) The scope may be request,form or service
	 * 
	 * @return the two dimensional String array
	 */
	public String[][] getResultDescriptor() {
		return (new String[][] { { "checkList", "com.integrosys.cms.app.checklist.bus.ICheckList", SERVICE_SCOPE }, });
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
			ICheckList checkList = (ICheckList) map.get("checkList");
			ICheckListItem checkListItem = (ICheckListItem) map.get("checkListItem");
			int index = Integer.parseInt((String) map.get("index"));
			String actionTypeName = (String) map.get("actionTypeName");
			DefaultLogger.debug(this, "actionTypeName" + actionTypeName);
			ICheckListItem temp[] = checkList.getCheckListItemList();
			ICheckListProxyManager proxy = CheckListProxyManagerFactory.getCheckListProxyManager();
			// DefaultLogger.debug(this,
			// "<<<<<< item status: "+temp[index].getItemStatus
			// ()+"\titem cust status: "+temp[index].getCustodianDocStatus());
			// HashMap statusMap =
			// proxy.getNextCheckListItemState(temp[index].getItemStatus
			// (),temp[index].getCustodianDocStatus(),actionTypeName);
			// checkListItem.setItemStatus((String)statusMap.get(ICMSConstant.
			// CHECKLIST_ITEM_STATE));
			// checkListItem.setCustodianDocStatus((String)statusMap.get(
			// ICMSConstant.CUSTODIAN_STATE));
			// CR CMS-382
			checkListItem.setLastUpdateDate(DateUtil.getDate());
			temp[index] = checkListItem;
			DefaultLogger.debug(this, " Narration Remarks ????? " + checkListItem.getRemarks());
			resultMap.put("checkList", checkList);
			// DefaultLogger.debug(this,"after save "+checkListItem);
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
