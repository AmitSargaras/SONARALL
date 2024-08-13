package com.integrosys.cms.app.collateralrocandinsurance.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.collateralrocandinsurance.bus.CollateralRocReplicationUtils;
import com.integrosys.cms.app.collateralrocandinsurance.bus.ICollateralRoc;
import com.integrosys.cms.app.common.constant.ICMSConstant;

public class MakerUpdateCollateralRocOperation extends AbstractCollateralRocTrxOperation{

	/**
     * Defaulc Constructor
     */
	public MakerUpdateCollateralRocOperation() {
		super();
	}
	/**
     * Get the operation name of the current operation
     *
     * @return String - the operation name of the current operation
     */
    public String getOperationName() {
        return ICMSConstant.ACTION_MAKER_UPDATE_COLLATERAL_ROC;
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
        ICollateralRocTrxValue trxValue = getCollateralRocTrxValue(anITrxValue);
        ICollateralRoc staging = trxValue.getStagingCollateralRoc();
       
            return trxValue;
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
        ICollateralRocTrxValue idxTrxValue = getCollateralRocTrxValue(anITrxValue);
        ICollateralRoc stage = idxTrxValue.getStagingCollateralRoc();
        ICollateralRoc replicatedCollateralRoc = CollateralRocReplicationUtils.replicateCollateralRocForCreateStagingCopy(stage);
     //   replicatedFacilityNewMaster.getSystemBankCode().setId(stage.getSystemBankCode().getId());
        idxTrxValue.setStagingCollateralRoc(replicatedCollateralRoc);

        ICollateralRocTrxValue trxValue = createStagingCollateralRoc(idxTrxValue);
        trxValue = updateCollateralRocTrx(trxValue);
        return super.prepareResult(trxValue);
    }
}
