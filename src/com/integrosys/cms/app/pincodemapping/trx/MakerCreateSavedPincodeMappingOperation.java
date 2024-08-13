package com.integrosys.cms.app.pincodemapping.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.pincodemapping.bus.IPincodeMapping;
import com.integrosys.cms.app.pincodemapping.bus.PincodeMappingReplicationUtils;

public class MakerCreateSavedPincodeMappingOperation extends
AbstractPincodeMappingTrxOperation {

	/**
	 * Defaulc Constructor
	 */
	public MakerCreateSavedPincodeMappingOperation() {
		super();
	}
	/**
	 * Get the operation name of the current operation
	 * 
	 * @return String - the operation name of the current operation
	 */
	public String getOperationName() {
		return ICMSConstant.ACTION_MAKER_CREATE_PINCODE_MAPPING;
	}
	/**
	 * Pre process. Prepares the transaction object for persistance Get the
	 * parent transaction ID to be appended as trx parent ref
	 * 
	 * @param anITrxValue
	 *            is of type ITrxValue
	 * @return ITrxValue
	 * @throws com.integrosys.base.businfra.transaction.TrxOperationException
	 *             on error
	 */
	public ITrxValue preProcess(ITrxValue anITrxValue)
			throws TrxOperationException {
		anITrxValue = super.preProcess(anITrxValue);
		IPincodeMappingTrxValue trxValue = getPincodeMappingTrxValue(anITrxValue);
		IPincodeMapping staging = trxValue.getStagingPincodeMapping();
		try {
			// if (staging != null) {
			//                
			//
			// if (staging.getId() != ICMSConstant.LONG_INVALID_VALUE) {
			// ICMSTrxValue parentTrx =
			// getTrxManager().getTrxByRefIDAndTrxType(String.valueOf(staging.getId()),
			// ICMSConstant.INSTANCE_SYSTEM_BANK_BRANCH);
			// trxValue.setTrxReferenceID(parentTrx.getTransactionID());
			// }
			//                
			// }else{
			// throw new TrxOperationException("Staging value is null.");
			// }
			return trxValue;
		}

		catch (Exception ex) {
			throw new TrxOperationException("Exception in preProcess: "
					+ ex.getMessage());
		}
	}
	/**
	 * Process the transaction 1. Create the staging data 2. Update the
	 * transaction record
	 * 
	 * @param anITrxValue
	 *            of ITrxValue type
	 * @return ITrxResult - the transaction result
	 * @throws TrxOperationException
	 *             if encounters any error during the processing of the
	 *             transaction
	 */
	public ITrxResult performProcess(ITrxValue anITrxValue)
			throws TrxOperationException {
		IPincodeMappingTrxValue idxTrxValue = getPincodeMappingTrxValue(anITrxValue);
		IPincodeMapping stage = idxTrxValue.getStagingPincodeMapping();
		IPincodeMapping replicatedPincodeMapping = PincodeMappingReplicationUtils
				.replicatePincodeMappingForCreateStagingCopy(stage);

		idxTrxValue.setStagingPincodeMapping(replicatedPincodeMapping);

		IPincodeMappingTrxValue trxValue = createStagingPincodeMapping(idxTrxValue);
		trxValue = updatePincodeMappingTrx(trxValue);
		return super.prepareResult(trxValue);
	}
}
