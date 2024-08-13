/*
 * Generated by XDoclet - Do not edit!
 */
package com.integrosys.cms.app.propertyindex.bus;

/**
 * Title: CLIMS 
 * Description: Data object for EBPropertyIdxSecSubType
 * Copyright: Integro Technologies Sdn Bhd 
 * Author: Andy Wong 
 * Date: Jan 15, 2008
 */

public class OBPropertyIdxSecSubType implements IPropertyIdxSecSubType
{
   private long propertyIdxSecSubTypeId;
   private long propertyIdxId;
   private java.lang.String securitySubTypeId;
   private java.lang.String status;

   public OBPropertyIdxSecSubType()
   {
   }

   public long getPrimaryKey() {
     return  getPropertyIdxSecSubTypeId();
   }

   public long getPropertyIdxSecSubTypeId()
   {
      return this.propertyIdxSecSubTypeId;
   }
   public void setPropertyIdxSecSubTypeId( long propertyIdxSecSubTypeId )
   {
      this.propertyIdxSecSubTypeId = propertyIdxSecSubTypeId;
   }

   public long getPropertyIdxId()
   {
      return this.propertyIdxId;
   }
   public void setPropertyIdxId( long propertyIdxId )
   {
      this.propertyIdxId = propertyIdxId;
   }

   public java.lang.String getSecuritySubTypeId()
   {
      return this.securitySubTypeId;
   }
   public void setSecuritySubTypeId( java.lang.String securitySubTypeId )
   {
      this.securitySubTypeId = securitySubTypeId;
   }

   public java.lang.String getStatus()
   {
      return this.status;
   }
   public void setStatus( java.lang.String status )
   {
      this.status = status;
   }

   public String toString()
   {
      StringBuffer str = new StringBuffer("{");

      str.append("propertyIdxSecSubTypeId=" + getPropertyIdxSecSubTypeId() + " " + "securitySubTypeId=" + getSecuritySubTypeId() + " " + "status=" + getStatus());
      str.append('}');

      return(str.toString());
   }

   public boolean equals( Object pOther )
   {
      if( pOther instanceof OBPropertyIdxSecSubType )
      {
    	 OBPropertyIdxSecSubType lTest = (OBPropertyIdxSecSubType) pOther;
         boolean lEquals = true;

         if( this.securitySubTypeId == null )
         {
            lEquals = lEquals && ( lTest.securitySubTypeId == null );
         }
         else
         {
            lEquals = lEquals && this.securitySubTypeId.equals( lTest.securitySubTypeId );
         }
         if( this.status == null )
         {
            lEquals = lEquals && ( lTest.status == null );
         }
         else
         {
            lEquals = lEquals && this.status.equals( lTest.status );
         }

         return lEquals;
      }
      else
      {
         return false;
      }
   }

}
