package com.netapps.assign2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class PostServlet extends HttpServlet{
//post in the class name refers to posting a new solution, not http POST
	public static void main(String[] args) {

	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().write("You made it to the post servlet, GET style.");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().write("You made it to the post servlet, POST style");
		
		//Get data sent
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
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		//base directory
		String res = getServletConfig().getServletContext().getRealPath(File.separator + "res");
		
		//check directory exists, if not create it
		File dir = new File(res, (String) json.get("gcid"));
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		String path = res + File.separator + json.get("gcid");
		
		//get all values from json
		String image = (String) json.get("image");
		String light = (String) json.get("light");
		String words = (String) json.get("words");
		String latitude = (String) json.get("latitude");
		String longitude = (String) json.get("longitude");
		String coords = latitude + ", " + longitude;
		
		//prepare all files
		String imgfilename = "image.txt";
		File imgfile = new File (path, imgfilename);
		if (!imgfile.exists()) {
			imgfile.createNewFile();
		}
		String lightfilename = "light.txt";
		File lightfile = new File (path, lightfilename);
		if (!lightfile.exists()) {
			lightfile.createNewFile();
		}
		String crdsfilename = "coords.txt";
		File crdsfile = new File (path, crdsfilename);
		if (!crdsfile.exists()) {
			crdsfile.createNewFile();
		}
		String wrdfilename = "words.txt";
		File wrdfile = new File (path, wrdfilename);
		if (!wrdfile.exists()) {
			wrdfile.createNewFile();
		}

		//write data to all files
		PrintWriter imgout = new PrintWriter(path+"/"+imgfilename);
		imgout.println(image);
		imgout.close();
		PrintWriter lightout = new PrintWriter(path+"/"+lightfilename);
		lightout.println(light);
		lightout.close();
		PrintWriter crdsout = new PrintWriter(path+"/"+crdsfilename);
		crdsout.println(coords);
		crdsout.close();
		PrintWriter wrdout = new PrintWriter(path+"/"+wrdfilename);
		wrdout.println(words);
		wrdout.close();
		
	}

}
