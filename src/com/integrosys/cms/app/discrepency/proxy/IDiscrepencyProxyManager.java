package com.integrosys.cms.app.discrepency.proxy;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.ejbsupport.ConcurrentUpdateException;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.cms.app.discrepency.bus.IDiscrepency;
import com.integrosys.cms.app.discrepency.bus.NoSuchDiscrepencyException;
import com.integrosys.cms.app.discrepency.trx.IDiscrepencyTrxValue;
import com.integrosys.cms.app.transaction.ITrxContext;

/**
 * 
 * @author sandiip.shinde
 * @since 01-06-2011
 */

public interface IDiscrepencyProxyManager {

	public SearchResult listDiscrepency(long customerId) throws NoSuchDiscrepencyException;
	
	public IDiscrepencyTrxValue makerCreateDiscrepency(ITrxContext anITrxContext, IDiscrepencyTrxValue anIDiscrepencyTrxValue, IDiscrepency anDiscrepency) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;

	public IDiscrepencyTrxValue getDiscrepencyById(long id) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;

	public IDiscrepency createDiscrepency(IDiscrepency discrepency ) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	 
	public IDiscrepency updateDiscrepency(IDiscrepency discrepency ) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException,ConcurrentUpdateException;
	
	public IDiscrepencyTrxValue makerUpdateDiscrepency(ITrxContext anITrxContext, IDiscrepencyTrxValue anICCDiscrepencyTrxValue, IDiscrepency anICCDiscrepency)throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue makerEditRejectedDiscrepency(ITrxContext anITrxContext, IDiscrepencyTrxValue anIDiscrepencyTrxValue, IDiscrepency anDiscrepency) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue getDiscrepencyTrxValue(long aDiscrepencyId) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue makerDeleteDiscrepency(ITrxContext anITrxContext, IDiscrepencyTrxValue anICCDiscrepencyTrxValue, IDiscrepency anICCDiscrepency)throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;

	public IDiscrepencyTrxValue makerActivateDiscrepency(ITrxContext anITrxContext, IDiscrepencyTrxValue anICCDiscrepencyTrxValue, IDiscrepency anICCDiscrepency)throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue getDiscrepencyByTrxID(String aTrxID) throws NoSuchDiscrepencyException,TransactionException,CommandProcessingException;
	
	public IDiscrepencyTrxValue checkerApproveDiscrepency(ITrxContext anITrxContext, IDiscrepencyTrxValue anIDiscrepencyTrxValue) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue checkerRejectDiscrepency(ITrxContext anITrxContext, IDiscrepencyTrxValue anIDiscrepencyTrxValue) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue makerCloseRejectedDiscrepency(ITrxContext anITrxContext, IDiscrepencyTrxValue anISystemBankTrxValue) throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue makerSaveDiscrepency(ITrxContext anITrxContext, IDiscrepency anICDiscrepency)throws NoSuchDiscrepencyException,TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue makerUpdateSaveCreateDiscrepency(ITrxContext anITrxContext,IDiscrepencyTrxValue anICCDiscrepencyTrxValue,IDiscrepency anICCDiscrepency)throws NoSuchDiscrepencyException, TrxParameterException,TransactionException;
	
	public IDiscrepencyTrxValue makerUpdateSaveUpdateDiscrepency(ITrxContext anITrxContext,IDiscrepencyTrxValue anICCDiscrepencyTrxValue,IDiscrepency anICCDiscrepency)throws NoSuchDiscrepencyException, TrxParameterException,TransactionException,ConcurrentUpdateException;
}
