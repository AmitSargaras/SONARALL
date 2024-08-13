/*
 * Copyright Integro Technologies Pte Ltd
 * $Header: $
 */
package com.integrosys.cms.app.creditriskparam.trx.entitylimit;

import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;

/**
 * This operation class is invoked by a checker to approve Entity Limit updated by a maker.
 *
 * @author   $Author: skchai $<br>
 * @version  $Revision: $
 * @since    $Date: $
 * Tag:      $Name: $
 */
public class CheckerApproveUpdateEntityLimitOperation extends AbstractEntityLimitTrxOperation
{
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor.
     */
    public CheckerApproveUpdateEntityLimitOperation()
    {}

    /**
     * Returns the Operation Name
     *
     * @return String
     */
    public String getOperationName() {
        return ICMSConstant.ACTION_CHECKER_APPROVE_ENTITY_LIMIT;
    }

    /**
     * The following tasks are performed:
     *
     * 1. update actual entityLimits record
     * 2. update Transaction record
     *
     * @param value is of type ITrxValue
     * @return transaction result
     * @throws TrxOperationException on error updating the transaction
     */
    public ITrxResult performProcess (ITrxValue value) throws TrxOperationException
    {
        try {
	        IEntityLimitTrxValue trxValue = super.getEntityLimitTrxValue (value);
				
			if ( trxValue.getEntityLimit() == null || trxValue.getEntityLimit().length == 0 ) {

            	trxValue = super.createActualEntityLimit (trxValue);
			}
			else
			{
				trxValue = super.updateActualEntityLimit (trxValue);
	        }
			trxValue = super.updateTransaction (trxValue);

	        return super.prepareResult(trxValue);
	    }
	    catch (TrxOperationException e) {
	        throw e;
	    }
	    catch (Exception e) {
	        throw new TrxOperationException ("Exception caught!", e);
	    }
    }
}
