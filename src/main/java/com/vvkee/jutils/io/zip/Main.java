package com.vvkee.jutils.io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

	public static void main(String[] args) throws Exception {
		zip();
	}

	private static void zip() throws Exception {
		String dir = "E:\\sword\\2016-07-05";
		String[] files = { "E:\\sword\\2016-07-05\\北京_2016-07-05.properties",
				"E:\\sword\\2016-07-05\\安徽_2016-07-05.properties", "E:\\sword\\2016-07-05\\广东_2016-07-05.properties",
				"E:\\sword\\2016-07-05\\河北_2016-07-05.properties", "E:\\sword\\2016-07-05\\湖北_2016-07-05.properties",
				"E:\\sword\\2016-07-05\\福建_2016-07-05.properties", "E:\\sword\\2016-07-05\\黑龙江_2016-07-05.properties" };

		File zipFile = new File(dir + File.separator + "aa.zip");

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));

		for (String file : files) {
			File f = new File(file);
			String province = f.getName().substring(0, f.getName().indexOf("_"));
			// province + File.separator + f.getName() 这个是相对于压岁文件的路径 如：a/aaa.txt
			// 那个在压缩文件里，a就是目录
			zos.putNextEntry(new ZipEntry(province + File.separator + f.getName()));
			FileInputStream in = new FileInputStream(file);
			int b;
			while ((b = in.read()) != -1) {
				zos.write(b);
			}
			in.close();
		}
		zos.closeEntry();
		zos.close();
	}
}
