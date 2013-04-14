package org.bose.applet;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;


public class FileDataApplet extends JApplet {
    //Called when this applet is loaded into the browser.
	
	JLabel lbl=  lbl = new JLabel("No file selected");
	 public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
    	
    	
    	
        try {
        	
        	
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                	JPanel container = new JPanel(true);
                	
                	
                	
                	
                	JButton button=new JButton("Select file from local system");
                	
                	
                	lbl.setVisible(true);
                    button.setVisible(true);
                    
                    
                    
                    button.addActionListener(new ActionListener() {
                    	  public void actionPerformed(ActionEvent evt) {
                    	    // ... called when button clicked
                    		  
                    		  
                    	      //Create a file chooser
                              
                              
                              final JFileChooser fc = new JFileChooser();
                              
                              //In response to a button click:
                              int returnVal = fc.showOpenDialog(null);
                              String filepath="";
                              if (returnVal == JFileChooser.APPROVE_OPTION) {
                                  File file = fc.getSelectedFile();
                                  //This is where a real application would open the file.
                                  
                                  filepath=file.getAbsolutePath();
                                
                              } else {
                                  filepath = "NOt found";
                              }
                        
                              update(lbl,filepath );
                             // lbl = new JLabel("Path "+filepath);
                             // lbl.setText(filepath);
                              repaint();
                    	  }
                    });
                   

                   
                   
                    container.add(button);
                    container.add(lbl);
                	container.setVisible(true);
                	
                	add(container);
                    repaint();
                    
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't complete successfully");
            e.printStackTrace();
            
            
        }
     
    	
    }
	 
	public void update(JLabel label, String text){
		label.setText("You selected : "+text);
	}

}