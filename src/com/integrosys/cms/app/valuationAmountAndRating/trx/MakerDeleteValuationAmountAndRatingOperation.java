package com.integrosys.cms.app.valuationAmountAndRating.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.transaction.ICMSTrxValue;
import com.integrosys.cms.app.valuationAmountAndRating.bus.IValuationAmountAndRating;
import com.integrosys.cms.app.valuationAmountAndRating.bus.ValuationAmountAndRatingReplicationUtils;

/**
 * @author dattatray.thorat
 * Maker delete operation to  delete Relationship Manager 
 */
public class MakerDeleteValuationAmountAndRatingOperation extends AbstractValuationAmountAndRatingTrxOperation {
    /**
     * Defaulc Constructor
     */
    public MakerDeleteValuationAmountAndRatingOperation() {
        super();
    }

    /**
     * Get the operation name of the current operation
     *
     * @return String - the operation name of the current operation
     */
    public String getOperationName() {
        return ICMSConstant.ACTION_MAKER_DELETE_VALUATION_AMOUNT_AND_RATING;
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
        IValuationAmountAndRatingTrxValue trxValue = getValuationAmountAndRatingTrxValue(anITrxValue);
        IValuationAmountAndRating staging = trxValue.getStagingValuationAmountAndRating();
        try {
//            if (staging != null) {
//                
//
//                if (staging.getId() != ICMSConstant.LONG_INVALID_VALUE) {
//                	ICMSTrxValue parentTrx = getTrxManager().getTrxByRefIDAndTrxType(String.valueOf(staging.getId()), ICMSConstant.INSTANCE_VALUATION_AMOUNT_AND_RATING);
//                    trxValue.setTrxReferenceID(parentTrx.getTransactionID());
//                }
//                
//            }else{
//            	throw new TrxOperationException("Staging Value is null");
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
        IValuationAmountAndRatingTrxValue idxTrxValue = getValuationAmountAndRatingTrxValue(anITrxValue);
        IValuationAmountAndRating stage = idxTrxValue.getStagingValuationAmountAndRating();
        IValuationAmountAndRating replicatedValuationAmountAndRating = ValuationAmountAndRatingReplicationUtils.replicateValuationAmountAndRatingForCreateStagingCopy(stage);
   
        idxTrxValue.setStagingValuationAmountAndRating(replicatedValuationAmountAndRating);

        IValuationAmountAndRatingTrxValue trxValue = createStagingValuationAmountAndRating(idxTrxValue);
        trxValue = updateValuationAmountAndRatingTrx(trxValue);
        return super.prepareResult(trxValue);
    }
}
