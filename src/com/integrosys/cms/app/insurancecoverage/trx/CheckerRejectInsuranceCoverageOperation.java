package com.integrosys.cms.app.insurancecoverage.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * @author dattatray.thorat
 * Checker reject Operation to reject made by maker
 */

public class CheckerRejectInsuranceCoverageOperation extends AbstractInsuranceCoverageTrxOperation{

    public CheckerRejectInsuranceCoverageOperation()
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
        return ICMSConstant.ACTION_CHECKER_REJECT_INSURANCE_COVERAGE;
    }

    /**
    * Process the transaction
    * 1.    Update the transaction record
    * @param anITrxValue - ITrxValue
    * @return ITrxResult - the transaction result
    * @throws com.integrosys.base.businfra.transaction.TrxOperationException if encounters any error during the processing of the transaction
    */
    public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException
    {
        IInsuranceCoverageTrxValue trxValue = super.getInsuranceCoverageTrxValue(anITrxValue);
        trxValue = super.updateInsuranceCoverageTrx(trxValue);
        return super.prepareResult(trxValue);
    }
}
