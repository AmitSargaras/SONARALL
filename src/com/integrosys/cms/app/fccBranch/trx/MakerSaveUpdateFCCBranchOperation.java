package com.integrosys.cms.app.fccBranch.trx;

import java.rmi.RemoteException;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.propertyindex.bus.IPropertyIdx;
import com.integrosys.cms.app.fccBranch.bus.IFCCBranch;
import com.integrosys.cms.app.fccBranch.bus.FCCBranchException;
import com.integrosys.cms.app.fccBranch.bus.FCCBranchReplicationUtils;
import com.integrosys.cms.app.transaction.ICMSTrxValue;

/**
 * Title: CLIMS 
 * Description:
 * Copyright: Integro Technologies Sdn Bhd 
 * Author: Andy Wong 
 * Date: Jan 18, 2008
 */
public class MakerSaveUpdateFCCBranchOperation extends AbstractFCCBranchTrxOperation {

   /**
    * Defaulc Constructor
    */
    public MakerSaveUpdateFCCBranchOperation()
    {
        super();
    }

	/**
	* Get the operation name of the current operation
	*
	* @return String - the operation name of the current operation
	*/
	public String getOperationName()
	{
		return ICMSConstant.ACTION_MAKER_SAVE_UPDATE_FCCBRANCH;
	}


	/**
	* Process the transaction
	* 1.	Create the staging data
	* 2.	Create the transaction record
	* @param anITrxValue of ITrxValue type
	* @return ITrxResult - the transaction result
	* @throws TrxOperationException if encounters any error during the processing of the transaction
	*/
	public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException {
        IFCCBranchTrxValue idxTrxValue = getFCCBranchTrxValue(anITrxValue);
        IFCCBranch stage = idxTrxValue.getStagingFCCBranch();
        IFCCBranch replicatedFCCBranch = FCCBranchReplicationUtils.replicateFCCBranchForCreateStagingCopy(stage);
     //   replicatedFCCBranch.getSystemBankCode().setId(stage.getSystemBankCode().getId());
        idxTrxValue.setStagingFCCBranch(replicatedFCCBranch);

        IFCCBranchTrxValue trxValue = createStagingFCCBranch(idxTrxValue);
        trxValue = updateFCCBranchTrx(trxValue);
        return super.prepareResult(trxValue);
    }

	
	

}
