package org.bose.controller;

import java.io.IOException;


import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bose.dao.FileDAO;
import org.json.simple.*;



/**
 * Servlet implementation class CatchFile
 */
public class CatchFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatchFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
					 System.out.println("--------------IN SERVLET-----");
				        
			         PrintWriter out = response.getWriter();
			         JSONObject jsono= new JSONObject();
			         JSONArray json = new JSONArray();
			         JSONObject outer = new JSONObject();
			        
			    try {
			        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			        
			        String fileName="";
			        String fileType="";
			        String fileMime ="";
			        String fieldname;
			        String fieldvalue=""; ;
			        InputStream filecontent=null ;  
			        
			        
			        // assumption was that there were just 2 fields.
			        // now there are 3. Added the file_description too in the form of file_name
			        
			        for (FileItem item : items) {
			            if (item.isFormField()) {
			                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
			                fieldname = item.getFieldName();
			                fieldvalue = item.getString();
			                
			                                
			                System.out.println(fieldname+"....formfield...."+fieldvalue);
			                // ... (do your job here)
			            } else {
			                // Process form file field (input type="file").
			               //ouur String fieldname = item.getFieldName();
			                fileName = (item.getName());
			                
			              
			                fileType= getFileType(fileName);
			                System.out.println("....non-form-field...."+fileName);
			                filecontent = item.getInputStream();
			                 FileDAO dao = new FileDAO();
			                int id=  dao.insertFile(fileName, filecontent, fileType,item.getSize(), item.getContentType());
			                 
			                 
			                
					          jsono.put("name", item.getName());
		                      jsono.put("size", item.getSize());
		                      jsono.put("url", "upload?getfile=" + item.getName());
		                      jsono.put("thumbnail_url", "upload?getthumb=" + id);
		                      jsono.put("delete_url", "upload?delfile=" + item.getName());
		                      jsono.put("delete_type", "GET");
		                      jsono.put("id",id);
		                      json.add(jsono);
		                      outer.put("files", json)            ;
		                      
		                      
		                      
					          
			              
			                // ... (do your job here)
			            }
			        }
			        
					response.setContentType("application/json");
			
			          
			          
			          //response.setStatus(200);
					System.out.println(outer);
			          out.print(outer);
			          
			          out.flush();
					
				}
			    catch(Exception e){
			    	e.printStackTrace();
			    }
    	
    }
	
	public String getFileType(String fileName){
		String extension="";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}

}
