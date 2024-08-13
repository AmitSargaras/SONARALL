/**
 * Copyright Integro Technologies Pte Ltd
 * $Header:
 */
package com.integrosys.cms.ui.caseBranch;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.uiinfra.common.CommonAction;
import com.integrosys.base.uiinfra.common.ICommand;
import com.integrosys.base.uiinfra.common.IPage;
import com.integrosys.base.uiinfra.common.IPin;
import com.integrosys.base.uiinfra.common.Page;
import com.integrosys.cms.app.caseBranch.bus.CaseBranchException;

/**
 * @author $Author: Abhijit R$ Action For CaseBranch
 */
public class MaintainCaseBranchAction extends CommonAction implements IPin {

	private Map nameCommandMap;

	public Map getNameCommandMap() {
		return nameCommandMap;
	}

	public void setNameCommandMap(Map nameCommandMap) {
		this.nameCommandMap = nameCommandMap;
	}

	public static final String FIRST_SORT = "LOGIN_ID";

	public static final String SECOND_SORT = "USER_NAME";

	// public static final String EVENT_LIST = "maker_list_caseBranch";
	public static final String MAKER_LIST_CASEBRANCH = "maker_list_caseBranch";
	public static final String CHECKER_LIST_CASEBRANCH = "checker_list_caseBranch";
	public static final String CHECKER_VIEW_CASEBRANCH = "checker_view_caseBranch";
	public static final String MAKER_VIEW_CASEBRANCH = "maker_view_caseBranch";
	public static final String MAKER_EDIT_CASEBRANCH_READ = "maker_edit_caseBranch_read";
	public static final String MAKER_CONFIRM_RESUBMIT_EDIT = "maker_confirm_resubmit_edit";
	public static final String MAKER_CONFIRM_RESUBMIT_CREATE = "maker_confirm_resubmit_create";
	public static final String MAKER_CONFIRM_RESUBMIT_DELETE = "maker_confirm_resubmit_delete";
	public static final String MAKER_EDIT_CASEBRANCH = "maker_edit_caseBranch";
	public static final String MAKER_DELETE_CASEBRANCH = "maker_delete_caseBranch";
	public static final String CHECKER_EDIT_READ = "checker_edit_read";
	public static final String REJECTED_DELETE_READ = "rejected_delete_read";
	public static final String CHECKER_APPROVE_EDIT = "checker_approve_edit";
	public static final String CHECKER_REJECT_EDIT = "checker_reject_edit";
	public static final String CHECKER_REJECT_CREATE = "checker_reject_create";
	public static final String CHECKER_REJECT_DELETE = "checker_reject_delete";
	public static final String MAKER_PREPARE_CLOSE = "maker_prepare_close";
	public static final String MAKER_PREPARE_RESUBMIT = "maker_prepare_resubmit";
	public static final String MAKER_CONFIRM_CLOSE = "maker_confirm_close";
	public static final String REDIRECT_LIST_CASEBRANCH = "redirect_list_caseBranch";
	public static final String MAKER_DELETE_CASEBRANCH_READ = "maker_delete_caseBranch_read";
	public static final String MAKER_EDIT_REJECT_EDIT = "maker_edit_reject_edit";
	public static final String MAKER_SEARCH_CASEBRANCH = "maker_search_caseBranch";
	public static final String LIST_PAGINATION = "list_pagination";
	public static final String CHECKER_LIST_PAGINATION = "checker_list_pagination";
	public static final String MAKER_SEARCH_LIST_CASEBRANCH = "maker_search_list_caseBranch";
	public static final String CHECKER_SEARCH_LIST_CASEBRANCH = "checker_search_list_caseBranch";
	public static final String CHECKER_PROCESS_EDIT = "checker_process_edit";
	public static final String CHECKER_PROCESS_DELETE = "checker_process_delete";
	public static final String CHECKER_PROCESS_CREATE = "checker_process_create";
	public static final String MAKER_PREPARE_RESUBMIT_DELETE = "maker_prepare_resubmit_delete";
	public static final String MAKER_PREPARE_CREATE_CASEBRANCH = "maker_prepare_create_caseBranch";
	public static final String MAKER_CREATE_CASEBRANCH = "maker_create_caseBranch";
	public static final String MAKER_DRAFT_CASEBRANCH = "maker_draft_caseBranch";
	public static final String MAKER_SAVE_PROCESS = "maker_save_process";
	public static final String MAKER_SAVE_CREATE = "maker_save_create";
	public static final String MAKER_PREPARE_RESUBMIT_CREATE = "maker_prepare_resubmit_create";
	public static final String MAKER_UPDATE_DRAFT_CASEBRANCH = "maker_update_draft_caseBranch";
	public static final String MAKER_UPDATE_SAVE_PROCESS = "maker_update_save_process";
	public static final String MAKER_SAVE_UPDATE = "maker_save_update";
	public static final String MAKER_DRAFT_CLOSE_PROCESS = "maker_draft_close_process";
	public static final String MAKER_CONFIRM_DRAFT_CLOSE = "maker_confirm_draft_close";

	//CaseBranch Master Upload
	public static final String CHECKER_APPROVE_INSERT = "checker_approve_insert";
	public static final String MAKER_PREPARE_UPLOAD_CASEBRANCH = "maker_prepare_upload_caseBranch";
	public static final String MAKER_EVENT_UPLOAD_CASEBRANCH = "maker_event_upload_caseBranch";
	public static final String MAKER_REJECTED_DELETE_READ = "maker_rejected_delete_read";
	public static final String CHECKER_PROCESS_CREATE_INSERT = "checker_process_create_insert";
	public static final String CHECKER_REJECT_INSERT = "checker_reject_insert";
	public static final String MAKER_PREPARE_INSERT_CLOSE = "maker_prepare_insert_close";
	public static final String MAKER_CONFIRM_INSERT_CLOSE = "maker_confirm_insert_close";
	/**
	 * This method return a Array of Commad Objects responsible for a event
	 * 
	 * @param event
	 *            is of type String
	 * @return Icommand Array
	 */
	public ICommand[] getCommandChain(String event) {

		DefaultLogger.debug(this,"In Maintain CaseBranch Action with event --" + event);

		ICommand objArray[] = null;
		if ((event.equals(CHECKER_LIST_CASEBRANCH))
				|| event.equals(MAKER_LIST_CASEBRANCH)) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get("ListCaseBranchCmd");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");
		} else if ((event.equals(CHECKER_VIEW_CASEBRANCH))
				|| event.equals(MAKER_VIEW_CASEBRANCH)) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get("MakerReadCaseBranchCmd");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");

		} else if ((event != null) && event.equals(MAKER_EDIT_CASEBRANCH_READ)) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get("MakerReadCaseBranchCmd");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");
		} else if ((event.equals(MAKER_CONFIRM_RESUBMIT_EDIT))
				|| event.equals(MAKER_EDIT_CASEBRANCH)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerEditCaseBranchCmd");
		} else if ((event != null) && (event.equals(MAKER_DELETE_CASEBRANCH_READ))) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get("MakerReadCaseBranchCmd");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");

		} else if ((event.equals(MAKER_CONFIRM_RESUBMIT_DELETE))
				|| event.equals(MAKER_DELETE_CASEBRANCH)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerDeleteCaseBranchCmd");
		} else if ((event.equals(MAKER_SEARCH_LIST_CASEBRANCH))
				|| event.equals(CHECKER_SEARCH_LIST_CASEBRANCH)) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"SearchListCaseBranchCommand");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");

		} else if ((event.equals(LIST_PAGINATION))
				|| event.equals(CHECKER_LIST_PAGINATION)) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get("PaginationCmd");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");

		} else if ((event.equals(CHECKER_PROCESS_EDIT) || event
				.equals(REJECTED_DELETE_READ))

				|| event.equals(CHECKER_PROCESS_DELETE)
				|| event.equals(CHECKER_PROCESS_CREATE)
				|| event.equals(MAKER_SAVE_PROCESS)
				|| event.equals(MAKER_UPDATE_SAVE_PROCESS)) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"CheckerReadCaseBranchCmd");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");
		} else if ((event != null) && event.equals(CHECKER_APPROVE_EDIT)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"CheckerApproveEditCaseBranchCmd");
		} else if ((event.equals(CHECKER_REJECT_CREATE))
				|| event.equals(CHECKER_REJECT_EDIT)
				|| event.equals(CHECKER_REJECT_DELETE)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"CheckerRejectEditCaseBranchCmd");
		} else if ((event.equals(MAKER_PREPARE_CLOSE))
				|| event.equals(MAKER_PREPARE_RESUBMIT)
				|| event.equals(MAKER_PREPARE_RESUBMIT_DELETE)
				|| event.equals(MAKER_PREPARE_RESUBMIT_CREATE)
				|| event.equals(MAKER_DRAFT_CLOSE_PROCESS)) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"CheckerReadCaseBranchCmd");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");
		} else if ((event != null)
				&& (event.equals(MAKER_CONFIRM_CLOSE) || event
						.equals(MAKER_CONFIRM_DRAFT_CLOSE))) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerCloseCaseBranchCmd");
		} else if ((event != null)
				&& event.equals(MAKER_PREPARE_CREATE_CASEBRANCH)) {
			objArray = new ICommand[2];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerPrepareCreateCaseBranchCmd");
			objArray[1] = (ICommand) getNameCommandMap().get("ListOptionsCmd");

		} else if ((event.equals(MAKER_CONFIRM_RESUBMIT_CREATE))
				|| event.equals(MAKER_CREATE_CASEBRANCH)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerCreateCaseBranchCmd");

		} else if (event.equals(MAKER_DRAFT_CASEBRANCH)
				|| event.equals(MAKER_UPDATE_DRAFT_CASEBRANCH)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerSaveCaseBranchCmd");

		} else if (event.equals(MAKER_SAVE_CREATE)
				|| event.equals(MAKER_SAVE_UPDATE)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerEditCaseBranchCmd");

		}else if (event.equals(MAKER_PREPARE_UPLOAD_CASEBRANCH)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerPrepareUploadCaseBranchCmd");

		}else if (event.equals(MAKER_EVENT_UPLOAD_CASEBRANCH)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerUploadCaseBranchCmd");

		}else if (event.equals(MAKER_REJECTED_DELETE_READ)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"CheckerReadFileInsertListCmd");

		}else if ((event != null) && event.equals(CHECKER_APPROVE_INSERT)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get("CheckerApproveInsertCaseBranchCmd");
		}else if ( event.equals(CHECKER_REJECT_INSERT)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"CheckerRejectInsertCaseBranchCmd");
		}else if ((event != null) && event.equals(CHECKER_PROCESS_CREATE_INSERT)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"CheckerReadFileInsertListCmd");
		}else if ((event != null) && event.equals(MAKER_PREPARE_INSERT_CLOSE)) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"CheckerReadFileInsertListCmd");
		}else if ((event != null) && (event.equals(MAKER_CONFIRM_INSERT_CLOSE))) {
			objArray = new ICommand[1];
			objArray[0] = (ICommand) getNameCommandMap().get(
					"MakerInsertCloseCaseBranchCmd");
		}


		return (objArray);
	}

	/**
	 * This method is called only for create and Update command to validate the
	 * form and return the ActionErrors object.
	 * 
	 * @param aForm
	 *            is of type ActionForm
	 * @param locale
	 *            of type Locale
	 * @return ActionErrors
	 */
	public ActionErrors validateInput(ActionForm aForm, Locale locale)  {
		return CaseBranchValidator.validateInput(aForm, locale);
	}

	protected boolean isValidationRequired(String event) {
		boolean result = false;
		if (event.equals(MAKER_EDIT_CASEBRANCH)
				|| event.equals(MAKER_EDIT_REJECT_EDIT)
				|| event.equals(MAKER_SEARCH_CASEBRANCH)
				|| event.equals(MAKER_CONFIRM_RESUBMIT_EDIT)
				|| event.equals(MAKER_CREATE_CASEBRANCH)
				|| event.equals(MAKER_SAVE_UPDATE)
				|| event.equals("maker_draft_caseBranch")
				|| event.equals(MAKER_CONFIRM_RESUBMIT_DELETE)
				|| event.equals(MAKER_CONFIRM_RESUBMIT_CREATE)
				|| event.equals("maker_update_draft_caseBranch")
				|| event.equals(MAKER_LIST_CASEBRANCH)
				|| event.equals(CHECKER_LIST_CASEBRANCH))

		{
			result = true;
		}
		return result;
	}

	/**
	 * This method is used to determine which the page to be displayed next
	 * using the event Result hashmap and exception hashmap.It returns the page
	 * object .
	 * 
	 * @param event
	 *            is of type String
	 * @param resultMap
	 *            is of type HashMap
	 * @param exceptionMap
	 *            is of type HashMap
	 * @return IPage
	 */
	public IPage getNextPage(String event, HashMap resultMap,
			HashMap exceptionMap) {
		Page aPage = new Page();
		DefaultLogger.debug(this, " Exception map error is "
				+ exceptionMap.isEmpty());
		if ((resultMap.get("wip") != null)
				&& (resultMap.get("wip")).equals("wip")) {
			aPage.setPageReference(getReference("work_in_process"));
			return aPage;
		} else if ((resultMap.get("Error_EmpId") != null)
				&& (resultMap.get("Error_EmpId")).equals("Error_EmpId")) {
			DefaultLogger.debug(this, "Inside Error_EmpId");
			aPage.setPageReference("maker_add_edit_caseBranch_page");
			return aPage;
		} else if(event.equalsIgnoreCase("maker_add_edit_caseBranch_error")) {
			aPage.setPageReference("maker_add_edit_caseBranch_page");
			return aPage;
		}else if ((resultMap.get("errorEveList") != null) && ((String) resultMap.get("errorEveList")).equals("errorEveList")) {
			aPage.setPageReference("maker_fileupload_caseBranch_page");
			return aPage;
		}else if(event.equalsIgnoreCase("checker_reject_edit_error")) {
			aPage.setPageReference("checker_caseBranch_page");
			return aPage;
		}else {
			aPage.setPageReference(getReference(event));
			return aPage;
		}
		/*if ((resultMap.get("wip") != null)
				&& (resultMap.get("wip")).equals("wip")) {
			aPage.setPageReference(getReference("work_in_process"));
			return aPage;
		} else if ((resultMap.get("Error_EmpId") != null)
				&& (resultMap.get("Error_EmpId")).equals("Error_EmpId")) {
			DefaultLogger.debug(this, "Inside Error_EmpId");
			aPage.setPageReference("maker_add_edit_caseBranch_page");
			return aPage;
		} else {
			aPage.setPageReference(getReference(event));
			return aPage;
		}*/
	}

	protected String getErrorEvent(String event) {
		String errorEvent = getDefaultEvent();
		if ("maker_add_caseBranch".equals(event)) {
			errorEvent = "maker_add_edit_caseBranch_error";
		} else if ("maker_edit_caseBranch".equals(event)
				|| event.equals("maker_confirm_resubmit_edit")|| event.equals("maker_update_draft_caseBranch")) {
			errorEvent = "maker_add_edit_caseBranch_error";
		} else if ("maker_edit_reject_add".equals(event)) {
			errorEvent = "maker_add_edit_caseBranch_error";
		} else if ("maker_edit_reject_edit".equals(event)) {
			errorEvent = "maker_add_edit_caseBranch_error";
		} else if ("checker_approve_edit".equals(event)) {
			errorEvent = "checker_approve_edit_error";
		} else if ("checker_approve_add".equals(event)) {
			errorEvent = "checker_add_read_error";
		} else if (event.equals("maker_search_caseBranch")) {
			errorEvent = "maker_prepare_search_caseBranch";
		}else if (event.equals("maker_create_caseBranch")|| event.equals("maker_draft_caseBranch")) {
			errorEvent = "maker_prepare_create_caseBranch";
		}else if (event.equals("maker_save_update")) {
			errorEvent = "maker_save_update_error";
		}else if (event.equals("maker_save_update")) {
			errorEvent = "maker_save_update_error";
		}else if (event.equals("maker_confirm_resubmit_delete")) {
			errorEvent = "maker_confirm_resubmit_delete_error";
		}else if (event.equals("checker_reject_edit")) {
			errorEvent = "checker_reject_edit_error";
		}else if (event.equals("checker_reject_create")) {
			errorEvent = "checker_reject_create_error";
		}else if (event.equals("checker_reject_delete")) {
			errorEvent = "checker_reject_delete_error";
		}else if(event.equals("maker_list_caseBranch")){
			errorEvent="maker_list_caseBranch";
		}else if(event.equals("checker_list_caseBranch")){
			errorEvent="checker_list_caseBranch";
		}
		return errorEvent;
	}

	/**
	 * method which determines the forward name for a particular event
	 * 
	 * @param event
	 *            as String
	 * @return String
	 */
	private String getReference(String event) {
		String forwardName = null;
		if (event.equals("maker_list_caseBranch")) {
			forwardName = "maker_list_caseBranch_page";
		} else if((event != null) && event.equals("checker_list_caseBranch")){
			forwardName = "checker_list_caseBranch_page";
		}
		else if ((event != null) && event.equals("maker_search_caseBranch")) {
			forwardName = "maker_list_caseBranch_page";
		} else if ((event.equals("checker_list_pagination"))
				|| event.equals("list_pagination")) {
			forwardName = "maker_list_caseBranch_page";
		} else if ((event != null) && event.equals("redirect_list_caseBranch")) {
			forwardName = "maker_list_caseBranch_page";
		} else if ((event != null) && event.equals("checker_list_caseBranch")) {
			forwardName = "checker_list_caseBranch_page";
		} else if ((event != null) && event.equals("maker_prepare_add_caseBranch")) {
			forwardName = "maker_add_edit_caseBranch_page";
		} else if ((event != null) && event.equals("maker_add_caseBranch")) {
			forwardName = "common_submit_page";
		} else if ((event.equals("checker_view_caseBranch"))
				|| event.equals("maker_view_caseBranch")) {
			forwardName = "maker_view_page";
		}  else if (event.equals("maker_confirm_resubmit_delete_error")) {
			forwardName = "maker_prepare_resubmit_delete";
		}else if ((event != null) && event.equals("maker_edit_caseBranch_read")) {
			forwardName = "maker_add_edit_caseBranch_page";
		} else if ((event.equals("maker_confirm_resubmit_edit"))
				|| event.equals("maker_edit_caseBranch")
				|| event.equals("maker_confirm_resubmit_delete")
				|| event.equals("maker_create_caseBranch")
				|| event.equals("maker_draft_caseBranch")
				|| event.equals("maker_confirm_resubmit_create")
				|| event.equals("maker_save_create")
				|| event.equals("maker_save_update")
				|| event.equals("maker_update_draft_caseBranch")
				|| event.equals("maker_event_upload_caseBranch")) {
			forwardName = "common_submit_page";
		} else if ((event != null) && event.equals("maker_delete_caseBranch_read")) {
			forwardName = "maker_view_delete_page";
		} else if ((event != null)
				&& (event.equals("maker_save_process") || event
						.equals("maker_update_save_process")|| event
						.equals("maker_save_update_error"))) {
			forwardName = "maker_view_save_page";
		} else if ((event != null) && event.equals("maker_delete_caseBranch")) {
			forwardName = "common_submit_page";
		} else if ((event != null) && event.equals("checker_add_read")) {
			forwardName = "checker_caseBranch_page";
		} else if ((event != null) && event.equals("checker_add_read_error")) {
			forwardName = "checker_caseBranch_page";
		} else if ((event != null) && event.equals("checker_edit_read")) {
			forwardName = "checker_caseBranch_page";
		} 
		else if ((event != null) && event.equals("checker_delete_read")) {
			forwardName = "checker_caseBranch_page";
		} else if ((event != null) && event.equals("checker_approve_add")) {
			forwardName = "common_approve_page";
		} else if ((event != null) && event.equals("checker_approve_edit")) {
			forwardName = "common_approve_page";
		} else if ((event != null) && event.equals("checker_approve_delete")) {
			forwardName = "common_approve_page";
		} else if ((event != null) && event.equals("checker_reject_add")) {
			forwardName = "common_reject_page";
		} else if ((event != null) && event.equals("checker_reject_edit")) {
			forwardName = "common_reject_page";
		} else if ((event != null) && event.equals("checker_reject_delete")) {
			forwardName = "common_reject_page";
		} else if ((event != null) && event.equals("rejected_add_read")) {
			forwardName = "maker_add_edit_caseBranch_page";
		} else if ((event != null) && event.equals("rejected_edit_read")) {
			forwardName = "maker_add_edit_caseBranch_page";
		} else if ((event != null) && event.equals("rejected_delete_read")) {
			forwardName = "maker_view_todo_page";
		} else if ((event != null) && event.equals("maker_cncl_reject_add")) {
			forwardName = "common_close_page";
		} else if ((event != null) && event.equals("maker_cncl_reject_edit")) {
			forwardName = "common_close_page";
		} else if ((event != null) && event.equals("maker_cncl_reject_delete")) {
			forwardName = "common_close_page";
		} else if ((event != null) && event.equals("maker_edit_reject_add")) {
			forwardName = "common_submit_page";
		} else if ((event != null) && event.equals("maker_edit_reject_edit")) {
			forwardName = "common_submit_page";
		} else if ((event != null) && event.equals("maker_edit_reject_delete")) {
			forwardName = "common_submit_page";
		} else if ((event != null) && event.equals("work_in_process")) {
			forwardName = "work_in_process_page";
		} else if ((event != null)
				&& event.equals("maker_add_edit_caseBranch_error")) {
			forwardName = "maker_add_edit_caseBranch_page";
		} else if ((event != null) && event.equals("rejected_read")) {
			forwardName = "rejected_read_page";
		} else if ((event != null)
				&& event.equals("checker_approve_edit_error")) {
			forwardName = "checker_caseBranch_page";
		} else if ((event != null)
				&& event.equals("maker_prepare_search_caseBranch")) {
			forwardName = "search_caseBranch_page";
		} else if ((event.equals("checker_search_list_caseBranch"))
				|| event.equals("maker_search_list_caseBranch")) {
			forwardName = "maker_list_caseBranch_page";
		} else if ((event.equals("checker_process_edit"))
				|| event.equals("checker_process_delete")
				|| event.equals("checker_process_create")) {
			forwardName = "checker_caseBranch_page";
		} else if ((event != null) && event.equals("maker_prepare_resubmit")) {
			forwardName = "maker_prepare_resubmit";
		} else if ((event != null)
				&& (event.equals("maker_prepare_close") || event
						.equals("maker_draft_close_process"))) {
			forwardName = "maker_prepare_close";
		} else if ((event != null)
				&& event.equals("maker_prepare_resubmit_delete")) {
			forwardName = "maker_prepare_resubmit_delete";
		} else if ((event != null)
				&& (event.equals(MAKER_CONFIRM_CLOSE) || event
						.equals(MAKER_CONFIRM_DRAFT_CLOSE))) {
			forwardName = "common_close_page";
		} else if ((event != null)
				&& (event.equals("maker_prepare_create_caseBranch"))) {
			forwardName = "prepare_create_caseBranch";
		}else if ((event != null)
				&& (event.equals("maker_create_caseBranch_error"))) {
			forwardName = "prepare_create_caseBranch";
		} else if (event.equals("maker_prepare_resubmit_create")) {
			forwardName = "maker_prepare_resubmit";
		}else if ((event != null) && event.equals("maker_prepare_upload_caseBranch")) {
			forwardName = "prepare_upload_caseBranch_page";
		}else if ((event != null) && event.equals("maker_rejected_delete_read")) {
			forwardName = "maker_view_insert_todo_page";
		}else if ((event != null) && event.equals("checker_approve_insert")) {
			forwardName = "common_approve_page";
		}else if ((event != null) && event.equals("checker_reject_insert")) {
		forwardName = "common_reject_page";
		}else if (event.equals("checker_process_create_insert")) {
			forwardName = "checker_caseBranch_insert_page";
		}else if ((event != null)
				&& (event.equals(MAKER_CONFIRM_INSERT_CLOSE))) {
			forwardName = "common_close_page";
		} else if ((event != null)
				&& (event.equals(MAKER_PREPARE_INSERT_CLOSE))) {
			forwardName = "maker_prepare_insert_close";
		} else if ( event.equals("checker_reject_edit_error")
				||event.equals("checker_reject_create_error")
				||event.equals("checker_reject_delete_error")) {
			forwardName = "checker_caseBranch_page";
		}

		return forwardName;
	}

}