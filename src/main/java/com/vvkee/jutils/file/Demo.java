package com.vvkee.jutils.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Demo {

	public static void main(String[] args) throws IOException {
		String path = "e://aaa/ax.txt";
		Map<String, String> sqls = Maps.newLinkedHashMap();
		for (int i = 0; i < 10; i++) {
			if (i != 5) {
				sqls.put("this is i index , i is " + i,
						"i is " + i + ", and now is " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			} else {
				sqls.put("this is i index , i is " + i, "-2");
			}

		}

		List<String> results = Lists.newArrayList();
		for (String key : sqls.keySet()) {
			String value = sqls.get(key);
			if (!value.equals("-2")) {
				results.add("# " + key);
				results.add(value);
			}
		}
		FileUtils.writeLines(new File(path), "UTF-8", results, false);
	}
}
