/**
 * Copyright Integro Technologies Pte Ltd
 * $Header:
 */
package com.integrosys.cms.ui.facmaster;

import java.util.ArrayList;
import java.util.HashMap;

import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.chktemplate.bus.ITemplate;
import com.integrosys.cms.app.chktemplate.bus.ITemplateItem;
import com.integrosys.component.commondata.app.CommonDataSingleton;

/**
 * @author $Author: hltan $<br>
 * @version $Revision: 1.2 $
 * @since $Date: 2003/10/28 08:58:58 $ Tag: $Name: $
 */
public class EditTemplateItemCommand extends AbstractCommand implements ICommonEventConstant {
	/**
	 * Default Constructor
	 */
	public EditTemplateItemCommand() {
	}

	/**
	 * Defines an two dimensional array with the result list to be expected as a
	 * result from the doExecute method using a HashMap syntax for the array is
	 * (HashMapkey,classname,scope) The scope may be request,form or service
	 * 
	 * @return the two dimensional String array
	 */
	public String[][] getParameterDescriptor() {
		return (new String[][] { { "index", "java.lang.String", REQUEST_SCOPE },
				{ "template", "com.integrosys.cms.app.chktemplate.bus.ITemplate", SERVICE_SCOPE }, });
	}

	/**
	 * Defines an two dimensional array with the result list to be expected as a
	 * result from the doExecute method using a HashMap syntax for the array is
	 * (HashMapkey,classname,scope) The scope may be request,form or service
	 * 
	 * @return the two dimensional String array
	 */
	public String[][] getResultDescriptor() {
		return (new String[][] { { "index", "java.lang.String", REQUEST_SCOPE },
				{ "templateItem", "com.integrosys.cms.app.chktemplate.bus.ITemplateItem", FORM_SCOPE },
				{ "monitorTypeLabels", "java.util.Collection", REQUEST_SCOPE },
				{ "monitorTypeValues", "java.util.Collection", REQUEST_SCOPE }, });
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

			ITemplate temp = (ITemplate) map.get("template");
			ITemplateItem[] list = temp.getTemplateItemList();
			int index = Integer.parseInt((String) map.get("index"));
			resultMap.put("templateItem", list[index]);
			resultMap.put("index", map.get("index"));

			ArrayList monitorTypeLabels = (ArrayList) CommonDataSingleton
					.getCodeCategoryLabels(ICMSConstant.CATEGORY_DOC_MONITOR_TYPE);
			ArrayList monitorTypeValues = (ArrayList) CommonDataSingleton
					.getCodeCategoryValues(ICMSConstant.CATEGORY_DOC_MONITOR_TYPE);
			resultMap.put("monitorTypeLabels", monitorTypeLabels);
			resultMap.put("monitorTypeValues", monitorTypeValues);
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
