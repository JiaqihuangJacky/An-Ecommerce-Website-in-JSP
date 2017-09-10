<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding new item</title>

</head>
<style>
<!-- This must be include to used css>

<%@ include file="/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"%>
</style>
<body>

<% Integer admin = session.getAttribute("admin_id") == null ? 1 : (Integer) session.getAttribute("admin_id"); %>


<!-- 	id,sku,pic,name,pict,price,delieverFee,descript,seller,categ,postDate <form action="adding" method="post">-->
	<div class="container text-center">
		  <div class="jumbotron">
		    <h1><strong>Adding new product</strong></h1> 
		  </div>
	</div>
	
	
	<div class = "container">
			<form action="productControllerServlet?_Seller=<%=admin %>" method="Post" enctype="multipart/form-data">
				<input type ="hidden" name ="command" value ="ADD"/>
				 <table border="0" width="45%" align="center">
				 

				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Sku</label>
				    <input type="text" class="form-control"  name="_Sku"  placeholder="Enter Products'Sku"  name="_Sku">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Name</label>
				    <input type="text" class="form-control" name="_Name" " placeholder="Enter Products'name" name="_Name">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Price</label>
				    <input type="number" class="form-control" name="_Price"  placeholder="Enter Products'price" name="_Price">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Deliever Fee</label>
				    <input type="number" class="form-control" name="_DelieverFee"  placeholder="Enter Products' deliever fee" name="_DelieverFee">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Description</label>
				    <input type="text" class="form-control" name="_Descript"  placeholder="Enter Products' description" name="_Descript">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  

				  
		
				<input type="file" name="file" placeholder="subpicture"/><br/>				
		
		
				  			
		  		<button type="submit" class="btn btn-primary save" value="Save">Save</button>
		  		
		</form>
		

		
	</div>
	
			    
    
   <div class="container text-center">

		<a class="btn btn-secondary btn-lg btn-block" type="button" href="productControllerServlet">Back</a>

	</div>
	
	
		
    
</body>
</html>