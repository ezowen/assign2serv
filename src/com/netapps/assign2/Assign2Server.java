package com.netapps.assign2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


public class Assign2Server extends HttpServlet{
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8888);
		
		WebAppContext context = new WebAppContext();
		context.setWar("war");
		context.setContextPath("/");
		server.setHandler(context);
		
		server.start();
		server.join();

	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.getWriter().write("You have reached the doGet method");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.getWriter().write("You have reached the doPost method");
	}

}


