package com.integrosys.cms.app.systemBankBranch.trx;

import com.integrosys.base.businfra.transaction.ITrxResult;
import com.integrosys.base.businfra.transaction.ITrxValue;
import com.integrosys.base.businfra.transaction.TrxOperationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.systemBankBranch.bus.ISystemBankBranch;
import com.integrosys.cms.app.systemBankBranch.bus.OBSystemBankBranch;

/**
 * @author abhijit.rudrakshawar
 *Maker Edit Rejected operation to   update rejected record by checker
 */
public class MakerEditRejectedUpdateSystemBankBranchOperation extends AbstractSystemBankBranchTrxOperation{
    /**
        * Defaulc Constructor
        */
        public MakerEditRejectedUpdateSystemBankBranchOperation()
        {
            super();
        }

        /**
        * Get the operation name of the current operation
        *
        * @return String - the operation name of the current operation
        */
        public String getOperationName()
        {
            return ICMSConstant.ACTION_MAKER_EDIT_REJECTED_UPDATE_SYSTEM_BANK_BRANCH;
        }

        /**
        * Process the transaction
        * 1.    Create Staging record
        * 2.    Update the transaction record
        * @param anITrxValue - ITrxValue
        * @return ITrxResult - the transaction result
        * @throws com.integrosys.base.businfra.transaction.TrxOperationException if encounters any error during the processing of the transaction
        */
        public ITrxResult performProcess(ITrxValue anITrxValue) throws TrxOperationException
        {
            ISystemBankBranchTrxValue idxTrxValue = getSystemBankBranchTrxValue(anITrxValue);
            ISystemBankBranch stage = idxTrxValue.getStagingSystemBankBranch();
            ISystemBankBranch replicatedSystemBankBranch= new OBSystemBankBranch();
            replicatedSystemBankBranch.setId(stage.getId());
            replicatedSystemBankBranch.setAddress(stage.getAddress());
            replicatedSystemBankBranch.setCityTown(stage.getCityTown());
            replicatedSystemBankBranch.setContactMail(stage.getContactMail());
            replicatedSystemBankBranch.setContactNumber(stage.getContactNumber());
            replicatedSystemBankBranch.setCountry(stage.getCountry());
            replicatedSystemBankBranch.setCreateBy(stage.getCreateBy());
            replicatedSystemBankBranch.setCreationDate(stage.getCreationDate());
            replicatedSystemBankBranch.setDeprecated(stage.getDeprecated());
            replicatedSystemBankBranch.setLastUpdateBy(stage.getLastUpdateBy());
            replicatedSystemBankBranch.setLastUpdateDate(stage.getLastUpdateDate());
            replicatedSystemBankBranch.setMasterId(stage.getMasterId());
            replicatedSystemBankBranch.setRegion(stage.getRegion());
            replicatedSystemBankBranch.setState(stage.getState());
            replicatedSystemBankBranch.setStatus(stage.getStatus());
            replicatedSystemBankBranch.setSystemBankBranchCode(stage.getSystemBankBranchCode());
            replicatedSystemBankBranch.setSystemBankBranchName(stage.getSystemBankBranchName());
            replicatedSystemBankBranch.setRbiCode(stage.getRbiCode());
            replicatedSystemBankBranch.setFaxNumber(stage.getFaxNumber());
            replicatedSystemBankBranch.setIsHub(stage.getIsHub());
            replicatedSystemBankBranch.setIsVault(stage.getIsVault());
            replicatedSystemBankBranch.setCustodian1(stage.getCustodian1());
            replicatedSystemBankBranch.setCustodian2(stage.getCustodian2());
            replicatedSystemBankBranch.setLinkedHub(stage.getLinkedHub());
            
            replicatedSystemBankBranch.setSystemBankCode(stage.getSystemBankCode());
            //replicatedSystemBankBranch.setSystemBankName(stage.getSystemBankName());
            replicatedSystemBankBranch.setVersionTime(stage.getVersionTime());
            replicatedSystemBankBranch.setPincode(stage.getPincode());
            
            idxTrxValue.setStagingSystemBankBranch(replicatedSystemBankBranch);

            ISystemBankBranchTrxValue trxValue = createStagingSystemBankBranch(idxTrxValue);
            trxValue = updateSystemBankBranchTrx(trxValue);
            return super.prepareResult(trxValue);
        }

}
