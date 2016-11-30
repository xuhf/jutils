package com.vvkee.jutils.jetty.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class HelloHandler extends AbstractHandler {

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpServletResponse servletResponse = response;
		servletResponse.setContentType("text/html; charset=utf-8");
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		Map<String, Object> result = Maps.newConcurrentMap();
		List<String> list = Lists.newArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		result.put("success", true);
		result.put("status", "1");
		result.put("list", list);
		String resultStr = JSON.toJSONString(result);
		servletResponse.getWriter().print(resultStr);
		baseRequest.setHandled(true);
	}
}
