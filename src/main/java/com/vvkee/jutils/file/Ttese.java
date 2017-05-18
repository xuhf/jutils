package com.vvkee.jutils.file;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Maps;

public class Ttese {

	public static void main(String[] args) throws Exception {
		String dir = "C:\\Users\\xuhf\\Desktop\\unicom";

		String sourcePath = "source.txt";
		String newPath = "new.txt";

		File sf = new File(dir + File.separator + sourcePath);
		File nf = new File(dir + File.separator + newPath);
		List<String> slines = FileUtils.readLines(sf, "UTF-8");
		List<String> nlines = FileUtils.readLines(nf, "UTF-8");
		Map<String, Integer> sMap = Maps.newHashMap();
		for (String s : slines) {
			String[] a = s.split(",");
			sMap.put(a[0] + "_" + a[3], Integer.parseInt(a[2]));
		}
		Map<String, Integer> nMap = Maps.newHashMap();
		for (String s : nlines) {
			String[] a = s.split(",");
			nMap.put(a[0] + "_" + a[3], Integer.parseInt(a[2]));
		}

		for (String key : sMap.keySet()) {
			Integer v = nMap.get(key);
			if (v == null) {
				System.out.println("在  source 中有但是在 new  中不存在" + key);
			}
		}

		for (String key : nMap.keySet()) {
			Integer v = sMap.get(key);
			if (v == null) {
				System.out.println("在  new 中有但是在 source  中不存在" + key);
				System.out.println("value is " + nMap.get(key));
			}
		}

	}
}
