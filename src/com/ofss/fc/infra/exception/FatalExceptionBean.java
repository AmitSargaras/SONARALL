/**
 * FatalExceptionBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ofss.fc.infra.exception;

public class FatalExceptionBean  extends org.apache.axis.AxisFault  implements java.io.Serializable {
	
    private java.lang.String errorCode;

    private java.lang.String message1;

    private com.ofss.fc.infra.enumeration.ResponseCodeType responseCode;

    private com.ofss.fc.infra.validation.error.ValidationError[] validationErrors;

    public FatalExceptionBean() {
    }

    public FatalExceptionBean(
           java.lang.String errorCode,
           java.lang.String message1,
           com.ofss.fc.infra.enumeration.ResponseCodeType responseCode,
           com.ofss.fc.infra.validation.error.ValidationError[] validationErrors) {
        this.errorCode = errorCode;
        this.message1 = message1;
        this.responseCode = responseCode;
        this.validationErrors = validationErrors;
    }


    /**
     * Gets the errorCode value for this FatalExceptionBean.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this FatalExceptionBean.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the message1 value for this FatalExceptionBean.
     * 
     * @return message1
     */
    public java.lang.String getMessage1() {
        return message1;
    }


    /**
     * Sets the message1 value for this FatalExceptionBean.
     * 
     * @param message1
     */
    public void setMessage1(java.lang.String message1) {
        this.message1 = message1;
    }


    /**
     * Gets the responseCode value for this FatalExceptionBean.
     * 
     * @return responseCode
     */
    public com.ofss.fc.infra.enumeration.ResponseCodeType getResponseCode() {
        return responseCode;
    }


    /**
     * Sets the responseCode value for this FatalExceptionBean.
     * 
     * @param responseCode
     */
    public void setResponseCode(com.ofss.fc.infra.enumeration.ResponseCodeType responseCode) {
        this.responseCode = responseCode;
    }


    /**
     * Gets the validationErrors value for this FatalExceptionBean.
     * 
     * @return validationErrors
     */
    public com.ofss.fc.infra.validation.error.ValidationError[] getValidationErrors() {
        return validationErrors;
    }


    /**
     * Sets the validationErrors value for this FatalExceptionBean.
     * 
     * @param validationErrors
     */
    public void setValidationErrors(com.ofss.fc.infra.validation.error.ValidationError[] validationErrors) {
        this.validationErrors = validationErrors;
    }

    public com.ofss.fc.infra.validation.error.ValidationError getValidationErrors(int i) {
        return this.validationErrors[i];
    }

    public void setValidationErrors(int i, com.ofss.fc.infra.validation.error.ValidationError _value) {
        this.validationErrors[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FatalExceptionBean)) return false;
        FatalExceptionBean other = (FatalExceptionBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.message1==null && other.getMessage1()==null) || 
             (this.message1!=null &&
              this.message1.equals(other.getMessage1()))) &&
            ((this.responseCode==null && other.getResponseCode()==null) || 
             (this.responseCode!=null &&
              this.responseCode.equals(other.getResponseCode()))) &&
            ((this.validationErrors==null && other.getValidationErrors()==null) || 
             (this.validationErrors!=null &&
              java.util.Arrays.equals(this.validationErrors, other.getValidationErrors())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getMessage1() != null) {
            _hashCode += getMessage1().hashCode();
        }
        if (getResponseCode() != null) {
            _hashCode += getResponseCode().hashCode();
        }
        if (getValidationErrors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValidationErrors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValidationErrors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FatalExceptionBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exception.infra.fc.ofss.com", "fatalExceptionBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exception.infra.fc.ofss.com", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exception.infra.fc.ofss.com", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exception.infra.fc.ofss.com", "responseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enumeration.infra.fc.ofss.com", "responseCodeType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validationErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exception.infra.fc.ofss.com", "validationErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://error.validation.infra.fc.ofss.com", "validationError"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
