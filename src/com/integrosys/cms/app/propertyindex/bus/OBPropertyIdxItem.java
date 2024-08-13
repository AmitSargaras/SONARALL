/*
 * Generated by XDoclet - Do not edit!
 */
package com.integrosys.cms.app.propertyindex.bus;

import java.util.Collection;
import java.util.Set;

/**
 * Title: CLIMS 
 * Description: Data object for EBPropertyIdxItem.
 * Copyright: Integro Technologies Sdn Bhd 
 * Author: Andy Wong 
 * Date: Jan 15, 2008
 */

public class OBPropertyIdxItem implements IPropertyIdxItem
{
   private long propertyIdxItemId;
   private long propertyIdxId;
   private java.math.BigDecimal idxYear;
   private java.lang.String idxType;
   private java.math.BigDecimal idxValue;
   private java.lang.String stateCode;
   private java.lang.String status;
   private long cmsRefId = com.integrosys.cms.app.common.constant.ICMSConstant.LONG_INVALID_VALUE;
   private Set propertyTypeList;
   private Set mukimList;
   private Set districtList;

  /* begin value object */

  /* end value object */

   public OBPropertyIdxItem()
   {
   }

   public OBPropertyIdxItem( long propertyIdxItemId,java.math.BigDecimal idxYear,java.lang.String idxType,java.math.BigDecimal idxValue,java.lang.String status,long cmsRefId )
   {
      setPropertyIdxItemId(propertyIdxItemId);
//      setPropertyIdxId(propertyIdxId);
      setIdxYear(idxYear);
      setIdxType(idxType);
      setIdxValue(idxValue);
      setStatus(status);
      setCmsRefId(cmsRefId);
   }

   public OBPropertyIdxItem( OBPropertyIdxItem otherData )
   {
      setPropertyIdxItemId(otherData.getPropertyIdxItemId());
//      setPropertyIdxId(otherData.getPropertyIdxId());
      setIdxYear(otherData.getIdxYear());
      setIdxType(otherData.getIdxType());
      setIdxValue(otherData.getIdxValue());
      setStatus(otherData.getStatus());
      setCmsRefId(otherData.getCmsRefId());

   }

   public long getPrimaryKey() {
     return  getPropertyIdxItemId();
   }

   public long getPropertyIdxItemId()
   {
      return this.propertyIdxItemId;
   }
   public void setPropertyIdxItemId( long propertyIdxItemId )
   {
      this.propertyIdxItemId = propertyIdxItemId;
   }

   public long getPropertyIdxId()
   {
      return this.propertyIdxId;
   }
   public void setPropertyIdxId( long propertyIdxId )
   {
      this.propertyIdxId = propertyIdxId;
   }

   public java.math.BigDecimal getIdxYear()
   {
      return this.idxYear;
   }
   public void setIdxYear( java.math.BigDecimal idxYear )
   {
      this.idxYear = idxYear;
   }

   public java.lang.String getIdxType()
   {
      return this.idxType;
   }
   public void setIdxType( java.lang.String idxType )
   {
      this.idxType = idxType;
   }

   public java.math.BigDecimal getIdxValue()
   {
      return this.idxValue;
   }
   public void setIdxValue( java.math.BigDecimal idxValue )
   {
      this.idxValue = idxValue;
   }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public java.lang.String getStatus()
   {
      return this.status;
   }
   public void setStatus( java.lang.String status )
   {
      this.status = status;
   }

   public long getCmsRefId()
   {
      return this.cmsRefId;
   }
   public void setCmsRefId( long cmsRefId )
   {
      this.cmsRefId = cmsRefId;
   }

   public String toString()
   {
      StringBuffer str = new StringBuffer("{");

      str.append("propertyIdxItemId=" + getPropertyIdxItemId() + " " + "idxYear=" + getIdxYear() + " " + "idxType=" + getIdxType() + " " + "idxValue=" + getIdxValue() + " " + "status=" + getStatus() + " " + "cmsRefId=" + getCmsRefId());
      str.append('}');

      return(str.toString());
   }

    public boolean equals( Object pOther )
    {
       if( pOther instanceof OBPropertyIdxItem )
       {
          OBPropertyIdxItem lTest = (OBPropertyIdxItem) pOther;
          boolean lEquals = true;

          if( this.stateCode == null )
          {
             lEquals = lEquals && ( lTest.stateCode == null );
          }
          else
          {
             lEquals = lEquals && this.stateCode == lTest.stateCode ;
          }
          if( this.idxYear == null )
          {
             lEquals = lEquals && ( lTest.idxYear == null );
          }
          else
          {
             lEquals = lEquals && this.idxYear.equals( lTest.idxYear );
          }
          if( this.idxType == null )
          {
             lEquals = lEquals && ( lTest.idxType == null );
          }
          else
          {
             lEquals = lEquals && this.idxType.equals( lTest.idxType );
          }
          if( this.idxValue == null )
          {
             lEquals = lEquals && ( lTest.idxValue == null );
          }
          else
          {
             lEquals = lEquals && this.idxValue.equals( lTest.idxValue );
          }
          if( this.status == null )
          {
             lEquals = lEquals && ( lTest.status == null );
          }
          else
          {
             lEquals = lEquals && this.status.equals( lTest.status );
          }
          if( this.cmsRefId == com.integrosys.cms.app.common.constant.ICMSConstant.LONG_INVALID_VALUE )
          {
             lEquals = lEquals && ( lTest.cmsRefId == com.integrosys.cms.app.common.constant.ICMSConstant.LONG_INVALID_VALUE );
          }
          else
          {
             lEquals = lEquals && this.cmsRefId == lTest.cmsRefId;
          }

          return lEquals;
       }
       else
       {
          return false;
       }
    }

    public Set getMukimList() {
        return mukimList;
    }

    public void setMukimList(Set mukimList) {
        this.mukimList = mukimList;
    }

    public Set getDistrictList() {
        return districtList;
    }

    public void setDistrictList(Set districtList) {
        this.districtList = districtList;
    }

    public Set getPropertyTypeList() {
        return propertyTypeList;
    }

    public void setPropertyTypeList(Set propertyTypeList) {
        this.propertyTypeList = propertyTypeList;
    }

}
