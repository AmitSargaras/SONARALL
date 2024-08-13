package com.integrosys.cms.app.collateral.bus.type.cash.subtype.cashfd;

import com.integrosys.base.techinfra.util.AccessorUtil;
import com.integrosys.cms.app.collateral.bus.OBCollateralSubType;
import com.integrosys.cms.app.collateral.bus.type.cash.OBCashCollateral;
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * This class represents Cash of Deposite Fixed Deposit.
 * 
 * @author $Author: Naveen $<br>
 * @version $Revision: 1.0 $
 * @since $Date: 2007/02/21 07:03:58 $ Tag: $Name: $
 */
public class OBCashFd extends OBCashCollateral implements ICashFd {
	/**
	 * Default Constructor.
	 */
	public OBCashFd() {
		super();
		super.setCollateralSubType(new OBCollateralSubType(ICMSConstant.COLTYPE_CASH_FD));
	}

	/**
	 * Construct the object from its interface.
	 * 
	 * @param obj is of type ICashFd
	 */
	public OBCashFd(ICashFd obj) {
		this();
		AccessorUtil.copyValue(obj, this);
	}

	/**
	 * Return a String representation of this object.
	 * 
	 * @return String
	 */
	public String toString() {
		return AccessorUtil.printMethodValue(this);
	}

	/**
	 * Test for equality.
	 * 
	 * @param obj is of type Object
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		else if (!(obj instanceof OBCashFd)) {
			return false;
		}
		else {
			if (obj.hashCode() == this.hashCode()) {
				return true;
			}
			else {
				return false;
			}
		}
	}
}