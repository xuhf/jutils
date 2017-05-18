package com.vvkee.jutils.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Main {

	public static void main(String[] args) throws IOException {
		int max = 10000;
		String dir = "C:\\Users\\xuhf\\Desktop\\ultra-log\\20161216";
		String path = dir + "\\20161216.txt";
		File f = new File(path);
		List<String> lines = FileUtils.readLines(f, "UTF-8");
		List<List<String>> pages = splitList(lines, max);
		for (int i = 0; i < pages.size(); i++) {
			String p = dir + File.separator + "20161216_" + i + ".txt";
			FileUtils.writeLines(new File(p), pages.get(i), "\r\n");
		}
	}

	private static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		int listSize = list.size();
		int page = (listSize + (pageSize - 1)) / pageSize;
		List<List<T>> listArray = new ArrayList<List<T>>();
		for (int i = 0; i < page; i++) {
			List<T> subList = new ArrayList<T>();
			for (int j = 0; j < listSize; j++) {
				int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
				if (pageIndex == (i + 1)) {
					subList.add(list.get(j));
				}
				if ((j + 1) == ((j + 1) * pageSize)) {
					break;
				}
			}
			listArray.add(subList);
		}
		return listArray;
	}

}
