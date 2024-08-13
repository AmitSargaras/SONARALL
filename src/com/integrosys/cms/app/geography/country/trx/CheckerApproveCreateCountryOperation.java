package com.integrosys.cms.app.geography.country.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.geography.country.bus.CountryReplicationUtils;
import com.integrosys.cms.app.geography.country.bus.ICountry;

public class CheckerApproveCreateCountryOperation extends AbstractCountryTrxOperation{

	/** Default Constructor
	 */
	public CheckerApproveCreateCountryOperation() {
		super();
	}

	/**
	 * Get the operation name of the current operation
	 * 
	 * @return String - the operation name of the current operation
	 */
	public String getOperationName() {
		return ICMSConstant.ACTION_CHECKER_APPROVE_CREATE_COUNTRY;
	}

	/**
	 * Process the transaction 1. Update the actual data 2. Update the
	 * transaction record
	 * 
	 * @param anITrxValue of ITrxValue type
	 * @return ITrxResult - the transaction result
	 * @throws com.integrosys.base.businfra.transaction.TrxOperationException if
	 *         encounters any error during the processing of the transaction
	 */
	public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException {
		
		ICountryTrxValue trxValue = getCountryTrxValue(anITrxValue);
		trxValue = createActualCountry(trxValue);
		trxValue = updateCountryTrx(trxValue);
		return super.prepareResult(trxValue);
	}

	/**
	 * Update the actual property index
	 * 
	 * @param anICCDocumentLocationTrxValue
	 * @return
	 * @throws TrxOperationException
	 */
	
	private ICountryTrxValue createActualCountry(ICountryTrxValue anICountryTrxValue)
			throws TrxOperationException {
		try {
			ICountry staging = anICountryTrxValue.getStagingCountry();
			// Replicating is necessary or else stale object error will arise
			ICountry replicateStaging = CountryReplicationUtils.replicateCountryForCreateStagingCopy(staging);
			
			ICountry actual = getCountryBusManager().createCountry(replicateStaging);
			anICountryTrxValue.setActualCountry(actual);	
			
			return anICountryTrxValue;
		}
		catch (Exception ex) {
			throw new TrxOperationException(ex);
		}
		
	}
}
