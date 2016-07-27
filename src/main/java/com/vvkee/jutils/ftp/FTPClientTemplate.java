package com.vvkee.jutils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPClientTemplate {

	private String host;
	private int port;
	private String username;
	private String password;

	private boolean binaryTransfer = true;
	private boolean passiveMode = true;
	private String encoding = "UTF-8";
	private int clientTimeout = 3000;
	private boolean flag = true;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBinaryTransfer() {
		return binaryTransfer;
	}

	public void setBinaryTransfer(boolean binaryTransfer) {
		this.binaryTransfer = binaryTransfer;
	}

	public boolean isPassiveMode() {
		return passiveMode;
	}

	public void setPassiveMode(boolean passiveMode) {
		this.passiveMode = passiveMode;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public int getClientTimeout() {
		return clientTimeout;
	}

	public void setClientTimeout(int clientTimeout) {
		this.clientTimeout = clientTimeout;
	}

	private FTPClient getFTPClient() throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding(encoding);

		connect(ftpClient);
		if (passiveMode) {
			ftpClient.enterLocalPassiveMode();
		}
		setFileType(ftpClient);

		try {
			ftpClient.setSoTimeout(clientTimeout);
		} catch (SocketException e) {
			throw new Exception("Set timeout error.", e);
		}

		return ftpClient;
	}

	/**
	 * �����ļ���������
	 * 
	 * @throws Exception
	 * @throws IOException
	 */
	private void setFileType(FTPClient ftpClient) throws Exception {
		try {
			if (binaryTransfer) {
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			} else {
				ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
			}
		} catch (IOException e) {
			throw new Exception("Could not to set file type.", e);
		}
	}

	public boolean connect(FTPClient ftpClient) throws Exception {
		try {
			ftpClient.connect(host, port);

			int reply = ftpClient.getReplyCode();

			if (FTPReply.isPositiveCompletion(reply)) {
				if (ftpClient.login(username, password)) {
					setFileType(ftpClient);
					return true;
				}
			} else {
				ftpClient.disconnect();
				throw new Exception("FTP server refused connection.");
			}
		} catch (IOException e) {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e1) {
					throw new Exception("Could not disconnect from server.", e);
				}

			}
			throw new Exception("Could not connect to server.", e);
		}
		return false;
	}

	/**
	 * �Ͽ�ftp����
	 * 
	 * @throws Exception
	 */
	private void disconnect(FTPClient ftpClient) throws Exception {
		try {
			ftpClient.logout();
			if (ftpClient.isConnected()) {
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			throw new Exception("Could not disconnect from server.", e);
		}
	}

	// ---------------------------------------------------------------------
	// public method
	// ---------------------------------------------------------------------
	/**
	 * �ϴ�һ�������ļ���Զ��ָ���ļ�
	 * 
	 * @param serverFile
	 *            ���������ļ���(��������·��)
	 * @param localFile
	 *            �����ļ���(��������·��)
	 * @return �ɹ�ʱ������true��ʧ�ܷ���false
	 * @throws Exception
	 */
	public boolean put(String serverFile, String localFile) throws Exception {
		return put(serverFile, localFile, false);
	}

	public boolean put(String serverFile, String localFile, boolean delFile) throws Exception {
		FTPClient ftpClient = null;
		InputStream input = null;
		try {
			ftpClient = getFTPClient();
			input = new FileInputStream(localFile);
			ftpClient.storeFile(serverFile, input);
			input.close();
			if (delFile) {
				(new File(localFile)).delete();
			}
			return true;
		} catch (FileNotFoundException e) {
			throw new Exception("local file not found.", e);
		} catch (IOException e) {
			throw new Exception("Could not put file to server.", e);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (Exception e) {
				throw new Exception("Couldn't close FileInputStream.", e);
			}
			if (ftpClient != null) {
				disconnect(ftpClient);
			}
		}
	}

	/**
	 * ����һ��Զ���ļ������ص�ָ���ļ�
	 * 
	 * @param serverFile
	 *            ���������ļ���(��������·��)
	 * @param localFile
	 *            �����ļ���(��������·��)
	 * @return �ɹ�ʱ������true��ʧ�ܷ���false
	 * @throws Exception
	 */
	public boolean get(String serverFile, String localFile) throws Exception {
		return get(serverFile, localFile, false);
	}

	/**
	 * ����һ��Զ���ļ������ص�ָ���ļ�
	 * 
	 * @param serverFile
	 *            ���������ļ���(��������·��)
	 * @param localFile
	 *            �����ļ���(��������·��)
	 * @return �ɹ�ʱ������true��ʧ�ܷ���false
	 * @throws Exception
	 */
	public boolean get(String serverFile, String localFile, boolean delFile) throws Exception {
		OutputStream output = null;
		try {
			output = new FileOutputStream(localFile);
			return get(serverFile, output, delFile);
		} catch (FileNotFoundException e) {
			throw new Exception("local file not found.", e);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				throw new Exception("Couldn't close FileOutputStream.", e);
			}
		}
	}

	public boolean get(String serverFile, OutputStream output) throws Exception {
		return get(serverFile, output, false);
	}

	public boolean get(String serverFile, OutputStream output, boolean delFile) throws Exception {
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient();
			ftpClient.retrieveFile(serverFile, output);
			if (delFile) {
				ftpClient.deleteFile(serverFile);
			}
			return true;
		} catch (IOException e) {
			throw new Exception("Couldn't get file from server.", e);
		} finally {
			if (ftpClient != null) {
				disconnect(ftpClient);
			}
		}
	}

	public boolean delete(String delFile) throws Exception {
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient();
			ftpClient.deleteFile(delFile);
			return true;
		} catch (IOException e) {
			throw new Exception("Couldn't delete file from server.", e);
		} finally {
			if (ftpClient != null) {
				disconnect(ftpClient);
			}
		}
	}

	/**
	 * ����ɾ��
	 * 
	 * @param delFiles
	 * @return
	 * @throws Exception
	 */
	public boolean delete(String[] delFiles) throws Exception {
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient();
			for (String s : delFiles) {
				ftpClient.deleteFile(s);
			}
			return true;
		} catch (IOException e) {
			throw new Exception("Couldn't delete file from server.", e);
		} finally {
			if (ftpClient != null) {
				disconnect(ftpClient); // �Ͽ�����
			}
		}
	}

	/**
	 * �г�Զ��Ĭ��Ŀ¼�����е��ļ�
	 * 
	 * @return Զ��Ĭ��Ŀ¼�������ļ�����б?Ŀ¼�����ڻ���Ŀ¼��û���ļ�ʱ����0���ȵ�����
	 * @throws Exception
	 */
	public String[] listNames() throws Exception {
		return listNames(null);
	}

	/**
	 * �г�Զ��Ŀ¼�����е��ļ�
	 * 
	 * @param remotePath
	 *            Զ��Ŀ¼��
	 * @return Զ��Ŀ¼�������ļ�����б?Ŀ¼�����ڻ���Ŀ¼��û���ļ�ʱ����0���ȵ�����
	 * @throws Exception
	 */
	public String[] listNames(String remotePath) throws Exception {
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient();
			String[] listNames = ftpClient.listNames(remotePath);
			return listNames;
		} catch (IOException e) {
			throw new Exception("�г�Զ��Ŀ¼�����е��ļ�ʱ�����쳣", e);
		} finally {
			if (ftpClient != null) {
				disconnect(ftpClient); // �Ͽ�����
			}
		}
	}

	public boolean isExist(String remoteFilePath) throws Exception {

		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient();
			File file = new File(remoteFilePath);

			String remotePath = remoteFilePath.substring(0, (remoteFilePath.indexOf(file.getName()) - 1));
			String[] listNames = ftpClient.listNames(remotePath);
			System.out.println(remoteFilePath);
			for (int i = 0; i < listNames.length; i++) {

				if (remoteFilePath.equals(listNames[i])) {
					flag = true;
					System.out.println("�ļ�:" + file.getName() + "�Ѿ�������");
					break;

				} else {
					flag = false;
				}
			}

		} catch (IOException e) {
			throw new Exception("��ѯ�ļ��Ƿ�����ļ�ʱ�����쳄1�7", e);
		} finally {
			if (ftpClient != null) {
				disconnect(ftpClient); // �Ͽ�����
			}
		}
		return flag;
	}
}
