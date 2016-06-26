package com.company.pages.callScripts;

import com.helper.utils.PropertiesFileManager;


public class OrangeHRMLoginPageCallScript {
 
	 public static String getKeyValue(String strKey){
		 final String strFileName = "src\\main\\resources\\objectsRepository\\OrangeHRMLoginPageObjects.properties";
		 return  (new PropertiesFileManager()).getValue(strFileName, strKey).trim();
	 }
	
	public static String getTxtUsername(){
		return getKeyValue("OrangeHRMLoginPage_Txt_Username_Xpath");
	}
	
	public static String getTxtPassword(){
		return getKeyValue("OrangeHRMLoginPage_Txt_Password_Xpath");
	}
	
	public static String getElmErrorMessage(){
		return getKeyValue("OrangeHRMLoginPage_Elm_ErrorMessage_Xpath");
	}
	
	public static String getBtnLogin(){
		return getKeyValue("OrangeHRMLoginPage_Btn_Login_Xpath");
	}
}