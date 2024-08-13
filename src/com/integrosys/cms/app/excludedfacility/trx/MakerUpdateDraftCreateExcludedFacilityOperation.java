package com.integrosys.cms.app.excludedfacility.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.excludedfacility.bus.ExcludedFacilityReplicationUtils;
import com.integrosys.cms.app.excludedfacility.bus.IExcludedFacility;

public class MakerUpdateDraftCreateExcludedFacilityOperation extends AbstractExcludedFacilityTrxOperation {

	/**
	 * Defaulc Constructor
	 */
	public MakerUpdateDraftCreateExcludedFacilityOperation() {
		super();
	}
	/**
     * Get the operation name of the current operation
     *
     * @return String - the operation name of the current operation
     */
    public String getOperationName() {
        return ICMSConstant.ACTION_MAKER_CREATE_EXCLUDED_FACILITY;
    }
    /**
     * Pre process.
     * Prepares the transaction object for persistance
     * Get the parent  transaction ID to be appended as trx parent ref
     *
     * @param anITrxValue is of type ITrxValue
     * @return ITrxValue
     * @throws com.integrosys.base.businfra.transaction.TrxOperationException
     *          on error
     */
    public ITrxValue preProcess(ITrxValue anITrxValue) throws TrxOperationException {
        anITrxValue = super.preProcess(anITrxValue);
        IExcludedFacilityTrxValue trxValue = getExcludedFacilityTrxValue(anITrxValue);
        IExcludedFacility staging = trxValue.getStagingExcludedFacility();
        try {
//            if (staging != null) {
//                
//
//                if (staging.getId() != ICMSConstant.LONG_INVALID_VALUE) {
//                	ICMSTrxValue parentTrx = getTrxManager().getTrxByRefIDAndTrxType(String.valueOf(staging.getId()), ICMSConstant.INSTANCE_FACILITY_NEW_MASTER);
//                    trxValue.setTrxReferenceID(parentTrx.getTransactionID());
//                }
//                
//            }else{
//            	throw new TrxOperationException("Staging value is null.");
//            }
            return trxValue;
        }
        
        catch (Exception ex) {
            throw new TrxOperationException("Exception in preProcess: " + ex.getMessage());
        }
    }
    /**
     * Process the transaction
     * 1.	Create the staging data
     * 2.	Update the transaction record
     *
     * @param anITrxValue of ITrxValue type
     * @return ITrxResult - the transaction result
     * @throws TrxOperationException if encounters any error during the processing of the transaction
     */
    public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException {
        IExcludedFacilityTrxValue idxTrxValue = getExcludedFacilityTrxValue(anITrxValue);
        IExcludedFacility stage = idxTrxValue.getStagingExcludedFacility();
        IExcludedFacility replicatedExcludedFacility = ExcludedFacilityReplicationUtils.replicateExcludedFacilityForCreateStagingCopy(stage);
     //   replicatedFacilityNewMaster.getSystemBankCode().setId(stage.getSystemBankCode().getId());
        idxTrxValue.setStagingExcludedFacility(replicatedExcludedFacility);

        IExcludedFacilityTrxValue trxValue = createStagingExcludedFacility(idxTrxValue);
        trxValue = updateExcludedFacilityTrx(trxValue);
        return super.prepareResult(trxValue);
    }
}
