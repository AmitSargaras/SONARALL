/*
 * Copyright Integro Technologies Pte Ltd
 * $Header: /home/cms2/cvsroot/cms2/src/com/integrosys/cms/app/collateral/bus/type/marketable/subtype/govtforeignsameccy/OBGovtForeignSameCurrency.java,v 1.6 2003/08/23 08:26:27 lyng Exp $
 */
package com.integrosys.cms.app.collateral.bus.type.marketable.subtype.govtforeignsameccy;

import com.integrosys.base.techinfra.util.AccessorUtil;
import com.integrosys.cms.app.collateral.bus.OBCollateralSubType;
import com.integrosys.cms.app.collateral.bus.type.marketable.subtype.OBBondsCommon;
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * This class represents Marketable Equity of type Government Foreign - Same
 * Currency.
 * 
 * @author $Author: lyng $<br>
 * @version $Revision: 1.6 $
 * @since $Date: 2003/08/23 08:26:27 $ Tag: $Name: $
 */
public class OBGovtForeignSameCurrency extends OBBondsCommon implements IGovtForeignSameCurrency {
	/**
	 * Default Constructor.
	 */
	public OBGovtForeignSameCurrency() {
		super();
		super.setCollateralSubType(new OBCollateralSubType(ICMSConstant.COLTYPE_MAR_GOVT_FOREIGN_SAMECCY));
	}

	/**
	 * Construct the object from its interface.
	 * 
	 * @param obj is of type IGovtForeignSameCurrency
	 */
	public OBGovtForeignSameCurrency(IGovtForeignSameCurrency obj) {
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
		else if (!(obj instanceof OBGovtForeignSameCurrency)) {
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