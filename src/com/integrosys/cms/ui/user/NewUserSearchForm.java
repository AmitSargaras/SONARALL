package com.integrosys.cms.ui.user;import com.integrosys.base.uiinfra.common.CommonSearchForm;/** *  * @author $Author: sathish $<br> *  * @version $Revision: 1.2 $ *  * @since $Date: 2003/08/21 08:37:47 $ *  *        Tag: $Name: $ */public class NewUserSearchForm extends CommonSearchForm implements java.io.Serializable {	private String userId = "";	private String selUserId = "";	private String selType = "";	private String userName = "";	private String[] pageUsers;	private String[] checkedUsers;	public String[] getPageUsers() {		return pageUsers;	}	public void setPageUsers(String[] pageUsers) {		this.pageUsers = pageUsers;	}	public String[] getCheckedUsers() {		return checkedUsers;	}	public void setCheckedUsers(String[] checkedUsers) {		this.checkedUsers = checkedUsers;	}	public String getUserId() {		return userId;	}	public void setUserId(String id) {		userId = id;	}	public String getSelUserId() {		return selUserId;	}	public void setSelUserId(String id) {		selUserId = id;	}	public String getSelType() {		return selType;	}	public void setSelType(String id) {		selType = id;	}	public String getUserName() {		return userName;	}	public void setUserName(String userName) {		this.userName = userName;	}	public void reset() {		setUserId("");		setSelUserId("");		setSelType("");	}	public String[][] getMapper() {		String[][] input = {		{ "CommonUserSearchCriteria", "com.integrosys.cms.ui.user.UserListMapper" },		};		return input;	}}