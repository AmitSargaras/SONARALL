/**
 * Copyright Integro Technologies Pte Ltd
 * $Header:
 */

package com.integrosys.cms.ui.manualinput.partygroup;

import java.util.HashMap;

import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.cms.app.partygroup.bus.OBPartyGroup;
import com.integrosys.cms.app.partygroup.bus.PartyGroupException;
import com.integrosys.cms.app.partygroup.proxy.IPartyGroupProxyManager;
import com.integrosys.cms.app.partygroup.trx.IPartyGroupTrxValue;
import com.integrosys.cms.app.partygroup.trx.OBPartyGroupTrxValue;
import com.integrosys.cms.app.transaction.OBTrxContext;

/**
@author $Author: Bharat Waghela$
* Command for Save PartyGroup
 */
public class MakerSavePartyGroupCmd extends AbstractCommand implements ICommonEventConstant {
	
	
	private IPartyGroupProxyManager partyGroupProxy;

	public IPartyGroupProxyManager getPartyGroupProxy() {
		return partyGroupProxy;
	}

	public void setPartyGroupProxy(IPartyGroupProxyManager partyGroupProxy) {
		this.partyGroupProxy = partyGroupProxy;
	}
	
	
	/**
	 * Default Constructor
	 */
	
	
	public MakerSavePartyGroupCmd() {
	}

	/**
	 * Defines an two dimensional array with the parameter list to be passed to
	 * the doExecute method by a HashMap syntax for the array is
	 * (HashMapkey,classname,scope) The scope may be request,form or service
	 * 
	 * @return the two dimensional String array
	 */
	 public String[][] getParameterDescriptor() {
	        return (new String[][]{
	        		{"IPartyGroupTrxValue", "com.integrosys.cms.app.partygroup.trx.IPartyGroupTrxValue", SERVICE_SCOPE},
	                {"theOBTrxContext", "com.integrosys.cms.app.transaction.OBTrxContext", FORM_SCOPE},
	                {"remarks", "java.lang.String", REQUEST_SCOPE},
	                {"event", "java.lang.String", REQUEST_SCOPE},
	        		{ "partyGroupObj", "com.integrosys.cms.app.partygroup.bus.OBPartyGroup", FORM_SCOPE }
	               
	        }
	        );
	    }

	 public String[][] getResultDescriptor() {
			return (new String[][] { 
					{"request.ITrxValue", "com.integrosys.cms.app.transaction.ICMSTrxValue", REQUEST_SCOPE}
					   });
		}
	    /**
	     * This method does the Business operations  with the HashMap and put the results back into
	     * the HashMap.
	     *
	     * @param map is of type HashMap
	     * @return HashMap with the Result
	     */
	    public HashMap doExecute(HashMap map) throws CommandProcessingException, CommandValidationException {
			HashMap returnMap = new HashMap();
			HashMap resultMap = new HashMap();
			
			try {
				String event = (String) map.get("event");
				OBPartyGroup partyGroup = (OBPartyGroup) map.get("partyGroupObj");
				OBTrxContext ctx = (OBTrxContext) map.get("theOBTrxContext");
				IPartyGroupTrxValue trxValueIn = (IPartyGroupTrxValue)map.get("IPartyGroupTrxValue");
				IPartyGroupTrxValue trxValueOut = new OBPartyGroupTrxValue();
				 if(event.equals("maker_create_draft_party_group")){
					trxValueOut = getPartyGroupProxy().makerCreateSavePartyGroup(ctx, partyGroup);
					
				 }
				else if(event.equals("maker_edit_draft_party_group")){
					trxValueOut = getPartyGroupProxy().makerEditSaveUpdatePartyGroup(ctx, trxValueIn, partyGroup);		
				}else{
				
				trxValueOut = getPartyGroupProxy().makerSavePartyGroup(ctx,partyGroup);
				}
				 resultMap.put("request.ITrxValue", trxValueOut);
			}catch (PartyGroupException ex) {
				DefaultLogger.debug(this, "got exception in doExecute" + ex);
				ex.printStackTrace();
				throw (new CommandProcessingException(ex.getMessage()));
			}
			catch (TransactionException e) {
				DefaultLogger.debug(this, "got exception in doExecute" + e);
				throw (new CommandProcessingException(e.getMessage()));
			}catch (Exception e) {
	            DefaultLogger.debug(this, "got exception in doExecute" + e);
	            e.printStackTrace();
	            throw (new CommandProcessingException(e.getMessage()));
	        }
			returnMap.put(ICommonEventConstant.COMMAND_RESULT_MAP, resultMap);
			return returnMap;
	    }


}
