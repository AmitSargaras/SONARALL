package com.integrosys.cms.ui.otherdoc;

import java.util.Locale;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.cms.ui.docglobal.DocumentationGlobalAction;



/**
 * @author $Author: elango $<br>
 * @version $Revision: 1.3 $
 * @since $Date: 2003/08/23 07:30:45 $ Tag: $Name: $
 */
public class OtherGlobalAction extends DocumentationGlobalAction {


	public ActionErrors validateInput(ActionForm aForm, Locale locale) {
		DefaultLogger.debug(this, "OtherGlobalAction go to OtherGlobalFormValidator");
		return OtherGlobalFormValidator.validateInput((OtherGlobalForm) aForm, locale);
	}
	
}
