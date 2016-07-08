package com.vvkee.jutils.code;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class Test {

	public static void main(String[] args) {
		addColumn();
		setValue();
	}

	public static void addColumn() {
		String[] attrs = new String[] { "specialStatus", "basicStatus", "themeStatus", "appStatus", "fusionIdStatus",
				"loAndLtStatus", "lacciStatus", "uaStatus", "swordStatus" };

		String sql = "alter table t_clouder2_param %s varchar2(16);";

		for (String a : attrs) {
			String s = DatabaseUtil.propertyToField(a);
			String f = String.format(sql, s);
			System.out.println(f);
		}
	}

	public static void setValue() {
		String[] attrs = new String[] { "special_status", "basic_status", "theme_status", "app_status",
				"fusion_id_status", "lo_and_lt_status", "lacci_status", "ua_status", "sword_status" };

		String sql = "update t_clouder2_param set %s";

		List<String> list = Lists.newArrayList();
		for (String a : attrs) {
			String s = a + " = " + a.replace("_status", "");
			list.add(s);
		}

		String f = String.format(sql, Joiner.on(",").join(list));
		System.out.println(f);
	}
}
