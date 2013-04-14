package org.bose.controller;
import org.apache.commons.io.IOUtils;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bose.dao.FileDAO;

/**
 * Servlet implementation class GetFile
 */
public class GetFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			
		
     //  PrintWriter out = response.getWriter();
       OutputStream o = response.getOutputStream();
       
		
		int file_id = Integer.parseInt(request.getParameter("file_id"));
		FileDAO dao = new FileDAO();
		FileDAO file= dao.getImageStream(file_id);
		
		
		response.setContentLength((int)file.getSize());
		
		response.setContentType("application/octet-stream");//file.getMime());
		
		
	
		        //response.setContentLength(-1);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Disposition","attachment; filename=\"" + file.getFile_name()+ "\"");//fileName);
		
		/*
		BufferedInputStream bin = new BufferedInputStream(my_file);
		BufferedReader br = new BufferedReader(new InputStreamReader(bin));
		
		String line="";
		while( (line=br.readLine())!=null){
			out.write(line);
		}
		*/
			
		/*
				InputStream my_file= file.getFile();
				

					BufferedInputStream bin = new BufferedInputStream(my_file);
					DataInputStream din = new DataInputStream(bin);
					
					while(din.available() > 0){
					    out.print(din.readLine());
					    out.print("\n");
					}
				
				InputStreamReader is = new InputStreamReader(my_file);
			*/
		
		

		byte b[]= IOUtils.toByteArray(file.getFile());
		
		
		o.write(b);
		o.flush();
		o.close();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
