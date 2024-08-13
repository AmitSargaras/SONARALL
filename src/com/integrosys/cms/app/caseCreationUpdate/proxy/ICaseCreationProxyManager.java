package com.integrosys.cms.app.caseCreationUpdate.proxy;

import java.util.ArrayList;
import java.util.List;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.ejbsupport.ConcurrentUpdateException;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.cms.app.fileInsertMapper.bus.IFileMapperMaster;
import com.integrosys.cms.app.fileInsertMapper.bus.OBFileMapperID;
import com.integrosys.cms.app.caseCreationUpdate.bus.CaseCreationException;
import com.integrosys.cms.app.fileInsertMapper.bus.OBFileMapperMaster;
import com.integrosys.cms.app.geography.bus.NoSuchGeographyException;
import com.integrosys.cms.app.caseCreationUpdate.bus.ICaseCreation;
import com.integrosys.cms.app.caseCreationUpdate.trx.ICaseCreationTrxValue;
import com.integrosys.cms.app.transaction.ITrxContext;

/**
 * This interface defines the list of attributes that will be available to the
 * generation of a diary item
 * 
 * @author $Author: jtan $<br>
 * @version $Revision: 1.6 $
 * @since $Date: 2004/06/29 10:03:55 $ Tag: $Name: $
 */
public interface ICaseCreationProxyManager {

	public List searchCaseCreation(String login) throws CaseCreationException,TrxParameterException,TransactionException;
	public SearchResult getAllActualCaseCreation(long id) throws CaseCreationException,TrxParameterException,TransactionException;
	public SearchResult getAllActualCaseCreation() throws CaseCreationException,TrxParameterException,TransactionException;
	public SearchResult getAllActualCaseCreationBranchMenu(String branchCode) throws CaseCreationException,TrxParameterException,TransactionException;
	public SearchResult getAllActual(String searchBy,String searchText) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreation deleteCaseCreation(ICaseCreation caseCreationUpdate) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerCloseRejectedCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerCloseDraftCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreation getCaseCreationById(long id) throws CaseCreationException,TrxParameterException,TransactionException ;
	public ICaseCreation updateCaseCreation(ICaseCreation caseCreationUpdate) throws CaseCreationException,TrxParameterException,TransactionException,ConcurrentUpdateException;
	public ICaseCreationTrxValue makerDeleteCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICCCaseCreationTrxValue, ICaseCreation anICCCaseCreation)throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerUpdateCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICCCaseCreationTrxValue, ICaseCreation anICCCaseCreation) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerUpdateCaseCreationBranch(ITrxContext anITrxContext, ICaseCreationTrxValue anICCCaseCreationTrxValue, ICaseCreation anICCCaseCreation) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerUpdateSaveUpdateCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICCCaseCreationTrxValue, ICaseCreation anICCCaseCreation)
	throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerUpdateSaveCreateCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICCCaseCreationTrxValue, ICaseCreation anICCCaseCreation)
	throws CaseCreationException,TrxParameterException,TransactionException;
	
	public ICaseCreationTrxValue makerEditRejectedCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue, ICaseCreation anCaseCreation) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerEditRejectedCaseCreationBranch(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue, ICaseCreation anCaseCreation) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue getCaseCreationTrxValue(long aCaseCreationId) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue getCaseCreationByTrxID(String aTrxID) throws CaseCreationException,TransactionException,CommandProcessingException;
	public ICaseCreationTrxValue checkerApproveCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue checkerRejectCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerCreateCaseCreation(ITrxContext anITrxContext, ICaseCreation anICCCaseCreation)throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerSaveCaseCreation(ITrxContext anITrxContext, ICaseCreation anICCCaseCreation)throws CaseCreationException,TrxParameterException,TransactionException;
	
	public boolean isPrevFileUploadPending() throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerInsertMapperCaseCreation(ITrxContext anITrxContext, OBFileMapperID obFileMapperID)throws CaseCreationException,TrxParameterException,TransactionException;
	public int insertCaseCreation(IFileMapperMaster fileMapperMaster, String userName, ArrayList resultList) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue getInsertFileByTrxID(String aTrxID) throws CaseCreationException,TransactionException,CommandProcessingException;
	public List getAllStage(String searchBy, String login) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue checkerApproveInsertCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue) throws CaseCreationException,TrxParameterException,TransactionException;
	public List getFileMasterList(String searchBy) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreation insertActualCaseCreation(String sysId) throws CaseCreationException,TrxParameterException,TransactionException,ConcurrentUpdateException;
	public ICaseCreationTrxValue checkerCreateCaseCreation(ITrxContext anITrxContext,ICaseCreation anICCCaseCreation, String refStage)throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue checkerRejectInsertCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue) throws CaseCreationException,TrxParameterException,TransactionException;
	public ICaseCreationTrxValue makerInsertCloseRejectedCaseCreation(ITrxContext anITrxContext, ICaseCreationTrxValue anICaseCreationTrxValue) throws CaseCreationException,TrxParameterException,TransactionException;
	public List getCaseCreationByBranchCode(String branchCode) ;
	public void deleteTransaction(OBFileMapperMaster obFileMapperMaster) throws NoSuchGeographyException,TrxParameterException,TransactionException;
}
