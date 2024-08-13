/*
 * Copyright Integro Technologies Pte Ltd
 * $Header: /home/cms2/cvsroot/cms2/src/com/integrosys/cms/app/custodian/trx/ApproveAuthzRelodgeCustodianDocTrxOperation.java,v 1.1 2004/05/10 03:14:00 btan Exp $
 */
package com.integrosys.cms.app.custodian.trx;

//app
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * This operation is responsible for the approval of custodian doc relodge
 * authorization
 * 
 * @author $Author: btan $
 * @version $Revision: 1.1 $
 * @since $Date: 2004/05/10 03:14:00 $ Tag: $Name: $
 */
public class ApproveAuthzRelodgeCustodianDocTrxOperation extends ApproveUpdateCustodianDocTrxOperation {
	/**
	 * Default Constructor
	 */
	public ApproveAuthzRelodgeCustodianDocTrxOperation() {
		super();
	}

	/**
	 * Get the operation name of the current operation
	 * @return String - the operation name of the current operation
	 */
	public String getOperationName() {
		return ICMSConstant.ACTION_APPROVE_AUTHZ_RELODGE_CUSTODIAN_DOC;
	}
}
