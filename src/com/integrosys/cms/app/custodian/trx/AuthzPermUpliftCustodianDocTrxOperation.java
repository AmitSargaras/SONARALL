/*
 * Copyright Integro Technologies Pte Ltd
 * $Header: /home/cms2/cvsroot/cms2/src/com/integrosys/cms/app/custodian/trx/AuthzPermUpliftCustodianDocTrxOperation.java,v 1.1 2003/06/23 06:39:40 hltan Exp $
 */
package com.integrosys.cms.app.custodian.trx;

//app
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * This operation is responsible for the authorization of perm uplifting of
 * custodian doc
 * 
 * @author $Author: hltan $
 * @version $Revision: 1.1 $
 * @since $Date: 2003/06/23 06:39:40 $ Tag: $Name: $
 */
public class AuthzPermUpliftCustodianDocTrxOperation extends UpdateCustodianDocTrxOperation {
	/**
	 * Default Constructor
	 */
	public AuthzPermUpliftCustodianDocTrxOperation() {
		super();
	}

	/**
	 * Get the operation name of the current operation This will overwrite the
	 * method from the super class
	 * @return String - the operation name of the current operation
	 */
	public String getOperationName() {
		return ICMSConstant.ACTION_AUTHZ_PERM_UPLIFT_CUSTODIAN_DOC;
	}
}
