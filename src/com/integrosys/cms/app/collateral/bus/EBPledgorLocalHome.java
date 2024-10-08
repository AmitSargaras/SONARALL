/*
 * Copyright Integro Technologies Pte Ltd
 * $Header: /home/cms2/cvsroot/cms2/src/com/integrosys/cms/app/collateral/bus/EBPledgorLocalHome.java,v 1.2 2003/10/10 09:37:55 lyng Exp $
 */
package com.integrosys.cms.app.collateral.bus;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

/**
 * Entity bean local home interface for EBPledgorBean.
 * 
 * @author $Author: lyng $<br>
 * @version $Revision: 1.2 $
 * @since $Date: 2003/10/10 09:37:55 $ Tag: $Name: $
 */
public interface EBPledgorLocalHome extends EJBLocalHome {
	/**
	 * Called by the client to create a pledgor.
	 * 
	 * @param pledgor of type IPledgor
	 * @return pledgor local ejb object
	 * @throws CreateException on error while creating the pledgor
	 */
	public EBPledgorLocal create(IPledgor pledgor) throws CreateException;

	/**
	 * Find the pledgor by its primary key.
	 * 
	 * @param pk the pledgor primary key
	 * @return pledgor local ejb object
	 * @throws FinderException on error while finding the pledgor
	 */
	public EBPledgorLocal findByPrimaryKey(Long pk) throws FinderException;

	/**
	 * Find the pledgor by SCI pledgor id.
	 * 
	 * @param sciPledgorID pledgor id generated by SCI
	 * @return a pledgor
	 * @throws FinderException on error while finding the pledgor
	 */
	public EBPledgorLocal findBySCIPledgorID(long sciPledgorID) throws FinderException;

	/**
	 * Find the pledgor by legal id.
	 *
	 * @param legalID legal id
	 * @return a pledgor
	 * @throws FinderException on error while finding the pledgor
	 */
	public EBPledgorLocal findByLegalID(String legalID) throws FinderException;    
}