package com.integrosys.cms.ui.fileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.context.BeanHouse;
import com.integrosys.base.techinfra.logger.DefaultLogger;
import com.integrosys.base.techinfra.util.DateUtil;
import com.integrosys.base.uiinfra.common.AbstractCommand;
import com.integrosys.base.uiinfra.common.ICommonEventConstant;
import com.integrosys.base.uiinfra.exception.CommandProcessingException;
import com.integrosys.base.uiinfra.exception.CommandValidationException;
import com.integrosys.cms.app.common.constant.ICMSConstant;
import com.integrosys.cms.app.fileUpload.bus.FileUploadException;
import com.integrosys.cms.app.fileUpload.bus.IFileUpload;
import com.integrosys.cms.app.fileUpload.bus.IFileUploadDao;
import com.integrosys.cms.app.fileUpload.bus.IFileUploadJdbc;
import com.integrosys.cms.app.fileUpload.bus.OBBahrainFile;
import com.integrosys.cms.app.fileUpload.bus.OBFileUpload;
import com.integrosys.cms.app.fileUpload.proxy.IFileUploadProxyManager;
import com.integrosys.cms.app.fileUpload.trx.IFileUploadTrxValue;
import com.integrosys.cms.app.fileUpload.trx.OBFileUploadTrxValue;
import com.integrosys.cms.app.generalparam.bus.IGeneralParamDao;
import com.integrosys.cms.app.generalparam.bus.IGeneralParamEntry;
import com.integrosys.cms.app.generalparam.bus.IGeneralParamGroup;
import com.integrosys.cms.app.transaction.OBTrxContext;
import com.integrosys.cms.batch.common.filereader.ProcessDataFile;
import com.integrosys.cms.batch.ubs.IUbsErrorLog;
import com.integrosys.cms.batch.ubs.OBUbsErrorLog;
import com.integrosys.cms.ui.common.constant.IGlobalConstant;
import com.integrosys.cms.ui.login.CMSGlobalSessionConstant;
import com.integrosys.component.user.app.bus.ICommonUser;

public class UploadBahrainFileCmd extends AbstractCommand implements ICommonEventConstant{
	
	private IFileUploadProxyManager fileUploadProxy;
	//public static final String BAHRAIN_UPLOAD = "BahrainUpload";

	public IFileUploadProxyManager getFileUploadProxy() {
		return fileUploadProxy;
	}
	public void setFileUploadProxy(IFileUploadProxyManager fileUploadProxy) {
		this.fileUploadProxy = fileUploadProxy;
	}
	public UploadBahrainFileCmd(){		
	}

	public String[][] getParameterDescriptor() {
		return new String[][] {
				{ "startIndex", "java.lang.String", REQUEST_SCOPE },
				{ "totalCount", "java.lang.String", REQUEST_SCOPE },
				{ "event", "java.lang.String", REQUEST_SCOPE },
				{ "path", "java.lang.String", REQUEST_SCOPE },
				{ IGlobalConstant.USER, "com.integrosys.component.user.app.bus.ICommonUser", GLOBAL_SCOPE },
				{"theOBTrxContext", "com.integrosys.cms.app.transaction.OBTrxContext", FORM_SCOPE},
				{ IGlobalConstant.USER_TEAM, "com.integrosys.component.bizstructure.app.bus.ITeam", GLOBAL_SCOPE },
				{ "locale", "java.util.Locale", REQUEST_SCOPE },
				{ CMSGlobalSessionConstant.TEAM_TYPE_MEMBERSHIP_ID, "java.lang.String", GLOBAL_SCOPE },
				{ "dataFromCacheView", "java.util.concurrent.ConcurrentMap", SERVICE_SCOPE },
				{ "dataFromUpdLineFacilityMV", "java.util.concurrent.ConcurrentMap", SERVICE_SCOPE },
		};
	}
	public String[][] getResultDescriptor() {
		return (new String[][]{              
				{ "event", "java.lang.String", REQUEST_SCOPE },
				{ "total", "java.lang.String", REQUEST_SCOPE },
				{ "correct", "java.lang.String", REQUEST_SCOPE },
				{ "fail", "java.lang.String", REQUEST_SCOPE },
				{ "preUpload", "java.lang.String", REQUEST_SCOPE },
				{ "fileType", "java.lang.String", REQUEST_SCOPE },
				{"session.searchcomponentName", "java.lang.String", SERVICE_SCOPE},
				{"trxValueOut", "com.integrosys.cms.ui.fileUpload.FileUploadAction.IFileUploadTrxValue", SERVICE_SCOPE},
				{ "startIndex", "java.lang.String", REQUEST_SCOPE },
				{ "totalUploadedList", "java.util.List", SERVICE_SCOPE },
				{ "errorList", "java.util.List", SERVICE_SCOPE },
				{ "finalList", "java.util.List", SERVICE_SCOPE },
				{com.integrosys.base.uiinfra.common.Constants.GLOBAL_LOCALE_KEY, "java.util.Locale", GLOBAL_SCOPE},
				{"stagingReferenceId", "java.lang.String", SERVICE_SCOPE}
		});
	}
	public HashMap doExecute(HashMap map) throws CommandProcessingException, CommandValidationException {
		HashMap returnMap = new HashMap();
		HashMap resultMap = new HashMap();
		String event=(String)map.get("event");
		String path=(String)map.get("path");
		// Added By Dayananda Laishram on 26/02/2015 || UBS_UPLOAD_OPTIMIZATION | Start
		ConcurrentMap<String, String> dataFromUpdLineFacilityMV = new ConcurrentHashMap<String,String>();
		ConcurrentMap<String, String> dataFromCacheView = new ConcurrentHashMap<String,String>();
		dataFromCacheView = (ConcurrentMap)map.get("dataFromCacheView");
		dataFromUpdLineFacilityMV = (ConcurrentMap)map.get("dataFromUpdLineFacilityMV");
		DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### size of dataFromCacheView map is : "+dataFromCacheView.size());
		DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### size of dataFromUpdLineFacilityMV map is : "+dataFromUpdLineFacilityMV.size());
		//Added By Dayananda Laishram on 26/02/2015 || UBS_UPLOAD_OPTIMIZATION | Ends
		int countPass =0;
		int countFail =0;
		List finalList = new ArrayList();
		String preUpload="false";
		ArrayList totalUploadedList=new ArrayList();
		ArrayList errorList=new ArrayList();
		ArrayList consolidateList=new ArrayList();
		HashMap mp = new HashMap();
		ICommonUser user=(ICommonUser)map.get(IGlobalConstant.USER);
		ArrayList resultList=new ArrayList();
		IUbsErrorLog objUbsErrorLog = new OBUbsErrorLog();
		String fileType = "CSV";
		IFileUploadTrxValue trxValueOut = new OBFileUploadTrxValue();
		trxValueOut.setTransactionSubType("BAHRAIN_UPLOAD");
		DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 114 : "+user);
		IGeneralParamDao generalParamDao =(IGeneralParamDao)BeanHouse.get("generalParamDao");
		IGeneralParamGroup generalParamGroup  =generalParamDao.getGeneralParamGroupByGroupType("actualGeneralParamGroup", "GENERAL_PARAM");
		IGeneralParamEntry[]generalParamEntries= generalParamGroup.getFeedEntries();
		Date applicationDate=new Date();
		for(int i=0;i<generalParamEntries.length;i++){
			if(generalParamEntries[i].getParamCode().equals("APPLICATION_DATE")){
				applicationDate=new Date(generalParamEntries[i].getParamValue());
			}
		}
		Date d = DateUtil.getDate();
		applicationDate.setHours(d.getHours());
		applicationDate.setMinutes(d.getMinutes());
		applicationDate.setSeconds(d.getSeconds());
		DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 128 : "+applicationDate);
		//below code create master transaction
		try{
			OBTrxContext ctx = (OBTrxContext) map.get("theOBTrxContext");
			File file=new File(path);
			DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 133 : "+path);
			FileInputStream readFile=new FileInputStream(path);    	 
			String fileName=file.getName();
			DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 136 : "+file.getName());
			IFileUploadDao dao=(IFileUploadDao)BeanHouse.get("fileUploadDao");
			IFileUploadJdbc jdbc=(IFileUploadJdbc)BeanHouse.get("fileUploadJdbc");
			if(fileName.endsWith(".xls")||fileName.endsWith(".XLS") || fileName.endsWith(".xlsx") || fileName.endsWith(".XLSX")){  
				ProcessDataFile dataFile = new ProcessDataFile();
				resultList = dataFile.processFileUpload(file, ICMSConstant.BAHRAIN_UPLOAD);
				DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 142 : "+dataFile.getErrorList());
				if(resultList!=null)
					DefaultLogger.debug(this, "########### resultList size is :##########In UploadBahrainFileCmd ##### line no 144 : "+resultList.size());
				HashMap eachDataMap = (HashMap)dataFile.getErrorList();
				DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 146 : ");
				List list = new ArrayList(eachDataMap.values());
				if(list!=null)
					DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 149 : "+list.size());
				
				DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 151 : ");
				if(list!=null && list.size()>0){
					for(int i =0;i<list.size();i++){
						DefaultLogger.debug(this, "#########Inside for loop when list.size()>0 ############In UploadBahrainFileCmd ##### line no 153 : "+list.size());
						String[][] errList = (String[][])list.get(i);
						String str = "";
						for(int p=0;p<6;p++){
							for(int j=0;j<4;j++){
								str = str+((errList[p][j]==null)?"":errList[p][j]+";");
							}
						str=str+"||";
						}
						finalList.add(str);
					}
				}
				DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 164 : ");
				
				if(resultList!= null && resultList.size()>0){
					DefaultLogger.debug(this, "#########Inside if condition resultList.size() is: ##########In UploadBahrainFileCmd ##### line no 167 : "+resultList.size());
					mp = (HashMap)jdbc.insertBahrainfile(resultList, this,fileName,user,applicationDate,dataFromCacheView,dataFromUpdLineFacilityMV);
					totalUploadedList = (ArrayList) mp.get("totalUploadedList");
					
					//jdbc.updateXrefStageAmount(totalUploadedList,"BAHRAIN");
					errorList = (ArrayList) mp.get("errorList");
					if(totalUploadedList!= null && totalUploadedList.size()>0){
						DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 164 : "+totalUploadedList.size());
						IFileUpload fileObj=new OBFileUpload();
						fileObj.setFileType("BAHRAIN_UPLOAD");
						fileObj.setUploadBy(ctx.getUser().getLoginID());
						fileObj.setUploadTime(applicationDate);
						fileObj.setFileName(fileName);
						fileObj.setTotalRecords(String.valueOf(resultList.size()));  
						for(int i=0;i<totalUploadedList.size();i++){
							OBBahrainFile bahrainRecord=(OBBahrainFile)totalUploadedList.get(i);
							if(bahrainRecord.getStatus().equals("PASS")){
								countPass++;
							}else if(bahrainRecord.getStatus().equals("FAIL")){
								countFail++;
							}
						}
						fileObj.setApproveRecords(String.valueOf(countPass));
						trxValueOut = getFileUploadProxy().makerCreateFile(ctx,fileObj);
						if(trxValueOut!=null){
							for(int i=0;i<totalUploadedList.size();i++){
								OBBahrainFile bahrainRecord=(OBBahrainFile)totalUploadedList.get(i);
								bahrainRecord.setFileId(Long.parseLong(trxValueOut.getStagingReferenceID()));
								consolidateList.add(bahrainRecord);
							}
							for(int i=0;i<errorList.size();i++){
								OBBahrainFile bahrainRecord=(OBBahrainFile)errorList.get(i);
								bahrainRecord.setFileId(Long.parseLong(trxValueOut.getStagingReferenceID()));
								consolidateList.add(bahrainRecord);
							}
							
							int batchSize = 200;
							for (int j = 0; j < consolidateList.size(); j += batchSize) {
								List<OBBahrainFile> batchList = consolidateList.subList(j, j + batchSize > consolidateList.size() ? consolidateList.size() : j + batchSize);
								jdbc.createEntireBahrainStageFile(batchList);
							}
						}
					}
					DefaultLogger.debug(this, "#####################In UploadBahrainFileCmd ##### line no 202 : "+errorList.size());
					// Added By Dayananda Laishram on 31/03/2015 || SP_FACILITY_RELEASE 
					// 03/02/2016: Uma Khot:Commented below code as not to update Released Amount from upload file.
				//	jdbc.spUpdateReleasedAmount("BAHRAIN_UPLOAD","STAGE");
				}
				
			}
			else{
				fileType="NOT_CSV";
			}
		}
		catch(FileUploadException ex){
			DefaultLogger.debug(this, "got exception in doExecute" + ex);
			ex.printStackTrace();
			//throw (new CommandProcessingException(ex.getMessage()));
		} catch (TrxParameterException e) {
			new CommandProcessingException(e.getMessage());
		} catch (Exception e) { 
			DefaultLogger.debug(this, "got exception in doExecute" + e);
			e.printStackTrace();
			//throw (new CommandProcessingException(e.getMessage()));
		}
		//end of master transaction
		resultMap.put("event", event);
		resultMap.put("fileType", fileType);
		resultMap.put("preUpload", preUpload);
		resultMap.put("trxValueOut", trxValueOut);
		if(null != trxValueOut) {
			resultMap.put("stagingReferenceId", trxValueOut.getStagingReferenceID());
		}
		resultMap.put("totalUploadedList", totalUploadedList);
		resultMap.put("errorList", errorList);
		resultMap.put("finalList", finalList);
		resultMap.put("total", String.valueOf(resultList.size()+finalList.size()));
		resultMap.put("correct", String.valueOf(countPass));
		resultMap.put("fail", String.valueOf(countFail+finalList.size()));
		returnMap.put(ICommonEventConstant.COMMAND_RESULT_MAP, resultMap);
		return returnMap;
	}

}
