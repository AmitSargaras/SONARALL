/*
 * Copyright Integro Technologies Pte Ltd
 * $Header: /home/cms2/cvsroot/cms2/src/com/integrosys/cms/app/generatereq/proxy/SBGenerateRequestProxyManager.java,v 1.4 2003/09/22 02:23:34 hltan Exp $
 */
package com.integrosys.cms.app.generatereq.proxy;

//java
import java.rmi.RemoteException;

import javax.ejb.EJBObject;

import com.integrosys.cms.app.customer.bus.ICMSCustomer;
import com.integrosys.cms.app.generatereq.bus.GenerateRequestException;
import com.integrosys.cms.app.generatereq.bus.IDeferralRequest;
import com.integrosys.cms.app.generatereq.bus.IWaiverRequest;
import com.integrosys.cms.app.generatereq.trx.IDeferralRequestTrxValue;
import com.integrosys.cms.app.generatereq.trx.IWaiverRequestTrxValue;
import com.integrosys.cms.app.limit.bus.ILimitProfile;
import com.integrosys.cms.app.transaction.ITrxContext;

/**
 * Session bean remote interface for the services provided by the generate
 * request proxy manager
 * 
 * @author $Author: hltan $<br>
 * @version $Revision: 1.4 $
 * @since $Date: 2003/09/22 02:23:34 $ Tag: $Name: $
 */
public interface SBGenerateRequestProxyManager extends EJBObject {
	/**
	 * Get a new waiver request for a limit profile
	 * @param anILimitProfile of ILimitProfile type
	 * @return IWaiverRequestTrxValue - the waiver request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue getNewWaiverRequestTrxValue(ILimitProfile anILimitProfile, ICMSCustomer anICMSCustomer)
			throws GenerateRequestException, RemoteException;

	/**
	 * Get a new waiver request for a non borrower
	 * @param anICMSCustomer of ICMSCustomer type
	 * @return IWaiverRequestTrxValue - the waiver request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue getNewWaiverRequestTrxValue(ICMSCustomer anICMSCustomer)
			throws GenerateRequestException, RemoteException;

	/**
	 * Get a new deferral request for a limit profile
	 * @param anILimitProfile of ILimitProfile type
	 * @return IDeferralRequestTrxValue - the deferral request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue getNewDeferralRequestTrxValue(ILimitProfile anILimitProfile,
			ICMSCustomer anICMSCustomer) throws GenerateRequestException, RemoteException;

	/**
	 * Get a new deferral request for a non borrower
	 * @param anICMSCustomer of ICMSCustomer type
	 * @return IDeferralRequestTrxValue - the deferral request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue getNewDeferralRequestTrxValue(ICMSCustomer anICMSCustomer)
			throws GenerateRequestException, RemoteException;

	/**
	 * Get the waiver trx value using the trx ID
	 * @param anILimitProfile of ILimitProfile type
	 * @param anICMSCustomer of ICMSCustomer type
	 * @param aTrxID of String type
	 * @return IWaiverRequestTrxValue - the trx value of the waiver request
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue getWaiverRequestTrxValue(ILimitProfile anILimitProfile, ICMSCustomer anICMSCustomer,
			String aTrxID) throws GenerateRequestException, RemoteException;

	/**
	 * Get the waiver trx value using the trx ID for non borrower
	 * @param anICMSCustomer of ICMSCustomer type
	 * @param aTrxID of String type
	 * @return IWaiverRequestTrxValue - the trx value of the waiver request
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue getWaiverRequestTrxValue(ICMSCustomer anICMSCustomer, String aTrxID)
			throws GenerateRequestException, RemoteException;

	/**
	 * Get the deferral trx value using the trx ID
	 * @param anILimitProfile of ILimitProfile type
	 * @param anICMSCustomer of ICMSCustomer type
	 * @param aTrxID of String type
	 * @return IDeferralRequestTrxValue - the trx value of the deferral request
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue getDeferralRequestTrxValue(ILimitProfile anILimitProfile,
			ICMSCustomer anICMSCustomer, String aTrxID) throws GenerateRequestException, RemoteException;

	/**
	 * Get the deferral trx value using the trx ID for a non borrower
	 * @param anICMSCustomer of ICMSCustomer type
	 * @param aTrxID of String type
	 * @return IDeferralRequestTrxValue - the trx value of the deferral request
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue getDeferralRequestTrxValue(ICMSCustomer anICMSCustomer, String aTrxID)
			throws GenerateRequestException, RemoteException;

	/**
	 * Maker generate the waiver request
	 * @param anITrxContext of ITrxContext type
	 * @param anIWaiverRequestTrxValue of IWaiverRequestTrxValue type
	 * @param anIWaiverRequets of IWaiverRequest type
	 * @return IWaiverRequestTrxValue - the generates waiver request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue makerGenerateRequest(ITrxContext anITrxContext,
			IWaiverRequestTrxValue anIWaiverRequestTrxValue, IWaiverRequest anIWaiverRequest)
			throws GenerateRequestException, RemoteException;

	/**
	 * Maker generate the deferral request
	 * @param anITrxContext of ITrxContext type
	 * @param anIDeferralRequestTrxValue of IDeferralRequestTrxValue type
	 * @param anIDeferralRequets of IDeferralRequest type
	 * @return IDeferralRequestTrxValue - the generates deferral request trx
	 *         value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue makerGenerateRequest(ITrxContext anITrxContext,
			IDeferralRequestTrxValue anIDeferralRequestTrxValue, IDeferralRequest anIDeferralRequest)
			throws GenerateRequestException, RemoteException;

	/**
	 * Checker approve the waiver request
	 * @param anITrxContext of ITrxContext type
	 * @param IWaiverRequestTrxValue of IWaiverRequestTrxValue type
	 * @return IWaiverRequestTrxValue - the generated waiver request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue checkerApproveGenerateRequest(ITrxContext anITrxContext,
			IWaiverRequestTrxValue anIWaiverRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * Checker approve the deferral request
	 * @param anITrxContext of ITrxContext type
	 * @param IDeferralRequestTrxValue of IDeferralRequestTrxValue type
	 * @return IDeferralRequestTrxValue - the generated deferral request trx
	 *         value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue checkerApproveGenerateRequest(ITrxContext anITrxContext,
			IDeferralRequestTrxValue anIDeferralRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * RM approve the waiver request
	 * @param anITrxContext of ITrxContext type
	 * @param IWaiverRequestTrxValue of IWaiverRequestTrxValue type
	 * @return IWaiverRequestTrxValue - the generated waiver request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue rmApproveGenerateRequest(ITrxContext anITrxContext,
			IWaiverRequestTrxValue anIWaiverRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * RM approve the deferral request
	 * @param anITrxContext of ITrxContext type
	 * @param IDeferralRequestTrxValue of IDeferralRequestTrxValue type
	 * @return IDeferralRequestTrxValue - the generated deferral request trx
	 *         value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue rmApproveGenerateRequest(ITrxContext anITrxContext,
			IDeferralRequestTrxValue anIDeferralRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * Checker reject the waiver request
	 * @param anITrxContext of ITrxContext type
	 * @param anIWaiverRequestTrxValue of IWaiverRequestTrxValue type
	 * @return IWaiverRequestTrxValue - the waiver request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue checkerRejectGenerateRequest(ITrxContext anITrxContext,
			IWaiverRequestTrxValue anIWaiverRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * Checker reject the deferral request
	 * @param anITrxContext of ITrxContext type
	 * @param anIDeferralRequestTrxValue of IDeferralRequestTrxValue type
	 * @return IDeferralRequestTrxValue - the deferral request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue checkerRejectGenerateRequest(ITrxContext anITrxContext,
			IDeferralRequestTrxValue anIDeferralRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * RM reject the waiver request
	 * @param anITrxContext of ITrxContext type
	 * @param anIWaiverRequestTrxValue of IWaiverRequestTrxValue type
	 * @return IWaiverRequestTrxValue - the waiver request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue rmRejectGenerateRequest(ITrxContext anITrxContext,
			IWaiverRequestTrxValue anIWaiverRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * RM reject the deferral request
	 * @param anITrxContext of ITrxContext type
	 * @param anIDeferralRequestTrxValue of IDeferralRequestTrxValue type
	 * @return IDeferralRequestTrxValue - the deferral request trx value
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue rmRejectGenerateRequest(ITrxContext anITrxContext,
			IDeferralRequestTrxValue anIDeferralRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * Maker edit the rejected waiver request
	 * @param anITrxContext of ITrxContext type
	 * @param anIWaiverRequestTrxValue of IWaiverRequestTrxValue
	 * @param anIWaiverRequest of IWaiverRequest
	 * @return IWaiverRequestTrxValue - the waiver request trx
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue makerEditRejectedGenerateRequest(ITrxContext anITrxContext,
			IWaiverRequestTrxValue anIWaiverRequestTrxValue, IWaiverRequest anIWaiverRequest)
			throws GenerateRequestException, RemoteException;

	/**
	 * Maker edit the rejected deferral request
	 * @param anITrxContext of ITrxContext type
	 * @param anIDeferralRequestTrxValue of IDeferralRequestTrxValue
	 * @param anIDeferralRequest of IDeferralRequest
	 * @return IDeferralRequestTrxValue - the deferral request trx
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue makerEditRejectedGenerateRequest(ITrxContext anITrxContext,
			IDeferralRequestTrxValue anIDeferralRequestTrxValue, IDeferralRequest anIDeferralRequest)
			throws GenerateRequestException, RemoteException;

	/**
	 * Make close the rejected waiver request
	 * @param anITrxContext of ITrxContext type
	 * @param anIWaiverRequestTrxValue of IWaiverRequestTrxValue type
	 * @return IWaiverRequestTrxValue - the waiver request trx
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IWaiverRequestTrxValue makerCloseRejectedGenerateRequest(ITrxContext anITrxContext,
			IWaiverRequestTrxValue anIWaiverRequestTrxValue) throws GenerateRequestException, RemoteException;

	/**
	 * Make close the rejected deferral request
	 * @param anITrxContext of ITrxContext type
	 * @param anIDeferralRequestTrxValue of IDeferralRequestTrxValue type
	 * @return IDeferralRequestTrxValue - the deferral request trx
	 * @throws GenerateRequestException on errors
	 * @throws RemoteException
	 */
	public IDeferralRequestTrxValue makerCloseRejectedGenerateRequest(ITrxContext anITrxContext,
			IDeferralRequestTrxValue anIDeferralRequestTrxValue) throws GenerateRequestException, RemoteException;
}
