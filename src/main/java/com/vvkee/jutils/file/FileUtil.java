package com.vvkee.jutils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;

/**
 * 文件操作工具类
 * 
 * @author xuhf
 *
 */
public class FileUtil extends FileUtils {

	/**
	 * 获取文件MD5值<br/>
	 * 
	 * 缺点是文件越大，MD5获取消耗时间越长
	 * 
	 * @param file
	 * @return
	 */
	public static String getMD5(File file) {
		FileInputStream in = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = in.read(buffer)) != -1) {
				md5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(md5.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将文件target合并到文件source中
	 * 
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void merge(String source, String target) throws Exception {
		merge(new File(source), new File(target));
	}

	/**
	 * 将文件target合并到文件source中
	 * 
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void merge(File source, File target) throws Exception {
		int BUFSIZE = 1024 * 8;
		boolean newFile = false;
		if (!source.exists()) {
			newFile = true;
			source.createNewFile();
		}
		if (!target.exists()) {
			return;
		}

		FileChannel sourceChannel = null;
		FileChannel targetChannel = null;

		if (!newFile) {
			// 如果不是新文件，那么需要换行
			FileWriter fw = new FileWriter(source, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.flush();
			fw.close();
			bw.close();
		}
		sourceChannel = new FileOutputStream(source, true).getChannel();
		targetChannel = new FileInputStream(target).getChannel();
		ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
		while (targetChannel.read(bb) != -1) {
			bb.flip();
			sourceChannel.write(bb);
			bb.clear();
		}
		sourceChannel.close();
		targetChannel.close();
	}

	/**
	 * 将多个文件合并到source里
	 * 
	 * @param source
	 * @param target
	 */
	public void merge(File source, File... target) throws Exception {
		if (!source.exists()) {
			source.createNewFile();
		}
		
		
	}

	/**
	 * Java 创建ZIP示例
	 * 
	 * @throws Exception
	 */
	public static void zip() throws Exception {
		String dir = "E:\\";
		String[] files = { "E:\\ll_a.txt", "E:\\ll_b.txt", "E:\\ll_c.txt", "E:\\lb_a.txt", "E:\\lb_b.txt",
				"E:\\lb_c.txt" };
		File zipFile = new File(dir + File.separator + "aa.zip");
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
		for (String file : files) {
			File f = new File(file);
			String zipDir = f.getName().substring(0, f.getName().indexOf("_"));
			// 那个在压缩文件里，zipDir就是目录
			zos.putNextEntry(new ZipEntry(zipDir + File.separator + f.getName()));
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
