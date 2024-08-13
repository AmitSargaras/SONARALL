package com.integrosys.cms.app.geography.region.trx;

import java.util.Map;

import com.integrosys.base.businfra.transaction.AbstractTrxController;
import com.integrosys.base.businfra.transaction.ITrxOperation;
import com.integrosys.base.businfra.transaction.ITrxOperationFactory;
import com.integrosys.base.businfra.transaction.ITrxParameter;
import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxControllerException;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.transaction.CMSReadTrxManager;

/**
 * 
 * @author sandiip.shinde
 * @since 14-04-2011
 */

public class RegionReadController extends AbstractTrxController implements ITrxOperationFactory {

	 private Map nameTrxOperationMap;

	public Map getNameTrxOperationMap() {
		return nameTrxOperationMap;
	}

	public void setNameTrxOperationMap(Map nameTrxOperationMap) {
		this.nameTrxOperationMap = nameTrxOperationMap;
	}
	
	/**
     * Default Constructor
     */
    public RegionReadController() {
    }

	public ITrxOperation getOperation(ITrxValue value, ITrxParameter param)throws TrxParameterException {
		 if (null == param) {
	            throw new TrxParameterException("ITrxParameter is null!");
	        }
        String action = param.getAction();
        if (action != null) {
            if (action.equals(ICMSConstant.ACTION_READ_REGION)) {
                return (ITrxOperation) getNameTrxOperationMap().get("ReadRegionOperation");
            } else if (action.equals(ICMSConstant.ACTION_READ_REGION_ID)) {
                return (ITrxOperation) getNameTrxOperationMap().get("ReadRegionIDOperation");
            }else{

            throw new TrxParameterException("Unknow Action: " + action + ".");
            }
        }else{
        throw new TrxParameterException("Action is null!");
        }
	}


	public String getInstanceName() {
		return ICMSConstant.INSTANCE_REGION;
	}


	public ITrxResult operate(ITrxValue value, ITrxParameter param)throws TrxParameterException, TrxControllerException,TransactionException {
		if (null == value) {
            throw new TrxParameterException("ITrxValue is null!");
        }
        if (null == param) {
            throw new TrxParameterException("ITrxParameter is null!");
        }
        value = setInstanceName(value);
        DefaultLogger.debug(this, "Instance Name: " + value.getInstanceName());
        ITrxOperation op = getOperation(value, param);
        DefaultLogger.debug(this, "From state " + value.getFromState());
        CMSReadTrxManager mgr = new CMSReadTrxManager();         
        try {
        	ITrxResult result = mgr.operateTransaction(op, value);
            return result;
        }
        catch (Exception re) {
            throw new TrxControllerException("Caught Unknown Exception: " + re.toString());
        }
	}

}
