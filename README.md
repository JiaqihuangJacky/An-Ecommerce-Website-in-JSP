# An Ecommerce Website in JSP:													    
•	A dynamically generated web page to manage large, complex design projects for clients by using JSP.
•	Collaborate with designers to integrate a bootstrap framework to link a backend database, MySQL to register items for store, and connect them to a messaging system. 
•	Complete detailed programing and development tasks for challenging back-end server code, such as PayPal-checkout.

# All the codes are in the bulldog file	

07/10/2017
1)Update: adding fileupload functon
xml changed
create new Uploadservlet
putting two common jar into the libary
create message.jsp upload.jsp
download can be found from http://www.codejava.net/download-attachment?fid=153
file come from: http://www.codejava.net/java-ee/servlet/eclipse-file-upload-servlet-with-apache-common-file-upload

Adding the UI design from the website 
css html file are under the webContent

Adding login in view and function
http://www.javaknowledge.info/login-and-registration-example-in-jsp-with-session/

---------------------------------------------------------------------------------------------------------------------------
07/11/2017
Adding paypal demo under demo dir
Paypal account has been set up
You need to install springboot before you run your program
Since we are doing the develop paypal, we can create and manipulate the account money we want
Example: we set up 9999 in a business account, after we pay(fix amount such as 999).
         Then, if we login into out account, then we can pay 999 to the second account
         then the money will be 9000.
 Related link of tutorial: http://blog.csdn.net/change_on/article/details/73881791
 
---------------------------------------------------------------------------------------------------------------------------
07/12/2017 17:17
adding new item function has been update
Creating a new form
Making a new categries before inserting new product
There are 4 types error I have fixed, such as 
id, seller, and price, they need to be cast to int / double
before inserting into database

---------------------------------------------------------------------------------------------------------------------------
07/13/2017 17:17
adding
deleting
updating
function has been created in productController.java
You can modifiy it as a management system
Updating your library to use c 

---------------------------------------------------------------------------------------------------------------------------
07/14/2017 17:17
Adding the complete management for products: searching, deleteing, adding, and updateing
Fixing the bug: such as css
Updating all the jsp file
Deleting all the unnessary files

---------------------------------------------------------------------------------------------------------------------------
07/18/2017 17:17
Adding the data into the product, and updating all the relative function


---------------------------------------------------------------------------------------------------------------------------
07/24/2017 17:17
Adding image class, and image sql function, and updating all the UI of back End.
Updating the product sql, updating the form the adding new item.
We need to add boostrap in the lib before start the integrating the project

---------------------------------------------------------------------------------------------------------------------------
07/29/2017 17:17
Modifies: change the connection to database using connectionManagement, fixed error of
try catch block
adding function such as: image uploading, imaging processing, displaying images in backend,
displaying items in home page
Need fixed: delete function, uploading more than 1 picture/ images

running productControllerServlet.java gives you the back management of products(search,delete,list,update)
running displayServlet.java gives you the home page and displaying all the products with price,name

-needed files:
	all files in com.products.
	all files in com.server.
	all files in Webcontent (copy past them, especially all files in css, js)
	the(addingitem.jsp, admin.jsp, home.jsp, list-items.jsp[houtai desgin], updating-product-form.jsp are needed)
	change your database link path, and uploading path as needed.
