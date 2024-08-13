package com.integrosys.cms.app.geography.country.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.geography.country.bus.CountryReplicationUtils;
import com.integrosys.cms.app.geography.country.bus.ICountry;
import com.integrosys.cms.app.geography.country.trx.AbstractCountryTrxOperation;
import com.integrosys.cms.app.geography.country.trx.ICountryTrxValue;
import com.integrosys.cms.app.transaction.ICMSTrxValue;

public class MakerDeleteCountryOperation  extends AbstractCountryTrxOperation{

	/**
     * Defaulc Constructor
     */
    public MakerDeleteCountryOperation() {
        super();
    }

    /**
     * Get the operation name of the current operation
     *
     * @return String - the operation name of the current operation
     */
    public String getOperationName() {
        return ICMSConstant.ACTION_MAKER_DELETE_COUNTRY;
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
        ICountryTrxValue trxValue = getCountryTrxValue(anITrxValue);
        ICountry staging = trxValue.getStagingCountry();
        try {
            if (staging != null) {
                if (staging.getIdCountry() != ICMSConstant.LONG_INVALID_VALUE) {
                	ICMSTrxValue parentTrx = getTrxManager().getTrxByRefIDAndTrxType(String.valueOf(staging.getIdCountry()), ICMSConstant.INSTANCE_COUNTRY);
                    trxValue.setTrxReferenceID(parentTrx.getTransactionID());
                }
                
            }else{
            	throw new TrxOperationException("Staging Value is null");
            }
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
        ICountryTrxValue idxTrxValue = getCountryTrxValue(anITrxValue);
        ICountry stage = idxTrxValue.getStagingCountry();
        ICountry replicatedCountry = CountryReplicationUtils.replicateCountryForCreateStagingCopy(stage);
   
        idxTrxValue.setStagingCountry(replicatedCountry);

        ICountryTrxValue trxValue = createStagingCountry(idxTrxValue);
        trxValue = updateCountryTrx(trxValue);
        return super.prepareResult(trxValue);
    }
}
