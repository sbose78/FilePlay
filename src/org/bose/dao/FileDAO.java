package org.bose.dao;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bose.dao.*;




public class FileDAO {
	
	

	String file_name;
	long size;
	public long getSize() {
		return size;
	}



	public void setSize(long size) {
		this.size = size;
	}



	public String getFile_name() {
		return file_name;
	}



	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}



	public FileDAO() {
	}



	public InputStream getFile() {
		return file;
	}



	public void setFile(InputStream file) {
		this.file = file;
	}



	public String getFormat() {
		return format;
	}



	public void setFormat(String format) {
		this.format = format;
	}

	InputStream file ;
	String format ;
	String mime;
	

	public FileDAO(String file_name, InputStream file, String format,long size, String mime) {
		this.file_name = file_name;
		this.file = file;
		this.format = format;
		this.size=size;
		this.mime = mime;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public int insertFile(String name , InputStream file_data, String type,long length, String mime){
		try{
	        
	        Connection conn= DbCon.getDbConnection();
	        
	                PreparedStatement pstatement = null;

	                String queryString = "INSERT INTO filestore(name, data, type ,size,mime) VALUES (?,?,?,?,?)";
	                pstatement = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
	                
	                pstatement.setString(1, name);
	                pstatement.setBlob(2, file_data );       
	                pstatement.setString(3, type);  
	                pstatement.setLong(4, length);
	                pstatement.setString(5, mime);
	                
	                int updateQuery = pstatement.executeUpdate();
	                
	                
	                ResultSet my_keys = pstatement.getGeneratedKeys();
	                while(my_keys.next())
	                {
	                	
	                	return my_keys.getInt(1);
	                	
	                }
	                
	                DbCon.closeConnection(conn, pstatement);
	                
	        }
	        catch(Exception e)
	        {
	            System.out.println(e.toString());
	        }
		return -1;
	}
	
public FileDAO getImageStream(int file_id){
    	
    	Connection con;//=DbCon.getDbConnection();
    	Statement stmt;
    	 FileDAO dao = null;
        InputStream is;
        byte[] imgdata=null;
   
      try{
          
              con=DbCon.getDbConnection();

              ResultSet rst=null;
             
              
            

              stmt=con.createStatement();
              rst=stmt.executeQuery("select * from filestore where file_id = "+file_id);
              while(rst.next()){    
                      InputStream b=rst.getBinaryStream("data");
                      String type = rst.getString("type");
                      String name = rst.getString("name");
                      long size = rst.getLong("size");
                      String mime = rst.getString("mime");
                      dao = new FileDAO(name, b, type,size,mime);
                      System.out.println(name+"retirved");
                      break;
              }
              DbCon.closeConnection(con);
           
      }
      
      
      
      catch(Exception e)
      {
          System.out.println(e.toString());
      }
      
      return dao;
      
    	
    }



public String getMime() {
	return mime;
}



public void setMime(String mime) {
	this.mime = mime;
}

}
