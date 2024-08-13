/**
 * 
 */
package com.integrosys.cms.ui.custrelationship.shareholder;

import java.util.HashMap;
import java.util.Locale;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;

import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.uiinfra.common.ICommand;
import com.integrosys.base.uiinfra.common.IPage;
import com.integrosys.base.uiinfra.common.Page;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.custrelationship.trx.shareholder.ICustShareholderTrxValue;
import com.integrosys.cms.ui.custrelationship.CustRelAction;



/**
 * @author siew kheat
 *
 */
public class ShareHolderListAction extends CustRelAction {

	protected ICommand[] getCommandChain(String event) {
		
		ICommand objArray [] = null;
		
		if (EVENT_READ.equals(event)) {
			objArray = new ICommand[1];
			objArray[0] = new ReadShareHolderListCommand();			
		}
		else if (EVENT_PREPARE.equals(event)) {
			objArray = new ICommand[1];
			objArray[0] = new ReadShareHolderListCommand();
        }
		else if (EVENT_READ_MAKER_EDIT.equals(event) ||
				EVENT_REMOVE_NOOP.equals(event)
		) {
			objArray = new ICommand[1];
			objArray[0] = new ReadShareHolderListCommand();
			
		}else if (EVENT_ADD.equals(event) 
					|| EVENT_ADD_FRAME.equals(event)
		) {
			objArray = new ICommand[1];
			objArray[0] = new AddShareHolderListCommand();

		} else if (EVENT_ADD_NEW.equals(event)) {
			
			objArray = new ICommand[1];
			objArray[0] = new AddNewShareHolderListCommand();
		
		}else if (EVENT_REMOVE.equals(event)) {
			objArray = new ICommand[1];
			objArray[0] = new DeleteShareHolderListCommand();
			
		} else if (EVENT_SUBMIT.equals(event)) {
            return new ICommand[]{new SubmitShareHolderListCommand()};
        } else if (EVENT_SUBMIT_NOOP.equals(event)) {
            objArray = new ICommand[1];
			objArray[0] = new AddNewShareHolderListCommand();
        
        } else if (EVENT_PAGINATE.equals(event)) {
        	return new ICommand[]{new PaginateShareHolderListCommand()};
        } else if (EVENT_VIEW.equals(event)) {
            return new ICommand[]{new ReadShareHolderListCommand()};
        } else if (EVENT_READ_CHECKER_APPROVE_REJECT.equals(event)) {
            return new ICommand[]{new ReadShareHolderListCommand(),
                    new CompareShareHolderListCommand()};
        } else if (EVENT_READ_MAKER_CLOSE.equals(event)) {
	        return new ICommand[]{new ReadShareHolderListCommand()};
        
        } else if (EVENT_APPROVE.equals(event)) {
            return new ICommand[]{new ApproveShareHolderListCommand()};
        } else if (EVENT_REJECT.equals(event)) {
          
        	return new ICommand[]{new RejectShareHolderListCommand()};
        } else if (EVENT_TOTRACK.equals(event)) {
          
        	return new ICommand[]{new ReadShareHolderListCommand()};
        } 
		else if (EVENT_CLOSE.equals(event)) {
			objArray = new ICommand[1];
			objArray[0] = new CloseShareHolderListCommand();			

        }else if (EVENT_CANCEL.equals(event)) {
			objArray = new ICommand[1];
			objArray[0] = new CancelShareHolderListCommand();
        } else if (EVENT_LIST_VIEW.equals(event) ||
        	EVENT_LIST_READ.equals(event)) {
        	
            return new ICommand[]{new ListShareHolderListCommand()};
        } else if (EVENT_LIST_CHECKER_APPROVE_REJECT.equals(event)) {
            
        	return new ICommand[]{new ListCompareShareHolderListCommand()};
        } else if (EVENT_LIST_MAKER_CLOSE.equals(event)) {
            
        	return new ICommand[]{new ListShareHolderListCommand()};
        }
        return objArray;
	}

    /**
     * This method is called only for create and Update command to validate the form
     * and return the ActionErrors object.
     * 
     * @param aForm  is of type ActionForm
     * @param locale of type Locale
     * @return ActionErrors
     */
    public ActionErrors validateInput(ActionForm aForm, Locale locale) {
        DefaultLogger.debug(this, "Inside validate Input  class " +  ((ShareHolderListForm) aForm));
        return ShareHolderListValidator.validateInput( (ShareHolderListForm) aForm, locale);
    }


    /**
     * This method is used to determine which the page to be displayed next using the event
     * Result hashmap and exception hashmap.It returns the page object .
     * 
     * @param event        is of type String
     * @param resultMap    is of type HashMap
     * @param exceptionMap is of type HashMap
     * @return IPage
     */
    public IPage getNextPage(String event, HashMap resultMap,
                             HashMap exceptionMap) {

        // String must be one of the struts local forwards.
        String forward = null;

        ICustShareholderTrxValue value = (ICustShareholderTrxValue) resultMap.get(
                "CustShareHolderTrxValue");

        boolean isWip = false;

        String status = "";
        if (value != null) {
            status = value.getStatus();
            isWip = status.equals(ICMSConstant.STATE_PENDING_UPDATE) ||
                    status.equals(ICMSConstant.STATE_REJECTED_UPDATE) ||
                    status.equals(ICMSConstant.STATE_PENDING_CREATE) ||
                    status.equals(ICMSConstant.STATE_REJECTED_CREATE);
        }
		
        if (EVENT_PREPARE.equals(event)) {
            if (isWip) {
                forward = "workInProgress";
            } else if (status.equals(ICMSConstant.STATE_REJECTED_UPDATE) ||
        		status.equals(ICMSConstant.STATE_REJECTED_CREATE))
        		forward = "listForMaker";
        	else        	
        		forward = "list";
        } else if (EVENT_READ.equals(event)) {
            forward = "view";
        } else if (EVENT_READ_MAKER_EDIT.equals(event)) {
        	 
			if (status.equals(ICMSConstant.STATE_REJECTED_UPDATE) ||
        		status.equals(ICMSConstant.STATE_REJECTED_CREATE))
        		forward = "listForMaker";
        	else        	
        		forward = "list";
        } else if (EVENT_ADD.equals(event)) {
            forward = "add";
		} else if (EVENT_ADD_FRAME.equals(event)) {
            forward = "add_frame";
        } else if (EVENT_ADD_NOOP.equals(event)) {
            forward = "list";
        } else if (EVENT_ADD_NEW.equals(event) || EVENT_CANCEL.equals(event) ) {
        	if (status.equals(ICMSConstant.STATE_REJECTED_UPDATE) ||
        			status.equals(ICMSConstant.STATE_REJECTED_CREATE))
        		forward = "listForMaker";
        	else        	
        		forward = "list";        
        } else if (EVENT_REMOVE.equals(event)) {
        	if (status.equals(ICMSConstant.STATE_REJECTED_UPDATE) ||
        			status.equals(ICMSConstant.STATE_REJECTED_CREATE))
        		forward = "listForMaker";
        	else
        		forward = "list";
        } else if (EVENT_REMOVE_NOOP.equals(event)) {
            if (status.equals(ICMSConstant.STATE_REJECTED_UPDATE) ||
        			status.equals(ICMSConstant.STATE_REJECTED_CREATE))
        		forward = "listForMaker";
        	else
        		forward = "list";
        } else if (EVENT_SUBMIT.equals(event)) {
            forward = "submit";
        } else if (EVENT_SUBMIT_NOOP.equals(event)) {
        	if (status.equals(ICMSConstant.STATE_REJECTED_UPDATE) ||
        			status.equals(ICMSConstant.STATE_REJECTED_CREATE))
        		forward = "listForMaker";
        	else
        		forward = "list";
        } else if (EVENT_READ_CHECKER_APPROVE_REJECT.equals(event)) {
            forward = "list2ForChecker";
        } else if (EVENT_LIST_CHECKER_APPROVE_REJECT.equals(event)) {
                forward = "list2ForChecker";
        } else if (EVENT_READ_MAKER_CLOSE.equals(event) || EVENT_LIST_MAKER_CLOSE.equals(event)) {
        	forward = "view_close";
        } else if (EVENT_PAGINATE.equals(event)) {
        	if (status.equals(ICMSConstant.STATE_REJECTED_UPDATE) ||
        			status.equals(ICMSConstant.STATE_REJECTED_CREATE))
        		forward = "listForMaker";
        	else
        		forward = "list";	
        } else if (EVENT_APPROVE.equals(event)) {
            forward = "approve";
        } else if (EVENT_REJECT.equals(event)) {
            forward = "reject";
        } else if (EVENT_CLOSE.equals(event)) {
            forward = "close";
        } else if (EVENT_VIEW.equals(event) || EVENT_LIST_VIEW.equals(event)) {
            forward = "view";
        } else if (EVENT_TOTRACK.equals(event) || EVENT_LIST_READ.equals(event)) {
            forward = "track";
        }

        DefaultLogger.debug(this, "The name of struts forward is " + forward);

        Page page = new Page();
        page.setPageReference(forward);

        return page;
    }


    protected boolean isValidationRequired(String event) {
        return EVENT_REMOVE.equals(event) ||
        		EVENT_SUBMIT.equals(event) || EVENT_APPROVE.equals(event) ||
                EVENT_REJECT.equals(event) || EVENT_CLOSE.equals(event) || EVENT_PAGINATE.equals(event);
    }


    protected String getErrorEvent(String event) {

        if (EVENT_ADD.equals(event)) {
            return EVENT_ADD_NOOP;
        } else if (EVENT_REMOVE.equals(event)) {
            return EVENT_REMOVE_NOOP;
        } else if (EVENT_SUBMIT.equals(event) || 
        		EVENT_PAGINATE.equals(event)) {
            return EVENT_SUBMIT_NOOP;
        } else if (EVENT_APPROVE.equals(event)) {
            return EVENT_LIST_CHECKER_APPROVE_REJECT;
        } else if (EVENT_REJECT.equals(event)) {
            return EVENT_LIST_CHECKER_APPROVE_REJECT;
        } else if (EVENT_CLOSE.equals(event)) {
            return EVENT_LIST_MAKER_CLOSE;
        }else
        	return event;
        
    }


    protected String getDefaultEvent() {
        return EVENT_READ;
    }


    /** For reading items. */
    public static final String EVENT_READ = "read";

    public static final String EVENT_READ_MAKER_EDIT = "readMakerEdit";
    
    public static final String EVENT_LIST_READ = "listRead";

    /** For adding new record. */
    public static final String EVENT_ADD = "add";
    public static final String EVENT_ADD_FRAME = "add_frame";
    
    public static final String EVENT_ADD_NEW = "add_new";

    public static final String EVENT_ADD_NOOP = "addNoop";

    /** For removing checked items. */
    public static final String EVENT_REMOVE = "remove";

    /** Just to go back to list page without reexecuting anything. */
    public static final String EVENT_REMOVE_NOOP = "removeNoop";

    /** For saving and then going to notification page. */
    public static final String EVENT_SAVE = "save";

    /** For saving into session and then going to list page. */
    public static final String EVENT_PAGINATE = "paginate";

    /** Just to go back to list page. */
    public static final String EVENT_SAVE_NOOP = "saveNoop";

    /** For submitting. */
    public static final String EVENT_SUBMIT = "submit";

    /** Just to go back to list page. */
    public static final String EVENT_SUBMIT_NOOP = "submitNoop";

    /**
     *
     */
    public static final String EVENT_READ_CHECKER_APPROVE_REJECT = "readCheckerApproveReject";

    public static final String EVENT_READ_MAKER_CLOSE = "readMakerClose";

    /** For checker approving. */
    public static final String EVENT_APPROVE = "approve";

    /** For checker rejecting. */
    public static final String EVENT_REJECT = "reject";

    /** For listing for checker. */
    public static final String EVENT_LIST_CHECKER_APPROVE_REJECT = "listCheckerApproveReject";

    public static final String EVENT_LIST_MAKER_CLOSE = "listMakerClose";

    /** For maker closing. */
    public static final String EVENT_CLOSE = "close";

    public static final String EVENT_LIST_STAGING = "listStaging";

    /** For totrack view. */
    public static final String EVENT_VIEW = "view";

    /** For totrack view pagination. */
    public static final String EVENT_LIST_VIEW = "listView";
    
    public static final String EVENT_TOTRACK = "to_track";
}
