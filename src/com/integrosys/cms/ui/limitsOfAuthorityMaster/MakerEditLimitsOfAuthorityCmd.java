package com.integrosys.cms.ui.limitsOfAuthorityMaster;

import java.util.Collection;
import java.util.HashMap;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.cms.app.limitsOfAuthorityMaster.bus.ILimitsOfAuthorityMaster;
import com.integrosys.cms.app.limitsOfAuthorityMaster.bus.LimitsOfAuthorityMasterException;
import com.integrosys.cms.app.limitsOfAuthorityMaster.bus.OBLimitsOfAuthorityMaster;
import com.integrosys.cms.app.limitsOfAuthorityMaster.proxy.IProxyManager;
import com.integrosys.cms.app.limitsOfAuthorityMaster.trx.ILimitsOfAuthorityMasterTrxValue;
import com.integrosys.cms.app.limitsOfAuthorityMaster.trx.OBLimitsOfAuthorityMasterTrxValue;
import com.integrosys.cms.app.transaction.ICMSTrxValue;
import com.integrosys.cms.app.transaction.OBTrxContext;

public class MakerEditLimitsOfAuthorityCmd extends AbstractCommand implements ICommonEventConstant,
ILimitsOfAuthorityMasterConstant {

	private IProxyManager proxyManager;
	
	public IProxyManager getProxyManager() {
		return proxyManager;
	}

	public void setProxyManager(IProxyManager proxyManager) {
		this.proxyManager = proxyManager;
	}
	
	public MakerEditLimitsOfAuthorityCmd() {
	}
	
	public String[][] getParameterDescriptor() {
		return (new String[][] {
			{ LIMITS_OF_AUTHORITY_TRX_VAL, ILimitsOfAuthorityMasterTrxValue.class.getName(), SERVICE_SCOPE },
			{ TRX_CONTEXT, OBTrxContext.class.getName(), FORM_SCOPE },
			{ "remarks", String.class.getName(), REQUEST_SCOPE },
			{ "event", String.class.getName(), REQUEST_SCOPE },
			{ LIMITS_OF_AUTHORITY_OBJ, ILimitsOfAuthorityMaster.class.getName(), FORM_SCOPE },	
		});
	}
	
	public String[][] getResultDescriptor() {
		return (new String[][] {
			{ "request.ITrxValue", ICMSTrxValue.class.getName(), REQUEST_SCOPE } 
			});
	}

	public HashMap doExecute(HashMap map) throws CommandProcessingException, CommandValidationException {
		HashMap returnMap = new HashMap();
		HashMap resultMap = new HashMap();
		HashMap exceptionMap = new HashMap();
		try {
			String event = (String) map.get("event");
			ILimitsOfAuthorityMaster obj = (ILimitsOfAuthorityMaster) map.get(LIMITS_OF_AUTHORITY_OBJ);
			OBTrxContext ctx = (OBTrxContext) map.get(TRX_CONTEXT);
			ILimitsOfAuthorityMasterTrxValue trxValueIn = (ILimitsOfAuthorityMasterTrxValue) map.get(LIMITS_OF_AUTHORITY_TRX_VAL);
			ILimitsOfAuthorityMasterTrxValue trxValueOut = new OBLimitsOfAuthorityMasterTrxValue();
			
			SearchResult loaMasterList = getProxyManager().getAllActual();
			Collection<OBLimitsOfAuthorityMaster> resultList = loaMasterList.getResultList();
			
			exceptionMap = LimitsOfAuthorityMasterHelper.validateLoaMaster(resultList, (OBLimitsOfAuthorityMaster) obj ,exceptionMap, trxValueIn.getStaging());
			
			if(!exceptionMap.isEmpty()) {
				resultMap.put("request.ITrxValue", null);
				returnMap.put(ICommonEventConstant.COMMAND_RESULT_MAP, resultMap);
				returnMap.put(ICommonEventConstant.COMMAND_EXCEPTION_MAP, exceptionMap);
				return returnMap;
			}
		
			if(trxValueIn.getFromState().equals("PENDING_PERFECTION")){
				trxValueOut = getProxyManager().makerUpdateSaveCreate(ctx, trxValueIn,obj);
			}
			else{
				if(LimitsOfAuthorityMasterAction.EVENT_MAKER_EDIT_LIMITS_OF_AUTHORITY.equals(event) || 
						LimitsOfAuthorityMasterAction.EVENT_MAKER_EDIT_UPDATE_LIMITS_OF_AUTHORITY.equals(event) ||
						LimitsOfAuthorityMasterAction.EVENT_MAKER_SAVE_UPDATE.equals(event)){
					trxValueOut = getProxyManager().makerUpdate(ctx, trxValueIn,obj);
				}
			}
			
			resultMap.put("request.ITrxValue", trxValueOut);
		} catch (LimitsOfAuthorityMasterException ex) {
			DefaultLogger.debug(this, "got exception in doExecute" + ex);
			ex.printStackTrace();
			throw (new CommandProcessingException(ex.getMessage()));
		} catch (TransactionException e) {
			DefaultLogger.debug(this, "got exception in doExecute" + e);
			throw (new CommandProcessingException(e.getMessage()));
		} catch (Exception e) {
			DefaultLogger.debug(this, "got exception in doExecute" + e);
			e.printStackTrace();
			throw (new CommandProcessingException(e.getMessage()));
		}
		
		returnMap.put(ICommonEventConstant.COMMAND_RESULT_MAP, resultMap);
		return returnMap;
	}

}