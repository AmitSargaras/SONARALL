package com.integrosys.cms.app.fileUpload.trx;

import java.util.Map;

import com.integrosys.base.businfra.transaction.ITrxOperation;
import com.integrosys.base.businfra.transaction.ITrxParameter;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.transaction.CMSTrxController;

public class FileUploadTrxController extends CMSTrxController{
	
	private Map nameTrxOperationMap;
	
	

	public Map getNameTrxOperationMap() {
		return nameTrxOperationMap;
	}

	public void setNameTrxOperationMap(Map nameTrxOperationMap) {
		this.nameTrxOperationMap = nameTrxOperationMap;
	}

	public String getInstanceName() {
		return ICMSConstant.INSTANCE_FILEUPLOAD;
	}

	public ITrxOperation getOperation(ITrxValue value, ITrxParameter param)
			throws TrxParameterException {
		ITrxOperation op = factoryOperation(value, param);
        DefaultLogger.debug(this, "Returning Operation: " + op);
        return op;
	}
	
	 private ITrxOperation factoryOperation(ITrxValue value, ITrxParameter param) throws TrxParameterException {
	        String action = param.getAction();
	        if (action==null) {
	            throw new TrxParameterException("Action is null in ITrxParameter!");
	        }
	        if (value==null) {
	            throw new TrxParameterException("Value is null in ITrxParameter!");
	        }
	        
	        DefaultLogger.debug(this, "Action: " + action);

	        String toState = value.getToState();
	        String fromState = value.getFromState();
	        DefaultLogger.debug(this, "toState: " + value.getToState());
	        if(toState!=null){
	        if(toState.equals(ICMSConstant.STATE_DRAFT)){
	        	if (action.equals(ICMSConstant.ACTION_MAKER_CREATE_COMPONENT)) {
	        		return (ITrxOperation) getNameTrxOperationMap().get("MakerUpdateDraftCreateComponentOperation");

	        	}
	        	if (action.equals(ICMSConstant.ACTION_MAKER_UPDATE_COMPONENT)) {
	        		return (ITrxOperation) getNameTrxOperationMap().get("MakerUpdateComponentOperation");
	        	}
	        }
	        }
	            if (action.equals(ICMSConstant.ACTION_MAKER_SAVE_COMPONENT)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("MakerSaveComponentOperation");
	            }
	            if (action.equals(ICMSConstant.ACTION_MAKER_SAVE_UPDATE_COMPONENT)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("MakerSaveUpdateComponentOperation");
	            }
	              if ((toState == null) || (toState.equals(ICMSConstant.STATE_ND))) {
	            if (action.equals(ICMSConstant.ACTION_MAKER_CREATE_FILEUPLOAD) || action.equals(ICMSConstant.ACTION_MAKER_CREATE_FILEUPLOAD_NEW)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("MakerCreateFileUploadOperation");
	            }
	            throw new TrxParameterException("Unknown Action: " + action + " with toState: " + toState);
	        } else if (toState.equals(ICMSConstant.STATE_PENDING_CREATE)) {
	            if (action.equals(ICMSConstant.ACTION_CHECKER_APPROVE_FILEUPLOAD) || action.equals(ICMSConstant.ACTION_CHECKER_APPROVE_FILEUPLOAD_NEW)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("CheckerApproveCreateFileUploadOperation");
	            }

	            if (action.equals(ICMSConstant.ACTION_CHECKER_REJECT_FILEUPLOAD)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("CheckerRejectFileUploadOperation");
	            }
	            throw new TrxParameterException("Unknown Action: " + action + " with toState: " + toState);
	        } else if (toState.equals(ICMSConstant.STATE_ACTIVE)) {
	            if (action.equals(ICMSConstant.ACTION_MAKER_UPDATE_COMPONENT)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("MakerUpdateComponentOperation");
	            } else if (action.equals(ICMSConstant.ACTION_MAKER_DELETE_COMPONENT)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("MakerDeleteComponentOperation");
	            }else if (action.equals(ICMSConstant.ACTION_MAKER_SAVE_UPDATE_COMPONENT)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("MakerUpdateComponentOperation");
	            }
	            throw new TrxParameterException("Unknow Action: " + action + " with toState: " + toState);
	        } else if (toState.equals(ICMSConstant.STATE_PENDING_UPDATE)) {
	            if (action.equals(ICMSConstant.ACTION_CHECKER_APPROVE_FILEUPLOAD) || action.equals(ICMSConstant.ACTION_CHECKER_APPROVE_FILEUPLOAD_NEW)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("CheckerApproveUpdateComponentOperation");
	            } else if (action.equals(ICMSConstant.ACTION_CHECKER_REJECT_FILEUPLOAD)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("CheckerRejectFileUploadOperation");
	            }
	            throw new TrxParameterException("Unknown Action: " + action + " with toState: " + toState);
	        } else if (toState.equals(ICMSConstant.STATE_PENDING_DELETE)) {
	            if (action.equals(ICMSConstant.ACTION_CHECKER_APPROVE_FILEUPLOAD) || action.equals(ICMSConstant.ACTION_CHECKER_APPROVE_FILEUPLOAD_NEW)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("CheckerApproveUpdateComponentOperation");
	            } else if (action.equals(ICMSConstant.ACTION_CHECKER_REJECT_FILEUPLOAD)) {
	                return (ITrxOperation) getNameTrxOperationMap().get("CheckerRejectFileUploadOperation");
	            }
	            throw new TrxParameterException("Unknown Action: " + action + " with toState: " + toState);
	        } else if (toState.equals(ICMSConstant.STATE_REJECTED)) {
	            if (action.equals(ICMSConstant.ACTION_MAKER_EDIT_REJECTED_COMPONENT)) {
	                if (fromState.equals(ICMSConstant.STATE_PENDING_CREATE)) {
	                    return (ITrxOperation) getNameTrxOperationMap().get("MakerEditRejectedCreateComponentOperation");
	                } else if (fromState.equals(ICMSConstant.STATE_PENDING_UPDATE)) {
	                    return (ITrxOperation) getNameTrxOperationMap().get("MakerEditRejectedUpdateComponentOperation");
	                } else if (fromState.equals(ICMSConstant.STATE_PENDING_DELETE)) {
	                    return (ITrxOperation) getNameTrxOperationMap().get("MakerEditRejectedDeleteComponentOperation");
	                }
	            } else if (action.equals(ICMSConstant.ACTION_MAKER_CLOSE_REJECTED_FILEUPLOAD)) {
	                if (fromState.equals(ICMSConstant.STATE_PENDING_CREATE)) {
	                    return (ITrxOperation) getNameTrxOperationMap().get("MakerCloseRejectedCreateFileUploadOperation");
	                } else if (fromState.equals(ICMSConstant.STATE_PENDING_UPDATE)) {
	                    return (ITrxOperation) getNameTrxOperationMap().get("MakerCloseRejectedUpdateComponentOperation");
	                } else if (fromState.equals(ICMSConstant.STATE_PENDING_DELETE)) {
	                    return (ITrxOperation) getNameTrxOperationMap().get("MakerCloseRejectedDeleteComponentOperation");
	                }
	            }
	            throw new TrxParameterException("Unknown Action: " + action + " with toState: " + toState);
	        }else if (action.equals(ICMSConstant.ACTION_MAKER_CLOSE_DRAFT_COMPONENT)) {
	        	return (ITrxOperation) getNameTrxOperationMap().get("MakerCloseDraftComponentOperation");
	        }
	        throw new TrxParameterException("To State does not match presets! No operations found!");
	    }

}
