package com.bizideal.whoami.utils.cloopen;

public class RandomNum {

	public static String randomNumber() {
		String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
		return code;
	}
}
