package com.vvkee.jutils.xml;

import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPathTest {

	@Test
	public void testXPathRead() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("src/main/resources/users.xml");
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		NodeList nodeList = (NodeList) xpath.evaluate("users/user", doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			String name = (String) xpath.evaluate("name", node, XPathConstants.STRING);
			String password = (String) xpath.evaluate("password", node, XPathConstants.STRING);
			String category = (String) xpath.evaluate("category", node, XPathConstants.STRING);
			Set<String> ips = new HashSet<String>();
			NodeList ipNodes = (NodeList) xpath.evaluate("whiteIps/*", node, XPathConstants.NODESET);
			for (int j = 0; j < ipNodes.getLength(); j++) {
				Node ipNode = ipNodes.item(j);
				ips.add(ipNode.getFirstChild().getNodeValue());
			}
			String interfaces = (String) xpath.evaluate("interfaces", node, XPathConstants.STRING);
			interfaces = StringUtils.trim(interfaces);
			String interfacesLimit = (String) xpath.evaluate("interfacesLimit", node, XPathConstants.STRING);
			interfacesLimit = StringUtils.trim(interfacesLimit);
			String charge = (String) xpath.evaluate("charge", node, XPathConstants.STRING);
			charge = StringUtils.trim(charge);
			String isLimitLog = (String) xpath.evaluate("isLimitLog", node, XPathConstants.STRING);
			String isLimitConcurrency = (String) xpath.evaluate("isLimitConcurrency", node, XPathConstants.STRING);
			System.out.println(name);
			System.out.println(password);
			System.out.println(category);
			System.out.println(ips);
			System.out.println(interfaces);
			System.out.println(charge);
			System.out.println(isLimitLog);
			System.out.println(isLimitConcurrency);
		}
	}

	@Test
	public void testWriteXml() {

	}
}
