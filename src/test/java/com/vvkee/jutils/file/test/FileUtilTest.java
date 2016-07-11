package com.vvkee.jutils.file.test;

import org.junit.Test;

import com.vvkee.jutils.file.FileUtil;

public class FileUtilTest {

	@Test
	public void testMerge() throws Exception {
		String source = "F:\\aaa11.txt";
		String target = "F:\\bbb.txt";
		FileUtil.merge(source, target);
	}
}
