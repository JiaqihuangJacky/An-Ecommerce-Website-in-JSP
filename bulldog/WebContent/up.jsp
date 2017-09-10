<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload page</title>
</head>

<style>
<!-- This must be include to used css>

<%@ include file="/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"%>
</style>

<body>
<% Integer admin = session.getAttribute("admin_id") == null ? -1 : (Integer) session.getAttribute("admin_id"); %>

<center>
<form action="productControllerServlet"  method="post"  enctype="multipart/form-data" >
	<input type ="hidden" name ="command" value ="IMAGE"/>
	<input type="file" name="file" placeholder="subpicture" multiple/><br/>
	<button type="submit" class="btn btn-primary save" value="Save">Save</button>
	
</form>
</center>





</body>

</html>








<!--ajax for future use  -->
<!-- <form>
<div class="centered">
 <h2 style="text-align:center;">sub pictures</h2>
 <input type="file" name="cover_file"/><br/>
 <div id="upload" style="display:none;">Uploading..</div>
</div>
</form> -->
	


<script src="/bulldog/js/jquery-1.8.2.js"></script>
<script src="/bulldog/js/jquery.ajaxfileupload.js"></script>
<script language="Javascript">
/* $(document).ready(function(){	 
	 $('#ipt').ajaxfileupload({
	      'action': 'upload',	      	    
	  'onComplete': function(response) {	        
	        $('#upload').hide();
	        alert("File SAVED!!");
	      },
	      'onStart': function() {
	        $('#upload').show(); 
	      }
	 });
});  */
</script>