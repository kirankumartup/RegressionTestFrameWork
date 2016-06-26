package com.helper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileManager {
	private Properties properties = null;
	public String getValue(String strFileName, String strKey) {
		String strValue = null;
		try {
			properties = new Properties();
			File inputFile = new File(strFileName);
			InputStream inputStream = new FileInputStream(inputFile.getAbsolutePath());
			properties.load(inputStream);
			inputStream.close();
			strValue = properties.getProperty(strKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strKey + "~" + strValue;
	}
}
