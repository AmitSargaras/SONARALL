package com.integrosys.cms.app.collateralNewMaster.proxy;

import java.util.List;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.ejbsupport.ConcurrentUpdateException;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.cms.app.collateralNewMaster.bus.CollateralNewMasterException;
import com.integrosys.cms.app.collateralNewMaster.bus.ICollateralNewMaster;
import com.integrosys.cms.app.collateralNewMaster.trx.ICollateralNewMasterTrxValue;
import com.integrosys.cms.app.transaction.ITrxContext;

/**
 * This interface defines the list of attributes that will be available to the
 * generation of a diary item
 * 
 * @author $Author: jtan $<br>
 * @version $Revision: 1.6 $
 * @since $Date: 2004/06/29 10:03:55 $ Tag: $Name: $
 */
public interface ICollateralNewMasterProxyManager {

	public List searchCollateralNewMaster(String login) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public SearchResult getAllActualCollateralNewMaster() throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public SearchResult getAllActual(String searchBy,String searchText) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMaster deleteCollateralNewMaster(ICollateralNewMaster collateralNewMaster) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue makerCloseRejectedCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICollateralNewMasterTrxValue) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue makerCloseDraftCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICollateralNewMasterTrxValue) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMaster getCollateralNewMasterById(long id) throws CollateralNewMasterException,TrxParameterException,TransactionException ;
	public ICollateralNewMaster updateCollateralNewMaster(ICollateralNewMaster collateralNewMaster) throws CollateralNewMasterException,TrxParameterException,TransactionException,ConcurrentUpdateException;
	public ICollateralNewMasterTrxValue makerDeleteCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICCCollateralNewMasterTrxValue, ICollateralNewMaster anICCCollateralNewMaster)throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue makerUpdateCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICCCollateralNewMasterTrxValue, ICollateralNewMaster anICCCollateralNewMaster)
	throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue makerUpdateSaveUpdateCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICCCollateralNewMasterTrxValue, ICollateralNewMaster anICCCollateralNewMaster)
	throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue makerUpdateSaveCreateCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICCCollateralNewMasterTrxValue, ICollateralNewMaster anICCCollateralNewMaster)
	throws CollateralNewMasterException,TrxParameterException,TransactionException;
	
	public ICollateralNewMasterTrxValue makerEditRejectedCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICollateralNewMasterTrxValue, ICollateralNewMaster anCollateralNewMaster) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue getCollateralNewMasterTrxValue(long aCollateralNewMasterId) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue getCollateralNewMasterByTrxID(String aTrxID) throws CollateralNewMasterException,TransactionException,CommandProcessingException;
	public ICollateralNewMasterTrxValue checkerApproveCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICollateralNewMasterTrxValue) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue checkerRejectCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMasterTrxValue anICollateralNewMasterTrxValue) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue makerCreateCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMaster anICCCollateralNewMaster)throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public ICollateralNewMasterTrxValue makerSaveCollateralNewMaster(ITrxContext anITrxContext, ICollateralNewMaster anICCCollateralNewMaster)throws CollateralNewMasterException,TrxParameterException,TransactionException;
	
	public boolean isCollateraNameUnique(String collateralName);
	public SearchResult getFilteredCollateral(String collateralCode,
			String collateralDescription, String collateralMainType,
			String collateralSubType) throws CollateralNewMasterException,TrxParameterException,TransactionException;
	public boolean isDuplicateRecord(String cpsId);
}
