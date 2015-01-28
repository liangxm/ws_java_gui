package com.yjm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	// �ֻ���ƥ��
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches()+"---");
		return m.matches();
	}

	// �Ƿ��Ǳ�ʾ���������
	public static boolean isAge(String string) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,2}");
		Matcher isNum = pattern.matcher(string);
		return isNum.matches();
	}

	// �ж��Ƿ��������ַ�����yyyy-MM-dd��
	public static boolean isDate(String string) {
		Pattern pattern = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");
		Matcher isNum = pattern.matcher(string);
		return isNum.matches();
	}

	// �ж��Ƿ�������
	public static boolean isPositiveInteger(String orginal) {
		return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
	}

	// ������ʽƥ��
	private static boolean isMatch(String regex, String orginal) {
		if (orginal == null || orginal.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum = pattern.matcher(orginal);
		return isNum.matches();
	}

	// �ж��Ƿ��Ǹ�������
	public static boolean isDecimal(String orginal) {
		return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
	}

	// ���֤������֤
	public static boolean checkNID(String idNum) {
		Pattern idNumPattern = Pattern
				.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		Matcher idNumMatcher = idNumPattern.matcher(idNum);
		return idNumMatcher.matches();
	}
}
