<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">   
<html>


<head>
	<title>Update Student</title>
	
</head>

<style>
<!-- This must be include to used css>
<%@ include file="/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"%>
</style>

<body>

<% Integer admin = session.getAttribute("admin_id") == null ? 1 : (Integer) session.getAttribute("admin_id"); %>

	
	
		<div class="container text-center">
		  <div class="jumbotron">
		    <h1><strong>Updating new product</strong></h1> 
		  </div>
		</div>
	
	
		<div class = "container">
			<form action="productControllerServlet?_Seller=<%=admin %>" method="POST">
				<input type ="hidden" name ="command" value ="UPDATE"/>
				<input type ="hidden" name ="productId" value ="${THE_PRODUCT.id}"/>
				 <table border="0" width="45%" align="center">

				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Sku</label>
				    <input type="text" class="form-control"  name="_Sku"  placeholder="Enter Products'Sku"  name="_Sku" value = "${THE_PRODUCT.sku}">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Name</label>
				    <input type="text" class="form-control" name="_Name" " placeholder="Enter Products'name" name="_Name" value = "${THE_PRODUCT.name}">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Price</label>
				    <input type="number" class="form-control" name="_Price"  placeholder="Enter Products'price" name="_Price" value = "${THE_PRODUCT.price}">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Deliever Fee</label>
				    <input type="number" class="form-control" name="_DelieverFee"  placeholder="Enter Products' deliever fee" name="_DelieverFee" value = "${THE_PRODUCT.delieverFee}">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
				  <div class="form-group">
				    <label for="exampleInputEmail1">Description</label>
				    <input type="text" class="form-control" name="_Descript"  placeholder="Enter Products' description" name="_Descript" value = "${THE_PRODUCT.descript}">
				    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				  </div>
				  
		

		
				  			
		  		<button type="submit" class="btn btn-primary save" value="Save">Save</button>
		  		
			</form>
		</div>
		
		
		 <div class="container text-center">

			<a class="btn btn-secondary btn-lg btn-block" type="button" href="productControllerServlet">Back</a>

		</div>
	
	


</body>
</html>