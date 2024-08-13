package com.integrosys.cms.app.insurancecoverage.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.insurancecoverage.bus.InsuranceCoverageReplicationUtils;
import com.integrosys.cms.ui.insurancecoverage.IInsuranceCoverage;

/**
 * @author dattatray.thorat
 * Checker approve Operation to approve update made by maker
 */

public class CheckerApproveCreateInsuranceCoverageOperation extends AbstractInsuranceCoverageTrxOperation {
	/**
	 * Default Constructor
	 */
	public CheckerApproveCreateInsuranceCoverageOperation() {
		super();
	}

	/**
	 * Get the operation name of the current operation
	 * 
	 * @return String - the operation name of the current operation
	 */
	public String getOperationName() {
		return ICMSConstant.ACTION_CHECKER_APPROVE_CREATE_INSURANCE_COVERAGE;
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
		
		IInsuranceCoverageTrxValue trxValue = getInsuranceCoverageTrxValue(anITrxValue);
		trxValue = updateActualInsuranceCoverage(trxValue);
		trxValue = updateInsuranceCoverageTrx(trxValue);
		return super.prepareResult(trxValue);
	}

	/**
	 * Update the actual property index
	 * 
	 * @param anICCDocumentLocationTrxValue
	 * @return
	 * @throws TrxOperationException
	 */
	private IInsuranceCoverageTrxValue updateActualInsuranceCoverage(IInsuranceCoverageTrxValue anICCInsuranceCoverageTrxValue)
			throws TrxOperationException {
		try {
			IInsuranceCoverage staging = anICCInsuranceCoverageTrxValue.getStagingInsuranceCoverage();
			IInsuranceCoverage replicatedInsuranceCoverage = InsuranceCoverageReplicationUtils.replicateInsuranceCoverageForCreateStagingCopy(staging);
			IInsuranceCoverage updatedInsuranceCoverage = getInsuranceCoverageBusManager().createInsuranceCoverage(replicatedInsuranceCoverage);
			anICCInsuranceCoverageTrxValue.setInsuranceCoverage(updatedInsuranceCoverage);

			return anICCInsuranceCoverageTrxValue;
		}
		catch (Exception ex) {
			throw new TrxOperationException(ex);
		}
		
	}
}
