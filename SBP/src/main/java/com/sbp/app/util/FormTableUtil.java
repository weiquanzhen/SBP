package com.sbp.app.util;

public class FormTableUtil {
	private FormTableUtil() {}
	public static FormTableUtil getInstance() {
		return FormTableUtilCore.instance;
	}
	static class FormTableUtilCore{
		public static final FormTableUtil instance = new FormTableUtil();
	}
	public boolean checkPhone(String phone){
		if(StringUtil.isEnpty(phone))return false;
		return true;
	}
}
