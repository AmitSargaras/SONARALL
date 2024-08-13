package com.integrosys.cms.app.excLineforstpsrm.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.excLineforstpsrm.bus.IExcLineForSTPSRM;
import com.integrosys.cms.app.excLineforstpsrm.bus.ReplicationUtils;

public class MakerUpdateDraftCreateOperation extends AbstractTrxOperation  {

	public MakerUpdateDraftCreateOperation() {
		super();
	}
	
    public String getOperationName() {
        return ICMSConstant.ACTION_MAKER_CREATE_EXC_LINE_FR_STP_SRM;
    }
    
    public ITrxValue preProcess(ITrxValue anITrxValue) throws TrxOperationException {
        anITrxValue = super.preProcess(anITrxValue);
        IExcLineForSTPSRMTrxValue trxValue = getTrxValue(anITrxValue);
        return trxValue;
    }
    
    public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException {
        IExcLineForSTPSRMTrxValue idxTrxValue = getTrxValue(anITrxValue);
        IExcLineForSTPSRM stage = idxTrxValue.getStaging();
        IExcLineForSTPSRM replicated = ReplicationUtils.replicateForCreateStagingCopy(stage);
        idxTrxValue.setStaging(replicated);
        IExcLineForSTPSRMTrxValue trxValue = createStaging(idxTrxValue);
        trxValue = updateTrx(trxValue);
        return super.prepareResult(trxValue);
    }

}