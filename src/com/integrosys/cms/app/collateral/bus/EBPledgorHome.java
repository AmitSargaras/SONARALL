/*
 * Copyright Integro Technologies Pte Ltd
 * $Header: /home/cms2/cvsroot/cms2/src/com/integrosys/cms/app/collateral/bus/EBPledgorHome.java,v 1.2 2003/10/10 09:37:47 lyng Exp $
 */
package com.integrosys.cms.app.collateral.bus;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

/**
 * Entity bean remote home interface for EBPledgorBean.
 * 
 * @author $Author: lyng $<br>
 * @version $Revision: 1.2 $
 * @since $Date: 2003/10/10 09:37:47 $ Tag: $Name: $
 */
public interface EBPledgorHome extends EJBHome {
	/**
	 * Called by the client to create a pledgor.
	 * 
	 * @param pledgor of type IPledgor
	 * @return pledgor ejb object
	 * @throws CreateException on error while creating the pledgor
	 */
	public EBPledgor create(IPledgor pledgor) throws CreateException, RemoteException;

	/**
	 * Find the pledgor by its primary key.
	 * 
	 * @param pk the pledgor primary key
	 * @return pledgor ejb object
	 * @throws FinderException on error while finding the pledgor
	 * @throws RemoteException on any error during remote method call
	 */
	public EBPledgor findByPrimaryKey(Long pk) throws FinderException, RemoteException;

	/**
	 * Find the pledgor by SCI pledgor id.
	 * 
	 * @param sciPledgorID pledgor id generated by SCI
	 * @return a pledgor
	 * @throws FinderException on error while finding the pledgor
	 * @throws RemoteException on any error during remote method call
	 */
	public EBPledgor findBySCIPledgorID(long sciPledgorID) throws FinderException, RemoteException;

	/**
	 * Find the pledgor by legal id.
	 * 
	 * @param legalID legal id
	 * @return a pledgor
	 * @throws FinderException on error while finding the pledgor
	 * @throws RemoteException on any error during remote method call
	 */
	public EBPledgor findByLegalID(String legalID) throws FinderException, RemoteException;

}