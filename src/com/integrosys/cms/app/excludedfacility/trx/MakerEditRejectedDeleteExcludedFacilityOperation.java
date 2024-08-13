package com.integrosys.cms.app.excludedfacility.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.excludedfacility.bus.IExcludedFacility;
import com.integrosys.cms.app.excludedfacility.bus.OBExcludedFacility;

public class MakerEditRejectedDeleteExcludedFacilityOperation extends AbstractExcludedFacilityTrxOperation {

	/**
	 * Defaulc Constructor
	 */
	public MakerEditRejectedDeleteExcludedFacilityOperation() {
		super();
	}
	/**
     * Get the operation name of the current operation
     *
     * @return String - the operation name of the current operation
     */
     public String getOperationName()
     {
         return ICMSConstant.ACTION_MAKER_EDIT_REJECTED_DELETE_EXCLUDED_FACILITY;
     }
     /**
      * Process the transaction
      * 1.    Create Staging record
      * 2.    Update the transaction record
      * @param anITrxValue - ITrxValue
      * @return ITrxResult - the transaction result
      * @throws com.integrosys.base.businfra.transaction.TrxOperationException if encounters any error during the processing of the transaction
      */
      public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException
      {
    	  IExcludedFacilityTrxValue idxTrxValue = getExcludedFacilityTrxValue(anITrxValue);
          IExcludedFacility stage = idxTrxValue.getStagingExcludedFacility();
          IExcludedFacility replicatedExcludedFacility= new OBExcludedFacility();
          replicatedExcludedFacility.setId(stage.getId());
          /*replicatedExcludedFacility.setExcludedFacilityCategory(stage.getExcludedFacilityCategory());*/
          replicatedExcludedFacility.setExcludedFacilityDescription(stage.getExcludedFacilityDescription());
          
          
          replicatedExcludedFacility.setCreateBy(stage.getCreateBy());
          replicatedExcludedFacility.setCreationDate(stage.getCreationDate());
          replicatedExcludedFacility.setDeprecated(stage.getDeprecated());
          replicatedExcludedFacility.setLastUpdateBy(stage.getLastUpdateBy());
          replicatedExcludedFacility.setLastUpdateDate(stage.getLastUpdateDate());
          
          replicatedExcludedFacility.setStatus(stage.getStatus());
          replicatedExcludedFacility.setVersionTime(stage.getVersionTime());
          
          idxTrxValue.setStagingExcludedFacility(replicatedExcludedFacility);

          IExcludedFacilityTrxValue trxValue = createStagingExcludedFacility(idxTrxValue);
          trxValue = updateExcludedFacilityTrx(trxValue);
          return super.prepareResult(trxValue);
      }
}
