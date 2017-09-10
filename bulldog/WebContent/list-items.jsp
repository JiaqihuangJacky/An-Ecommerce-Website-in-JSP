<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Products Tracker App</title>
</head>


<%
String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
		    <h1><strong>Product Management System</strong></h1> 
		  </div>
		</div>
	
	
	<div class="container">

		
		
			<!--  add a search box -->
			<form action="productControllerServlet" method="GET">
		

            <!--  adding search bar and function button to search fromthe sql -->
                <div class="col-lg-6">
	                <div class="input-group">
	                	  <input type="hidden" name="command" value="SEARCH" />
					      <input type="text" class="form-control" placeholder="Search for..." name="theSearchName">
					      <span class="input-group-btn">
					        <button class="btn btn-secondary" type="submit" value="Search">Go!</button>
					      </span>
				    </div>      
                  </div>    
                            

				<!-- put new button: Adding tempProduct -->
            	<button type="button" class="btn btn-outline-secondary" type="button" value="Add new Item"
						onclick="window.location.href='addingitem.jsp';return false;">Add new item</button>
						
				<!--  go back to main -->
				<button type="button" class="btn btn-outline-secondary" type="button" value="back_main"
						onclick="window.location.href='displayServlet';return false;">Back to home</button>
				
            </form>
				
	</div>
		
		
	</br>
		
	<div class="container">		
		<table class="table">
		  <thead class="thead-inverse">
		   <tr>
					<th>ID</th>
					<th>Sku</th>
					<th>name</th>
					<th>Price</th>
					<th>deliever Fee</th>
					<th>descript</th>
					<th>Date</th>
					<th>Image</th>
					<th>Modify</th>
					
				</tr>
		  </thead>
		 <c:forEach var="tempProduct" items="${PRODUCT_LIST}">
					
					<!-- seeting up a link for each tempProduct -->
					<c:url var = "tempLink" value="productControllerServlet">
						<c:param name="command" value="LOAD"/>
						<c:param name="productId" value = "${tempProduct.id}"/>
					</c:url>
					
					
						<!-- seeting up a link delete tempProduct -->
					<c:url var = "deletelink" value="productControllerServlet">
						<c:param name="command" value="DELETE"/>
						<c:param name="productId" value = "${tempProduct.id}"/>
					</c:url>
					
		<!-- id,sku,pic,name,pict,price,delieverFee,descript,seller,categ,postDate  -->				
					<tr>
						<td>${tempProduct.getId()}</td>
						<td>${tempProduct.getSku()}</td>			
						<td>${tempProduct.getName()}</td>
						<td>${tempProduct.getPrice()}</td>
						<td>${tempProduct.getDelieverFee()}</td>
						<td>${tempProduct.getDescript()}</td>
						<td>${tempProduct.getDate()}</td>

				
						<!-- adding c:url to display the image it is important  -->
							<td><img src="<c:url value="${tempProduct.getImageurl()}"/>" width="250" height="180" border="1"/></td>
						<td>
						
							<a href = "${tempLink}">Update</a>
							|
							<a href = "${deletelink}"
							onclick="if(!(confirm('Are you sure you want to delete? '))) return false">
							Delete</a>
						</td>
					</tr>
					
				</c:forEach>
		</table>
		
		
		
	</div>	
		
		
		
		
		
		
		
		
</body>
</html>
