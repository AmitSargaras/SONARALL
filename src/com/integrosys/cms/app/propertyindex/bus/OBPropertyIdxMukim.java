/*
 * Generated by XDoclet - Do not edit!
 */
package com.integrosys.cms.app.propertyindex.bus;

/**
 * Title: CLIMS 
 * Description: Data object for EBPropertyIdxMukim.
 * Copyright: Integro Technologies Sdn Bhd 
 * Author: Andy Wong 
 * Date: Jan 15, 2008
 */

public class OBPropertyIdxMukim implements IPropertyIdxMukim
{
   private long propertyIdxMukimCodeId;
   private long propertyItemIdxId;
   private java.lang.String mukim_code;
   private java.lang.String status;

   public OBPropertyIdxMukim()
   {
   }

   public OBPropertyIdxMukim( long propertyIdxMukimCodeId,java.lang.String mukim_code,java.lang.String status )
   {
      setPropertyIdxMukimCodeId(propertyIdxMukimCodeId);
      setPropertyItemIdxId(propertyItemIdxId);
      setMukimCode(mukim_code);
      setStatus(status);
   }

   public long getPrimaryKey() {
     return  getPropertyIdxMukimCodeId();
   }

   public long getPropertyIdxMukimCodeId()
   {
      return this.propertyIdxMukimCodeId;
   }
   public void setPropertyIdxMukimCodeId( long propertyIdxMukimCodeId )
   {
      this.propertyIdxMukimCodeId = propertyIdxMukimCodeId;
   }

   public long getPropertyItemIdxId()
   {
      return this.propertyItemIdxId;
   }
   public void setPropertyItemIdxId( long propertyItemIdxId )
   {
      this.propertyItemIdxId = propertyItemIdxId;
   }

   public java.lang.String getMukimCode()
   {
      return this.mukim_code;
   }
   public void setMukimCode( java.lang.String mukim_code )
   {
      this.mukim_code = mukim_code;
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

      str.append("propertyIdxMukimCodeId=" + getPropertyIdxMukimCodeId() + " "  + "mukim_code=" + getMukimCode() + " " + "status=" + getStatus());
      str.append('}');

      return(str.toString());
   }  
   
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;
        else if (!(obj instanceof OBPropertyIdxMukim))
            return false;
        else {
            if (((OBPropertyIdxMukim)obj).getMukimCode().equals( this.getMukimCode() ) )
                return true;
            else
                return false;
        }
    }

}
