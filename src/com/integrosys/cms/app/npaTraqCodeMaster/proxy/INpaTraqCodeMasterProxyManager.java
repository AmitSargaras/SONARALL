package com.integrosys.cms.app.npaTraqCodeMaster.proxy;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.cms.app.npaTraqCodeMaster.bus.INpaTraqCodeMaster;
import com.integrosys.cms.app.npaTraqCodeMaster.bus.NpaTraqCodeMasterException;
import com.integrosys.cms.app.npaTraqCodeMaster.bus.OBNpaTraqCodeMaster;
import com.integrosys.cms.app.npaTraqCodeMaster.trx.INpaTraqCodeMasterTrxValue;
import com.integrosys.cms.app.transaction.ITrxContext;
import com.integrosys.cms.app.transaction.OBTrxContext;

public interface INpaTraqCodeMasterProxyManager {

	public SearchResult getAllActualNpaTraqCodeMaster() throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	
	public boolean isNpaTraqCodeUniqueJdbc(String securityType, String securitySubType, String propertyTypeDesc);
	
	public INpaTraqCodeMasterTrxValue makerCreateNpaTraqCodeMaster(ITrxContext anITrxContext, INpaTraqCodeMaster anICCNpaTraqCodeMaster)throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	
	public INpaTraqCodeMasterTrxValue getNpaTraqCodeMasterByTrxID(String aTrxID) throws NpaTraqCodeMasterException,TransactionException,CommandProcessingException;
	
	public INpaTraqCodeMasterTrxValue checkerApproveNpaTraqCodeMaster(ITrxContext anITrxContext, INpaTraqCodeMasterTrxValue anINpaTraqCodeMasterTrxValue) throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	
	public boolean isNpaTraqCodeUnique(String npaTraqCode, String securityType, String securitySubType, String propertyTypeDesc);
	
	public INpaTraqCodeMasterTrxValue makerUpdateSaveUpdateNpaTraqCodeMaster(ITrxContext anITrxContext, INpaTraqCodeMasterTrxValue anICCNpaTraqCodeMasterTrxValue, INpaTraqCodeMaster anICCNpaTraqCodeMaster)
			throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	
	public INpaTraqCodeMasterTrxValue makerSaveNpaTraqCodeMaster(ITrxContext anITrxContext, INpaTraqCodeMaster anICCNpaTraqCodeMaster)throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	//added by santosh
	public INpaTraqCodeMasterTrxValue getNpaTraqCodeMasterTrxValue(long productCode)
			throws NpaTraqCodeMasterException,TransactionException,CommandProcessingException;

	public INpaTraqCodeMasterTrxValue makerUpdateNpaTraqCodeMaster(OBTrxContext ctx, INpaTraqCodeMasterTrxValue trxValueIn,
			OBNpaTraqCodeMaster npaTraqCodeMaster)throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	
	public INpaTraqCodeMasterTrxValue checkerRejectNpaTraqCodeMaster(ITrxContext ctx, INpaTraqCodeMasterTrxValue trxValueIn)
			throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;

	public INpaTraqCodeMasterTrxValue makerEditRejectedNpaTraqCodeMaster(ITrxContext ctx, INpaTraqCodeMasterTrxValue trxValueIn,INpaTraqCodeMaster anICCNpaTraqCodeMaster)
			throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;

	public INpaTraqCodeMasterTrxValue makerCloseRejectedNpaTraqCodeMaster(ITrxContext ctx, INpaTraqCodeMasterTrxValue trxValueIn)
			throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;

	public INpaTraqCodeMasterTrxValue makerUpdateSaveCreateNpaTraqCodeMaster(ITrxContext ctx, INpaTraqCodeMasterTrxValue trxValueIn,INpaTraqCodeMaster anICCNpaTraqCodeMaster)
			throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	
	public INpaTraqCodeMasterTrxValue makerCloseDraftNpaTraqCodeMaster(ITrxContext ctx, INpaTraqCodeMasterTrxValue trxValueIn)
			throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	
	public INpaTraqCodeMasterTrxValue makerDeleteNpaTraqCodeMaster(ITrxContext ctx, INpaTraqCodeMasterTrxValue trxValueIn,INpaTraqCodeMaster anICCNpaTraqCodeMaster)
			throws NpaTraqCodeMasterException,TrxParameterException,TransactionException;
	
/*	public SearchResult getAllFilteredActualProductMaster(String code,String name)
			throws ProductMasterException,TrxParameterException,TransactionException;*/
}
