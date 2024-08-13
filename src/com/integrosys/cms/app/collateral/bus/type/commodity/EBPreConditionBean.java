/**
 * Copyright Integro Technologies Pte Ltd
 * $Header: /home/cms2/cvsroot/cms2/src/com/integrosys/cms/app/collateral/bus/type/commodity/EBPreConditionBean.java,v 1.1 2005/07/15 06:25:01 lyng Exp $
 */
package com.integrosys.cms.app.collateral.bus.type.commodity;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

import com.integrosys.base.techinfra.dbsupport.SequenceManager;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.techinfra.util.AccessorUtil;
import com.integrosys.cms.app.common.constant.ICMSConstant;

/**
 * Entity bean implementation for commodity pre-condition.
 * 
 * @author $Author: lyng $
 * @version $Revision: 1.1 $
 * @since $Date: 2005/07/15 06:25:01 $ Tag: $Name: $
 */
public abstract class EBPreConditionBean implements EntityBean, IPreCondition {
	/** The container assigned reference to the entity. */
	private EntityContext context;

	/** A list of methods to be excluded during create/update pre-condition. */
	private static final String[] EXCLUDE_METHOD = new String[] { "getPreConditionID" };

	public EBPreConditionBean() {
	}

	/**
	 * Get pre-condition id.
	 * 
	 * @return long
	 */
	public long getPreConditionID() {
		return getEBPreConditionID().longValue();
	}

	/**
	 * Set pre-condition id.
	 * 
	 * @param preConditionID of type long
	 */
	public void setPreConditionID(long preConditionID) {
		setEBPreConditionID(new Long(preConditionID));
	}

	public abstract Long getEBPreConditionID();

	public abstract void setEBPreConditionID(Long preConditionID);

	/**
	 * Get commodity pre-condition business object.
	 * 
	 * @return IPreCondition
	 */
	public IPreCondition getValue() {
		OBPreCondition preCondition = new OBPreCondition();
		AccessorUtil.copyValue(this, preCondition);
		return preCondition;
	}

	/**
	 * Persist newly updated pre-condition.
	 * 
	 * @param preCondition of type IPreCondition
	 */
	public void setValue(IPreCondition preCondition) {
		AccessorUtil.copyValue(preCondition, this, EXCLUDE_METHOD);
	}

	/**
	 * Create a pre-condition record.
	 * 
	 * @param preCondition precondition to be created.
	 * @return Long
	 * @throws CreateException on error creating the record.
	 */
	public Long ejbCreate(IPreCondition preCondition) throws CreateException {
		try {
			String newPreConditionID = (new SequenceManager()).getSeqNum(ICMSConstant.SEQUENCE_PRECOND, true);
			AccessorUtil.copyValue(preCondition, this, EXCLUDE_METHOD);
			setEBPreConditionID(new Long(newPreConditionID));
			return null;
		}
		catch (Exception e) {
			DefaultLogger.error(this, "", e);
			throw new CreateException(e.toString());
		}
	}

	/**
	 * The container invokes this method after invoking the ejbCreate method
	 * with the same arguments.
	 * 
	 * @param preCondition of type IPreCondition
	 * @throws CreateException on error creating the references
	 */
	public void ejbPostCreate(IPreCondition preCondition) throws CreateException {
	}

	/**
	 * EJB callback method to set the context of the bean.
	 * 
	 * @param context the entity context.
	 */
	public void setEntityContext(EntityContext context) {
		this.context = context;
	}

	/**
	 * EJB callback method to clears the context of the bean.
	 */
	public void unsetEntityContext() {
		this.context = null;
	}

	/**
	 * This method is called when the container picks this entity object and
	 * assigns it to a specific entity object. No implementation currently
	 * acquires any additional resources that it needs when it is in the ready
	 * state.
	 */
	public void ejbActivate() {
	}

	/**
	 * This method is called when the container diassociates the bean from the
	 * entity object identity and puts the instance back into the pool of
	 * available instances. No implementation is currently provided to release
	 * resources that should not be held while the instance is in the pool.
	 */
	public void ejbPassivate() {
	}

	/**
	 * The container invokes this method on the bean whenever it becomes
	 * necessary to synchronize the bean's state with the state in the database.
	 * This method is called after the container has loaded the bean's state
	 * from the database.
	 */
	public void ejbLoad() {
	}

	/**
	 * The container invokes this method on the bean whenever it becomes
	 * necessary to synchronize the state in the database with the state of the
	 * bean. This method is called before the container extracts the fields and
	 * writes them into the database.
	 */
	public void ejbStore() {
	}

	/**
	 * The container invokes this method in response to a client-invoked remove
	 * request. No implementation is currently provided for taking actions
	 * before the bean is removed from the database.
	 */
	public void ejbRemove() {
	}
}