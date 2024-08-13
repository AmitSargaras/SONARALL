package com.integrosys.cms.app.leiDetailsUpload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.context.BeanHouse;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.cms.app.fileUpload.bus.FileUploadException;
import com.integrosys.cms.app.fileUpload.bus.IFileUpload;
import com.integrosys.cms.app.fileUpload.bus.IFileUploadJdbc;
import com.integrosys.cms.app.fileUpload.bus.OBLeiDetailsFile;
import com.integrosys.cms.app.fileUpload.trx.IFileUploadTrxValue;
import com.integrosys.cms.app.fileUpload.trx.OBFileUploadTrxValue;
import com.integrosys.cms.app.leiDetailsUpload.proxy.ILeiDetailsUploadProxyManager;

public class MakerReadLeiDetailsRejectCmd extends AbstractCommand implements ICommonEventConstant{
	public MakerReadLeiDetailsRejectCmd(){
		
	}
	
	private ILeiDetailsUploadProxyManager leidetailsuploadProxy;
	
	public ILeiDetailsUploadProxyManager getLeiDetailsuploadProxy() {
		return leidetailsuploadProxy;
	}

	public void setLeiDetailsuploadProxy(ILeiDetailsUploadProxyManager leidetailsuploadProxy) {
		this.leidetailsuploadProxy = leidetailsuploadProxy;
	} 

	public String[][] getParameterDescriptor() {
		return (new String[][] { 
				{ "TrxId", "java.lang.String", REQUEST_SCOPE },
				{"event", "java.lang.String", REQUEST_SCOPE},
				
		});
	}

	/**
	 * Defines an two dimensional array with the result list to be expected as a
	 * result from the doExecute method using a HashMap syntax for the array is
	 * (HashMapkey,classname,scope) The scope may be request,form or service
	 * 
	 * @return the two dimensional String array
	 */
	public String[][] getResultDescriptor() {
		return (new String[][] { 
			
				{"IFileUploadTrxValue", "com.integrosys.cms.app.fileUpload.trx.IFileUploadTrxValue", SERVICE_SCOPE},
				{"event", "java.lang.String", REQUEST_SCOPE},
				{"leiDetailsList", "java.util.List", SERVICE_SCOPE},
				{"totalUploadedList", "java.util.List", SERVICE_SCOPE},
				{ "total", "java.lang.String", REQUEST_SCOPE },
				{ "correct", "java.lang.String", REQUEST_SCOPE },
				{ "fail", "java.lang.String", REQUEST_SCOPE },
				
		});
	}
	
	public HashMap doExecute(HashMap map) throws CommandProcessingException, CommandValidationException,FileUploadException {
		HashMap returnMap = new HashMap();
		HashMap resultMap = new HashMap();
		try {
			IFileUpload fileUpload;
			SearchResult leidetailsFileList = new SearchResult();
			IFileUploadTrxValue trxValue=null;
			String fileId=(String) (map.get("TrxId"));
			String event = (String) map.get("event");
			
			ArrayList errorList=new ArrayList();
			// function to get Component Trx value
			trxValue = (OBFileUploadTrxValue) getLeiDetailsuploadProxy().getFielUploadByTrxID(fileId);
			IFileUploadJdbc jdbc=(IFileUploadJdbc)BeanHouse.get("fileUploadJdbc");
			leidetailsFileList=jdbc.getAllLeiDetailsFile(trxValue.getStagingReferenceID());
			
			List uploadList=new ArrayList();
			if(leidetailsFileList!=null){
				uploadList = new ArrayList(leidetailsFileList.getResultList());		
			}
			long countPass = 0;
			long countFail = 0;
			
					for (int i = 0; i < uploadList.size(); i++) {
						OBLeiDetailsFile oBLeiDetailsFile = (OBLeiDetailsFile) uploadList.get(i);
	
						if ("FAIL".equals(oBLeiDetailsFile.getStatus()) && "N".equals(oBLeiDetailsFile.getUploadStatus())) {
							countFail++;
						} else if ("PASS".equals(oBLeiDetailsFile.getStatus()) && "Y".equals(oBLeiDetailsFile.getUploadStatus())) {
							countPass++;
						}
					}
			
			//Added For FD Upload End
			resultMap.put("total", String.valueOf(uploadList.size()));
			resultMap.put("correct", String.valueOf(countPass));
			resultMap.put("fail", String.valueOf(countFail));

			resultMap.put("IFileUploadTrxValue", trxValue);
			resultMap.put("totalUploadedList", uploadList);
			resultMap.put("leiDetailsList", uploadList);
			resultMap.put("event", event);
		} catch(FileUploadException ex){
			DefaultLogger.debug(this, "got exception in doExecute" + ex);
			ex.printStackTrace();
		} catch (TrxParameterException e) {
			throw (new CommandProcessingException(e.getMessage()));
		} catch (Exception e) { 
			DefaultLogger.debug(this, "got exception in doExecute" + e);
			e.printStackTrace();
			throw (new CommandProcessingException(e.getMessage()));
		}

		returnMap.put(ICommonEventConstant.COMMAND_RESULT_MAP, resultMap);
		return returnMap;
	}



}
