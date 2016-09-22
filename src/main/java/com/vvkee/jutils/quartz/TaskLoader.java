package com.vvkee.jutils.quartz;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Lists;

public class TaskLoader {

	private static String TASK_LOCATION = "quartz.xml";

	public static void main(String[] args) throws Exception {
		TaskLoader l = new TaskLoader();
		l.load();
	}

	public List<Task> load() {
		List<Task> list = Lists.newArrayList();
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(false);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(TASK_LOCATION);
			Document doc = db.parse(in);
			NodeList nodeList = (NodeList) xpath.evaluate("/tasks/task", doc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				Task task = new Task();
				task.setDisplay(readValue(xpath, "display", n));
				task.setName(readValue(xpath, "name", n));
				task.setClazz(readValue(xpath, "clazz", n));
				task.setMethod(readValue(xpath, "method", n));
				task.setCrontab(readValue(xpath, "crontab", n));
				task.setGroup("myGroup");
				task.setContainerInit(readValue(xpath, "isContainerInit", n).equals("true"));
				task.setConcurrent(readValue(xpath, "isConcurrent", n).equals("true"));
				list.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String readValue(XPath xpath, String name, Node root) throws Exception {
		Node attributeNode = (Node) xpath.evaluate(name, root, XPathConstants.NODE);
		return attributeNode.getTextContent();
	}

}
