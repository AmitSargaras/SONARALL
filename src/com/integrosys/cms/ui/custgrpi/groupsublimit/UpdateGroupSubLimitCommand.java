package com.integrosys.cms.ui.custgrpi.groupsublimit;

import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.cms.app.collateral.bus.type.guarantee.subtype.gtegovtlink.IFeeDetails;
import com.integrosys.cms.app.collateral.bus.type.guarantee.subtype.gtegovtlink.IGteGovtLink;
import com.integrosys.cms.app.collateral.trx.ICollateralTrxValue;
import com.integrosys.cms.app.custgrpi.bus.IGroupSubLimit;
import com.integrosys.cms.app.custgrpi.bus.ICustGrpIdentifier;
import com.integrosys.cms.app.custgrpi.trx.ICustGrpIdentifierTrxValue;
import com.integrosys.cms.app.custgrpi.trx.OBCustGrpIdentifierTrxValue;
import com.integrosys.cms.app.creditriskparam.bus.bankentitybranch.IBankEntityBranchParam;
import com.integrosys.cms.ui.custgrpi.CustGroupUIHelper;
import com.integrosys.cms.ui.custgrpi.CustGrpIdentifierUIHelper;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionMessage;

/**
 * Created by IntelliJ IDEA.
 * User: jitendra
 * Date: May 8, 2007
 * Time: 7:23:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpdateGroupSubLimitCommand extends AbstractCommand {


    public String[][] getParameterDescriptor() {
        return (new String[][]{
                {CustGroupUIHelper.service_groupTrxValue, "com.integrosys.cms.app.custgrpi.trx.ICustGrpIdentifierTrxValue", SERVICE_SCOPE},
                {CustGroupUIHelper.form_groupSubLimitObj, "com.integrosys.cms.app.custgrpi.bus.IGroupSubLimit", FORM_SCOPE},
                {"itemType", "java.lang.String", REQUEST_SCOPE},
                {"indexID", "java.lang.String", REQUEST_SCOPE},
        });
    }

    /**
     * Defines an two dimensional array with the result list to be
     * expected as a result from the doExecute method using a HashMap
     * syntax for the array is (HashMapkey,classname,scope)
     * The scope may be request,form or service
     *
     * @return the two dimensional String array
     */
    public String[][] getResultDescriptor() {
        return (new String[][]{
                {CustGroupUIHelper.service_groupTrxValue, "com.integrosys.cms.app.custgrpi.trx.ICustGrpIdentifierTrxValue", SERVICE_SCOPE},
                {"itemType", "java.lang.String", REQUEST_SCOPE},
                {"indexID", "java.lang.String", REQUEST_SCOPE},
                {"from_event", "java.lang.String", REQUEST_SCOPE},
        });
    }

    /**
     * This method does the Business operations  with the HashMap and put the results back into
     * the HashMap.Here reading for Company Borrower is done.
     *
     * @param map is of type HashMap
     * @return HashMap with the Result
     * @throws com.integrosys.base.uiinfra.exception.CommandProcessingException
     *          on errors
     * @throws com.integrosys.base.uiinfra.exception.CommandValidationException
     *          on errors
     */
    public HashMap doExecute(HashMap map) throws CommandProcessingException, CommandValidationException {

        DefaultLogger.debug(this, "inside doExecute ");

        HashMap result = new HashMap();
        HashMap exceptionMap = new HashMap();
        HashMap temp = new HashMap();

        IGroupSubLimit groupObj = (IGroupSubLimit) map.get(CustGroupUIHelper.form_groupSubLimitObj);
        ICustGrpIdentifierTrxValue trxValue = (ICustGrpIdentifierTrxValue) map.get(CustGroupUIHelper.service_groupTrxValue);
        if (trxValue == null) {
            trxValue = new OBCustGrpIdentifierTrxValue();
        }
        ICustGrpIdentifier stagingGroupObj = trxValue.getStagingCustGrpIdentifier();
        if (stagingGroupObj != null) {
            Debug("checkOBj.get = " + stagingGroupObj.getGroupName());
        } else {
            Debug("checkOBj.getGroupName is  null");
        }

        int index = 0;
        if (map.get("indexID") != null) {
            index = Integer.parseInt((String) map.get("indexID"));
        }

        DefaultLogger.debug(this, "Existing index " + index);

        //Andy Wong, 4 July 2008: validate for duplicate sub limit type and descr
        if (!ArrayUtils.isEmpty(stagingGroupObj.getGroupSubLimit())) {
            if(!groupObj.getDescription().equals(stagingGroupObj.getGroupSubLimit()[index].getDescription()))
            {
                for (int i = 0; i < stagingGroupObj.getGroupSubLimit().length; i++) {
                    IGroupSubLimit iGroupSubLimit = stagingGroupObj.getGroupSubLimit()[i];

                    if (iGroupSubLimit.getDescription().equals(groupObj.getDescription())) {
                        exceptionMap.put("desc", new ActionMessage("error.group.sub.limit.duplicate"));
                        break;
                    }

                }
            }
        }

        //derive limit amount for sub limit when GP5 or Capital Fund % selected
        if ((CustGroupUIHelper.INT_LMT_CAP_FUND_PERCENT.equals(stagingGroupObj.getInternalLmt())
                || CustGroupUIHelper.INT_LMT_GP5_REQ.equals(stagingGroupObj.getInternalLmt())) && CustGroupUIHelper.INT_LMT_SUBTYP_AB_ENTITY.equals(groupObj.getSubLimitTypeCD())) {
            groupObj.setLimitAmt(CustGrpIdentifierUIHelper.getGroupLimit(stagingGroupObj.getInternalLmt(), groupObj.getDescription()));
        }

        if(exceptionMap.size() == 0)
        {
            this.updateChildRecord(stagingGroupObj, groupObj, index);
            trxValue.setStagingCustGrpIdentifier(stagingGroupObj);
        }

        result.put(CustGroupUIHelper.service_groupTrxValue, trxValue);
        result.put("itemType", map.get("itemType"));

        DefaultLogger.debug(this, "Existing doExecute ");

        temp.put(ICommonEventConstant.COMMAND_RESULT_MAP, result);
        temp.put(ICommonEventConstant.COMMAND_EXCEPTION_MAP, exceptionMap);
        return temp;
    }


    private void updateChildRecord(ICustGrpIdentifier obj, IGroupSubLimit iFeeDetails, int index) {
        IGroupSubLimit[] existingArray = obj.getGroupSubLimit();
        existingArray[index] = iFeeDetails;
        obj.setGroupSubLimit(existingArray);
    }

    private void Debug(String msg) {
    	DefaultLogger.debug(this,"UpdateGroupSubLimitCommand = " + msg);
    }
}
