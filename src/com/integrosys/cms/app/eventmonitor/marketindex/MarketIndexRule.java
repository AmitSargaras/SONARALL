/**
 * Copyright Integro Technologies Pte Ltd
 * $Header: /home/cms2/cvsroot/cms2/src/com/integrosys/cms/app/eventmonitor/marketindex/MarketIndexRule.java,v 1.4 2003/09/19 09:15:00 phtan Exp $
 */

package com.integrosys.cms.app.eventmonitor.marketindex;

import java.util.Locale;

import com.integrosys.base.techinfra.mapper.MapperUtil;
import com.integrosys.cms.app.eventmonitor.IMonRule;
import com.integrosys.cms.app.eventmonitor.IMonitorConstant;
import com.integrosys.cms.app.eventmonitor.IRuleParam;

/**
 * @author $Author: phtan $
 * @version $Revision: 1.4 $ Always return true
 */
public class MarketIndexRule implements IMonRule, java.io.Serializable {
	public String evaluateRule(IRuleParam ruleParam, Object anObject) {

		OBMarketIndexInfo info = (OBMarketIndexInfo) anObject;
		double d1 = info.getCurrentIndex();
		double d2 = info.getOldIndex();
		OBPercentRuleParam param = (OBPercentRuleParam) ruleParam;
		double percentChange = (d1 - d2) / d2;
		if (percentChange >= (param.getPercent() / 100.0)) {
			info.setPercentChange(MapperUtil.mapDoubleToString(percentChange * 100, 2, Locale.US));
			return IMonitorConstant.EVENT_TRUE;
		}

		return IMonitorConstant.EVENT_NONE;
	}
}
