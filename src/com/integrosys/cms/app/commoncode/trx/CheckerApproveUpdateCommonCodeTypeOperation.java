package com.integrosys.cms.app.commoncode.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * @author $Author: marvin $<br>
 * @version $Id$
 */
public class CheckerApproveUpdateCommonCodeTypeOperation extends AbstractCommonCodeTypeTrxOperation {

	/**
	 * Defaulc Constructor
	 */
	public CheckerApproveUpdateCommonCodeTypeOperation() {
		super();
	}

	/**
	 * Get the operation name of the current operation
	 * 
	 * @return String - the operation name of the current operation
	 */
	public String getOperationName() {
		return ICMSConstant.ACTION_CHECKER_APPROVE_UPDATE_COMMON_CODE_TYPE;
	}

	/**
	 * Process the transaction 1. Update the actual data 2. Update the
	 * transaction record
	 * @param anITrxValue - ITrxValue
	 * @return ITrxResult - the transaction result
	 * @throws TrxOperationException if encounters any error during the
	 *         processing of the transaction
	 */
	public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException {
		ICommonCodeTypeTrxValue trxValue = getCommonCodeTypeTrxValue(anITrxValue);

		trxValue = updateActualCommonCodeType(trxValue);
		trxValue = super.updateCommonCodeTypeTransaction(trxValue);
		return super.prepareResult(trxValue);
	}

}
