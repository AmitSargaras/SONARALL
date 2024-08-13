package com.integrosys.cms.app.goodsMaster.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;

public class CheckerRejectGoodsMasterOperation extends AbstractGoodsMasterTrxOperation{

	 public CheckerRejectGoodsMasterOperation(){
		 super();
	 }
	  
	public String getOperationName() {
        return ICMSConstant.ACTION_CHECKER_REJECT_GOODS_MASTER;
    }
	
	/**
     * Process the transaction
     * 1.	Create the actual data
     * 2.	Update the transaction record
     *
     * @param anITrxValue of ITrxValue type
     * @return ITrxResult - the transaction result
     * @throws com.integrosys.base.businfra.transaction.TrxOperationException
     *          if encounters any error during the processing of the transaction
     */
    public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException {
        IGoodsMasterTrxValue trxValue = super.getGoodsMasterTrxValue(anITrxValue);
        trxValue = super.updateGoodsMasterTrx(trxValue);
        return super.prepareResult(trxValue);
    }
}
