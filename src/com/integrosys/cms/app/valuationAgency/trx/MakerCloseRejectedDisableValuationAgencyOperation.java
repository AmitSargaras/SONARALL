package com.integrosys.cms.app.valuationAgency.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * @author rajib.aich
 *  Checker approve Operation to approve update made by maker
 */
public class MakerCloseRejectedDisableValuationAgencyOperation extends
		AbstractValuationAgencyTrxOperation {

	/**
	 * Defaulc Constructor
	 */
	public MakerCloseRejectedDisableValuationAgencyOperation() {
		super();
	}

	public String getOperationName() {
		return ICMSConstant.ACTION_MAKER_CLOSE_REJECTED_DISABLE_VALUATION_AGENCY;
	}

	/**
	 * Process the transaction 1. Update the transaction record
	 * 
	 * @param anITrxValue
	 *            - ITrxValue
	 * @return ITrxResult - the transaction result
	 * @throws com.integrosys.base.businfra.transaction.TrxOperationException
	 *             if encounters any error during the processing of the
	 *             transaction
	 */
	public ITrxResult performProcess(ITrxValue anITrxValue)
			throws TrxOperationException {
		IValuationAgencyTrxValue trxValue = super
				.getValuationAgencyTrxValue(anITrxValue);
		trxValue = updateValuationAgencyTrx(trxValue);
		return super.prepareResult(trxValue);
	}
}
