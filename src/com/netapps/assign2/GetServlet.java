package com.netapps.assign2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetServlet extends HttpServlet{

	public static void main(String[] args) {

	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().write("You made it to the get servlet, GET style.");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		//get data sent
		BufferedReader reader = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			sb.append(line+"\n");
			line = reader.readLine();
		}
		reader.close();
		String data = sb.toString();
		
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) new JSONParser().parse(data);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		} 
		
		//base directory
		String res = getServletConfig().getServletContext().getRealPath(File.separator + "res");
		String gcid = (String) json.get("gcid");
		String sCurrentLine;
		String exists = "1";
		
		//check directory exists
		File dir = new File(res, gcid);
		if (!dir.exists()) {
			exists = "0";
		}
		
		//get the coords
		String coords = "";
		BufferedReader br1 = null;
		try {
			br1 = new BufferedReader(new FileReader(res + File.separator + gcid + File.separator + "coords.txt"));
			while ((sCurrentLine = br1.readLine()) != null) {
				coords = coords + sCurrentLine;
			}
		} catch (IOException e) {			
		} finally {
			try {
				if (br1 != null) br1.close();
			} catch (IOException ex) {}
		}
		
		//get the image
		String image = "";
		BufferedReader br2 = null;
		try {
			br2 = new BufferedReader(new FileReader(res + File.separator + gcid + File.separator + "image.txt"));
			while ((sCurrentLine = br2.readLine()) != null) {
				image = image + sCurrentLine;
			}
		} catch (IOException e) {			
		} finally {
			try {
				if (br2 != null) br2.close();
			} catch (IOException ex) {}
		}
		
		//get the light
		String light = "";
		BufferedReader br3 = null;
		try {
			br3 = new BufferedReader(new FileReader(res + File.separator + gcid + File.separator + "light.txt"));
			while ((sCurrentLine = br3.readLine()) != null) {
				light = light + sCurrentLine;
			}
		} catch (IOException e) {			
		} finally {
			try {
				if (br3 != null) br3.close();
			} catch (IOException ex) {}
		}
		
		//get the light
		String words = "";
		BufferedReader br4 = null;
		try {
			br4 = new BufferedReader(new FileReader(res + File.separator + gcid + File.separator + "words.txt"));
			while ((sCurrentLine = br4.readLine()) != null) {
				words = words + sCurrentLine;
			}
		} catch (IOException e) {			
		} finally {
			try {
				if (br4 != null) br4.close();
			} catch (IOException ex) {}
		}
		
		
		String send = "{ \"exists\":\"" + exists + "\" , \"coords\":\"" + coords + "\" , \"image\":\"" + image + "\" , \"light\":\"" + light + "\" , \"words\":\"" + words + "\" }";
		resp.getWriter().write(send);
		
	}

}
