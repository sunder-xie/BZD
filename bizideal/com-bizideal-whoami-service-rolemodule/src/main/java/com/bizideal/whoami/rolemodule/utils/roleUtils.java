package com.bizideal.whoami.rolemodule.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class roleUtils {

	public static String listToString(List<Integer> list) {
		Collections.sort(list);
		StringBuffer fields = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 == list.size()) {
				fields.append(list.get(i));
			} else {
				fields.append(list.get(i) + ",");
			}
		}
		return fields.toString();
	}

	public static List<Integer> stringToList(String fields) {

		String[] split = fields.split(",");
		List list = new ArrayList<>();
		for (int i = 0; i < split.length; i++) {
			list.add(Integer.valueOf(split[i]));
		}
		return list;
	}

	// public static void main(String[] args) {
	// List list = new ArrayList<>();
	// list.add(1);
	// list.add(2);
	// list.add(3);
	// list.add(4);
	// StringBuffer listToString = listToString(list);
	// System.out.println(listToString);
	// List stringToList = stringToList(listToString);
	// System.out.println(stringToList);
	//
	// }
}
