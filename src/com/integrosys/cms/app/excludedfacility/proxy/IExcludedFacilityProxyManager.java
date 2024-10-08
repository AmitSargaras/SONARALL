package com.integrosys.cms.app.excludedfacility.proxy;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.cms.app.excludedfacility.bus.ExcludedFacilityException;
import com.integrosys.cms.app.excludedfacility.bus.IExcludedFacility;
import com.integrosys.cms.app.excludedfacility.trx.IExcludedFacilityTrxValue;
import com.integrosys.cms.app.transaction.ITrxContext;

public interface IExcludedFacilityProxyManager {

	public SearchResult getAllActualExcludedFacility() throws ExcludedFacilityException,TrxParameterException,TransactionException;
	public SearchResult getSearchedExcludedFacility(String type,String text) throws ExcludedFacilityException,TrxParameterException,TransactionException;
	public IExcludedFacilityTrxValue makerCreateExcludedFacility(ITrxContext anITrxContext, IExcludedFacility anICCExcludedFacility)throws ExcludedFacilityException,TrxParameterException,TransactionException;
	public IExcludedFacilityTrxValue getExcludedFacilityByTrxID(String aTrxID) throws ExcludedFacilityException,TransactionException,CommandProcessingException;
	public IExcludedFacilityTrxValue checkerApproveExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anIExcludedFacilityTrxValue) throws ExcludedFacilityException,TrxParameterException,TransactionException;
	public IExcludedFacilityTrxValue checkerRejectExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anIExcludedFacilityTrxValue) throws ExcludedFacilityException,TrxParameterException,TransactionException;
	public IExcludedFacilityTrxValue makerSaveExcludedFacility(ITrxContext anITrxContext, IExcludedFacility anICCExcludedFacility)throws ExcludedFacilityException,TrxParameterException,TransactionException;
	
	public IExcludedFacilityTrxValue makerUpdateSaveUpdateExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anICCExcludedFacilityTrxValue, IExcludedFacility anICCExcludedFacility)
			throws ExcludedFacilityException,TrxParameterException,TransactionException;
	
	
	public IExcludedFacilityTrxValue makerEditRejectedExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anICCExcludedFacilityTrxValue, IExcludedFacility anExcludedFacility) throws ExcludedFacilityException,TrxParameterException,TransactionException;
	
	public IExcludedFacilityTrxValue makerDeleteExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anICCExcludedFacilityTrxValue, IExcludedFacility anICCExcludedFacility)throws ExcludedFacilityException,TrxParameterException,TransactionException;
	public IExcludedFacilityTrxValue makerUpdateSaveCreateExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anICCExcludedFacilityTrxValue, IExcludedFacility anICCExcludedFacility)
			throws ExcludedFacilityException,TrxParameterException,TransactionException;
	public IExcludedFacilityTrxValue makerUpdateExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anICCExcludedFacilityTrxValue, IExcludedFacility anICCExcludedFacility)
			throws ExcludedFacilityException,TrxParameterException,TransactionException;
	
	public IExcludedFacilityTrxValue getExcludedFacilityTrxValue(Long aExcludedFacilityId) throws ExcludedFacilityException,TrxParameterException,TransactionException;
	public IExcludedFacilityTrxValue makerCloseDraftExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anIExcludedFacilityTrxValue) throws ExcludedFacilityException,TrxParameterException,TransactionException;
	
	public IExcludedFacilityTrxValue makerCloseRejectedExcludedFacility(ITrxContext anITrxContext, IExcludedFacilityTrxValue anIExcludedFacilityTrxValue) throws ExcludedFacilityException,TrxParameterException,TransactionException;
	
	public boolean isExcludedFacilityDescriptionUnique(String excludedFacilityDescription);
	
}
