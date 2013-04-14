<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang = "en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My file game</title>
</head>

<style>

body{
    
    margin-left:10%;
    margin-right:10%;

}

.link_div{
	
	margin:5px;

}

.bar {
    height: 18px;
    background: green;
   
}

#progress{

	margin-top: 50px;
	padding: 10px;
	border: solid 1px black;
	
}

.desc{
	
	font-size: large;
	

}

#path{

	margin-left:20%;
	padding: 7%;
}

#upload{
    padding: 2%;
	display:block;
	margin-left:20%;
	margin-right:20%;
   /* border: solid 2px black; */
			
}

.fileuploadzone{
/*	visibility: hidden;  */
    background-color:#0054BB;   
    width: 300px;
    height:1300px;
    padding: 15px;
    
}

#upload_status{

	margin: 20px;

}

#developer_text{
	margin-top: 1000px;
	height: 1000px;
	margin-left: 20%;
	margin-right: 20%;

}


header#horizontal_panel{

	background: rgb(246,248,249); /* Old browsers */
background: -moz-linear-gradient(top, rgba(246,248,249,1) 0%, rgba(229,235,238,1) 50%, rgba(215,222,227,1) 51%, rgba(245,247,249,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(246,248,249,1)), color-stop(50%,rgba(229,235,238,1)), color-stop(51%,rgba(215,222,227,1)), color-stop(100%,rgba(245,247,249,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top, rgba(246,248,249,1) 0%,rgba(229,235,238,1) 50%,rgba(215,222,227,1) 51%,rgba(245,247,249,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top, rgba(246,248,249,1) 0%,rgba(229,235,238,1) 50%,rgba(215,222,227,1) 51%,rgba(245,247,249,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top, rgba(246,248,249,1) 0%,rgba(229,235,238,1) 50%,rgba(215,222,227,1) 51%,rgba(245,247,249,1) 100%); /* IE10+ */
background: linear-gradient(to bottom, rgba(246,248,249,1) 0%,rgba(229,235,238,1) 50%,rgba(215,222,227,1) 51%,rgba(245,247,249,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#f6f8f9', endColorstr='#f5f7f9',GradientType=0 ); /* IE6-9 */
	

	position: fixed;
	z-index: 10;
	width: 100%;

}

header#horizontal_panel ul li{
	display: inline;
	

}
header#horizontal_panel ul li a{
	text-decoration: none;
	padding: 6px;
	border: solid 1px #6DBDE2;
	background-color: white;
	color: black;
	border-radius: 5px;
	margin: 1px;

}


</style>


	<header id="horizontal_panel">
		<ul>
			<li>
				<a href="#">HOME</a>
			<li>
			
			<li>
				<a href="#developer"> Developer </a>
			</li>

		</ul>

	</header><BR><br>

<body>
    <!-- ... -->
    <div id="path">
	    
	    <script src="<%=request.getContextPath()%>/js/deployJava.js"></script>
	    <script> 
	        var attributes = {
	            code:'org.bose.applet.FileDataApplet',  width:600, height:100} ; 
	        var parameters = {jnlp_href: 'myapp.jnlp'} ; 
	        deployJava.runApplet(attributes, parameters, '1.6'); 
	    </script>
	    <!-- ... -->
    </div>
    <br>
    
    <hr></hr>
    
    
    
    <div  id="upload">
    
    <label class="desc">
    	Drag 'n drop any file from your desktop below and we'll secure it for you in our server.
    </label>
    
    <br><br><br><br>
    
    
		<label class="fileuploadzone"><input id="fileupload" type="file" name="files[]" data-url="<%=request.getContextPath()%>/CatchFile" multiple>
		</label> 
		<!--  script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>   -->
		<script src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
		
		<script src="<%=request.getContextPath()%>/js/vendor/jquery.ui.widget.js"></script>
		
		<script src="<%=request.getContextPath()%>/js/jquery.iframe-transport.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.fileupload.js"></script>
		<script>
		$(function () {
		    $('#fileupload').fileupload({
		    	
		        dataType: 'json',
		        done: function (e, data) {
		        	 $.each(data.result.files, function (index, file) {
		        		 var div = document.createElement("div");
		        		 
		        		 var link = document.createElement("a");
		        		 
		        		 $(div).attr("class","link_div");		        		 
		        		 $(link).attr("href","GetFile?file_id="+file.id);
		        		 
		        		 $(link).text(file.name);
		        		 
		        		// alert("Im here");
		        		 
		        		 $(div).html(link);
		        		 
		        		 //$(div).html(file.name);
		                 $('#upload').append(div);
		                 $("#upload_status").html("Uploaded");
		                 $(".fileuploadzone").css(
		     		        	'visibility','visible'	
		     		        );
		                 
		             });
		        	 /*
		        	var div = document.createElement("div")      	;
		        	$(div).html(data.result.files[0].name);
		        	$('#upload').append(div);
		        	*/
		        	/*
		            $.each(data.result.files, function (index, file) {
		                $('<p/>').text(file.name).appendTo(document.body);
		            });
		        	*/
		        },
		    
		    progressall: function (e, data) {
		        var progress = parseInt(data.loaded / data.total * 100, 10);
		        for(  var i=0;i<10000;i++){
		        	for( var j=0;j<10000;j++){}
		        	
		        }
		        $('#progress .bar').css(
		            'width',
		            progress + '%'
		        );
		        
		    },
		    
		    start: function (e, data) {
		    //	alert("submit");
		        var input = $('#upload_status');
		        $(input).html("UPLOADING....");
		        $(".fileuploadzone").css(
		        	'visibility','hidden'	
		        );
		    }
		    
		    
		    });
		});
		</script>
    
    
    <!--
    
    	 <form method="post" action="<%=request.getContextPath()%>/CatchFile" name="form1" id="form1"  enctype="multipart/form-data">
    	 
    	 	<input type="file" name="my_file"><input type="submit">	 
       	    	 
    	 
    	 </form>
-->
		
    

			<div id="progress" width="50%" >
			   			 <div class="bar" style="width: 0%;"></div>
			</div>
    
			    <div id="upload_status" align="center">
			    </div> 
  </div>
    
    
 <div id="developer_text">
			<a id="developer"></a><br><br><br>

			<h2> Developer </h2>

			<p>

				<strong> Author :  </strong> &nbsp; Shoubhik Bose ( sbose78@gmail.com ) <br><br>



				<strong> The objective :  </strong><br>

				<ul>
					<li>
					 A user can select a file from his local computer and 
					 it will pull the file's ABSOLUTE PATH 
					 (for example: /Users/David/Desktop/script/files.sh).
					</li>


					<li>
						A user can drag and drop a file (under 5MB) and 
						it will upload the file and allow the user to download it from a link. 
						(There should be no page refresh on this).
					</li>
				</ul>

				<br>

				<strong> The code </strong>: &nbsp;
				<a href="https://github.com/sbose78/my_vimeo_scrape">View on Github</a>

				<br><br>


				<strong> The architecture </strong><br>

				<ul>
					<li>
						Web-framework :&nbsp; Servlets & JSP , JEE 
					</li>
					<li>
						Database server:&nbsp; MySQL 5.5 on Amazon RDS
					</li>
					<li>
						Application server:&nbsp; 64bit Amazon Linux running Tomcat 6 &nbsp;
						 <a href="http://aws.amazon.com/elasticbeanstalk/">Amazon Web Services Beanstalk</a>
					</li>

					<li>
						Front-end design & dev : &nbsp; HTML5 + CSS + Javascript + JQuery1.8
					</li>

				</ul>

<br>
				<strong> How to test </strong><br>

				<ul>
				
					<li>
						The 1st half of the homepage has an applet which asks the user to choose a file
						and the applet in turn displays the absolute path of the file.
						<br> <strong>What's so special about this?</strong> 
						Usually the absolute path of a file in the client's local system   
						is not available to the browser - this can be worked around using
						applets. 
					</li>
				
					<li>
						Drag and Drop any file < 5MB in the area marked for Drag n Drop file upload.
						The green bar below will signify progress of the upload.
					</li>
						
					<li>
						After upload, a download link will be generated.
					</li>
				</ul>

			</p>
		</div>
    
</body>

</body>
</html>