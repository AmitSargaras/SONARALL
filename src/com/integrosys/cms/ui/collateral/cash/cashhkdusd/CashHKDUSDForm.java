//GENERATED FILE... ANYMODIFICATION WILL BE LOST. ASK SATHISH FOR ANY CLARIFICATION
package com.integrosys.cms.ui.collateral.cash.cashhkdusd;

import java.io.Serializable;

import com.integrosys.cms.ui.collateral.cash.CashForm;

/**
 * Created by IntelliJ IDEA. User: ssathish Date: Jun 20, 2003 Time: 2:56:38 PM
 * To change this template use Options | File Templates.
 */
public class CashHKDUSDForm extends CashForm implements Serializable {

	public static final String CASHHKDUSDMAPPER = "com.integrosys.cms.ui.collateral.cash.cashhkdusd.CashHKDUSDMapper";

	private String minimalFSV;

	public String getMinimalFSV() {
		return this.minimalFSV;
	}

	public void setMinimalFSV(String minimalFSV) {
		this.minimalFSV = minimalFSV;
	}

	public void reset() {
		super.reset();

	}

	/**
	 * This method defines a String array which tells what object is to be
	 * formed from the form and using what mapper classes to form it. it has a
	 * syntax (key, Mapperclassname)
	 * 
	 * @return One-dimesnional String Array
	 */
	public String[][] getMapper() {

		String[][] input = { { "form.collateralObject", CASHHKDUSDMAPPER },
				{ "theOBTrxContext", "com.integrosys.cms.ui.common.TrxContextMapper" }, };
		return input;
	}

}
