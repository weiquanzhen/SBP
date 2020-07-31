package com.sbp.app.util;

public class StringUtil {
	public static boolean isEnpty(String str) {
		if(str == null||str.trim()=="")return true;
		return false;
	}
}
