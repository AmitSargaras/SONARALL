package com.integrosys.cms.ui.excLineforstpsrm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.integrosys.base.uiinfra.common.AbstractCommonMapper;
import com.integrosys.base.uiinfra.common.CommonForm;
import com.integrosys.base.uiinfra.exception.MapperException;
import com.integrosys.cms.app.excLineforstpsrm.bus.IExcLineForSTPSRM;
import com.integrosys.cms.app.excLineforstpsrm.bus.OBExcLineForSTPSRM;
import com.integrosys.cms.ui.common.constant.IGlobalConstant;
import com.integrosys.component.user.app.bus.ICommonUser;

public class ExcLineForSTPSRMMapper extends AbstractCommonMapper {

	@Override
	public Object mapFormToOB(CommonForm cForm, HashMap map) throws MapperException {
		ExcLineForSTPSRMForm form = (ExcLineForSTPSRMForm) cForm;
		ICommonUser user = (ICommonUser) map.get(IGlobalConstant.USER);
		IExcLineForSTPSRM obItem = new OBExcLineForSTPSRM();

		try {
			if (StringUtils.isNotEmpty(form.getLineCode())) {
				obItem.setLineCode(form.getLineCode());
			}

			if (StringUtils.isNotEmpty(form.getExcluded())) {
				obItem.setExcluded(form.getExcluded());
			}

			if (StringUtils.isNotEmpty(form.getDeprecated())) {
				obItem.setDeprecated(form.getDeprecated());
			} else {
				obItem.setDeprecated("N");
			}

			if (StringUtils.isNotEmpty(form.getId())) {
				obItem.setId(Long.parseLong(form.getId()));
			}

			if (StringUtils.isNotEmpty(form.getCreateBy())) {
				obItem.setCreateBy(form.getCreateBy());
			} else {
				obItem.setCreateBy(user.getLoginID());
			}

			if (StringUtils.isNotEmpty(form.getLastUpdateBy())) {
				obItem.setLastUpdateBy(form.getLastUpdateBy());
			} else {
				obItem.setLastUpdateBy(user.getLoginID());
			}

			if (StringUtils.isNotEmpty(form.getLastUpdateDate())) {
				obItem.setLastUpdateDate(new SimpleDateFormat("dd/MMM/yyyy").parse(form.getLastUpdateDate()));
			} else {
				obItem.setLastUpdateDate(new Date());
			}

			if (StringUtils.isNotEmpty(form.getCreationDate())) {
				obItem.setCreationDate(new SimpleDateFormat("dd/MMM/yyyy").parse(form.getCreationDate()));
			} else {
				obItem.setCreationDate(new Date());
			}

			if (StringUtils.isNotEmpty(form.getVersionTime())) {
				obItem.setVersionTime(Long.parseLong(form.getVersionTime()));
			}else
				obItem.setVersionTime(0l);
				
			
			if (StringUtils.isNotEmpty(form.getStatus()))
				obItem.setStatus(form.getStatus());
			else
				obItem.setStatus("ACTIVE");

			if (StringUtils.isNotEmpty(form.getOperationName())) {
				obItem.setOperationName(form.getOperationName());
			}

			return obItem;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MapperException("failed to map form to ob of Exclusion Line for STP for SRM ", ex);
		}
	}

	@Override
	public CommonForm mapOBToForm(CommonForm cForm, Object cObj, HashMap map) throws MapperException {
		ExcLineForSTPSRMForm form = (ExcLineForSTPSRMForm) cForm;
		IExcLineForSTPSRM item = (IExcLineForSTPSRM) cObj;

		form.setLineCode(item.getLineCode());
		form.setExcluded(item.getExcluded());
		form.setCreateBy(item.getCreateBy());
		form.setLastUpdateBy(item.getLastUpdateBy());
		form.setLastUpdateDate(new SimpleDateFormat("dd/MMM/yyyy").format(item.getLastUpdateDate()));
		form.setCreationDate(new SimpleDateFormat("dd/MMM/yyyy").format(item.getCreationDate()));
		form.setVersionTime(Long.toString(item.getVersionTime()));
		form.setDeprecated(item.getDeprecated());
		form.setId(Long.toString(item.getId()));
		form.setStatus(item.getStatus());

		return cForm;
	}
	
	public String[][] getParameterDescriptor() {
		return (new String[][] {
				{ IGlobalConstant.USER, ICommonUser.class.getName(), GLOBAL_SCOPE }
			});
	}

}