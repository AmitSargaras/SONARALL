package com.integrosys.cms.app.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.integrosys.cms.app.ws.dto.DigitalLibraryRequestDTO;
import com.integrosys.cms.app.ws.dto.DigitalLibraryResponseDTO;
import com.integrosys.cms.app.ws.jax.common.CMSFault;
import com.integrosys.cms.app.ws.jax.common.CMSValidationFault;

@WebService(serviceName = "DigitalLibraryServiceV2EndPoint", portName = "DigitalLibraryServiceV2EndPointPort", targetNamespace="http://www.aurionprosolutions.com/DIGITALLIBRARYV2/")
public interface IDigitalLibraryServiceV2EndPoint {

	@WebMethod(operationName="getDigitalLibraryDetails")
	DigitalLibraryResponseDTO  getDigitalLibraryDetailsForV2(@WebParam (name = "DigitalLibraryRequest") DigitalLibraryRequestDTO  digitalLibraryRequestDTO) throws CMSValidationFault, CMSFault;


	
}
