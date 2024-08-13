package com.integrosys.cms.app.relationshipmgr.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * @author dattatray.thorat
 * Maker Close operation to remove  create rejected by checker
 */

public class MakerCloseRejectedCreateRelationshipMgrOperation extends AbstractRelationshipMgrTrxOperation{

    private static final String DEFAULT_OPERATION_NAME = ICMSConstant.ACTION_MAKER_CLOSE_REJECTED_CREATE_RELATIONSHIP_MGR;

    private String operationName;

    /**
    * Defaulc Constructor
    */
    public MakerCloseRejectedCreateRelationshipMgrOperation()
    {
        operationName = DEFAULT_OPERATION_NAME;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
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
        IRelationshipMgrTrxValue trxValue = super.getRelationshipMgrTrxValue (anITrxValue);
        trxValue = updateRelationshipMgrTrx(trxValue);
        return super.prepareResult(trxValue);
    }
}
