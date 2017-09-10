<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.products.ProductDbUtil"%>
<%@ page import="java.util.List" %>
<%@ page import="com.products.product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>American daigou</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>

<%@ include file="/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"%>


.thead-inverse th {
            color: #fff;
            background-color: #373a3c;
        }




</style>
<body>


	
	
		<div class="container text-center">
		  <div class="jumbotron">
		    <h1><strong>Daigou Diplays item demo</strong></h1> 
		  </div>
		</div>





		
	<div class="container">		
		<table class="table">
		  <thead class="thead-inverse">
		   
		   
		  </thead>
		 <c:forEach var="tempProduct" items="${PRODUCT_LIST}">
					

					
					 <div>
					 
					 
		             <dl>
		             
		             	<dt><!-- adding c:url to display the image it is important  -->
							<img src="<c:url value="${tempProduct.getImageurl()}"/>" width="250" height="180" border="1"/></td>
						
						</dt>
		             
		               <dt>Name:
		                 ${tempProduct.getName()}
		               </dt>
		 
		               <dd class="dd_city">price: ${tempProduct.getPrice()}</dd> 
		             </dl>
		          </div>
					
				</c:forEach>
		</table>
		
		
		
	</div>	



	
	
	
	
	
	
	
	
	
	

 <!-- product frid end -->
 <%//} %>   

		

    
  </div>
		
		
		

	
</body>

</html>