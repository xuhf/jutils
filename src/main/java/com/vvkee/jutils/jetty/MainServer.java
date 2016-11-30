package com.vvkee.jutils.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;

import com.vvkee.jutils.jetty.handler.HelloHandler;

public class MainServer {

	public static void main(String[] args) throws Exception {

		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(9999);
		server.setConnectors(new Connector[] { connector });

		ContextHandler helloContext = new ContextHandler("/hello");
		helloContext.setHandler(new HelloHandler());
		helloContext.setAllowNullPathInfo(true);

		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { helloContext, new DefaultHandler() });
		server.setHandler(handlers);
		server.start();
		server.join();
	}
}
