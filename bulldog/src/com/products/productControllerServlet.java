package com.products;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.sql.*;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.*;
/**
 * Servlet implementation class productControllerServlet
 */
@WebServlet("/productControllerServlet")
@MultipartConfig
public class productControllerServlet extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "C:/Users/Jiaqihuang/Desktop/bdproj/bulldog/bulldog/WebContent/imagesproduct/";
	private final String image_DIRECTORY = "imagesproduct/";
	private static final long serialVersionUID = 1L; 
	private ProductDbUtil productDbUtil;

	
	private Connection dataSource;
	
	
	public productControllerServlet(){
		super();
		dataSource = ConnectionManager.getConnection();
		productDbUtil = new ProductDbUtil(dataSource);
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			
			//route to the appropriate method
			if(theCommand ==null){
				theCommand = "LIST";
			}
			
			switch(theCommand){
			
			case "LIST":
				listProducts(request, response);
				break;
	
			case "ADD":
				addProducts(request, response);
				break;
				
			case "LOAD":
				loadProduct(request, response);
				break;
				
			case "UPDATE":
				updateProduct(request, response);
				break;
				
			case "DELETE":
				deleteProduct(request, response);
				break;
				
				
			case "SEARCH":
                searchProducts(request, response);
                break;

			
			default:
				listProducts(request,response);
			
			}
			
			
			
			//list the products .. in MVC fashion
			//listProducts(request,response);
		}
		catch(Exception exc){
			System.out.println(exc.getMessage());
			throw new ServletException(exc);
		}
		
	}
	



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			doGet(request,response);
		
	}


	



	private void searchProducts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		 // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<product> products = productDbUtil.searchProducts(theSearchName);
        
		//add product to the request
		request.setAttribute("PRODUCT_LIST", products);
		
		//send to the JSP page (view)
		request.getRequestDispatcher("/list-items.jsp").forward(request, response);

		
	}



	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//read id from the databse
		String theProductId = request.getParameter("productId");
		
		//delete product from the database
		productDbUtil.deleteProduct(theProductId);
		
		
		//send then to server
		listProducts(request,response);
	}



	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

			//read theProductId info from the data
			int id = Integer.parseInt(request.getParameter("productId"));
			String sku = request.getParameter("_Sku").toString();	
			String name = request.getParameter("_Name").toString();		
			double price =  Double.parseDouble(request.getParameter("_Price"));	
			double DelieverFee = Double.parseDouble(request.getParameter("_DelieverFee"));	
			String Descript = request.getParameter("_Descript").toString();			
			int Seller = Integer.parseInt( request.getParameter("_Seller").toString());	

			
			//create a new theProductId object
			//int id, String sku, String name, double price,double delieverFee, String descript, int selle
			product theProduct = new product(id,sku,name,price,DelieverFee,Descript,Seller);
			
			//perform update on database
			productDbUtil.updateProduct(theProduct);
		
			//send them back to the list theProductId page
			listProducts(request,response);
		
	}



	private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		//reading theProductId id
		String theProductId = request.getParameter("productId");
		
		//get productId from databse (db util)
		product theProduct = productDbUtil.getProduct(theProductId);
		
		//place product in the request attribute
		request.setAttribute("THE_PRODUCT", theProduct);
		
		//send to jsp page: update-product-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-product-form.jsp");
		dispatcher.forward(request, response);
	}



	private void addProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//id,sku,pic,name,pict,price,delieverFee,descript,seller,categ,postDate
				int id = 0;
				String sku = request.getParameter("_Sku").toString();	
				String name = request.getParameter("_Name").toString();		
				double price =  Double.parseDouble(request.getParameter("_Price"));	
				double DelieverFee = Double.parseDouble(request.getParameter("_DelieverFee"));	
				String Descript = request.getParameter("_Descript").toString();			
				int Seller =Integer.parseInt( request.getParameter("_Seller").toString());	

				//create a new product object
				product theProduct = new product(sku,name,price,DelieverFee,Descript,Seller);
				
				//image object
				images theimage = null;
				
				//images list for mutiple picture
				List<images> image_list;
				
				//image url link
				String final_url = "";
				//adding image
				if(ServletFileUpload.isMultipartContent(request))
				{
			    	
					try
					{	
						
						//System.out.println(request.getParameter("user"));
						List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
						File uploads = new File(UPLOAD_DIRECTORY);
						
					    for (Part filePart : fileParts) {
					        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
					        File file = new File(uploads, fileName);

					        InputStream fileContent = filePart.getInputStream();
					        if(file.exists()){
					        	File newFile = new File(uploads,"1"+fileName);
					        	file.renameTo(newFile);
					        }
					 
					        Files.copy(fileContent, file.toPath());
					        

					        //getting all the url
					        final_url += image_DIRECTORY + fileName;
					        
				
					    }
					    
					   
				        																
						request.setAttribute("message", "File uploaded successfully.");
					}
					catch(Exception ex)
					{
			
						System.out.println(ex.getMessage());
						request.setAttribute("message", "File upload failed due to : " + ex);
					}
				}
				else
				{
					System.out.println("failing!201");
					request.setAttribute("message", "Sorry this servlet only handles file upload request.");
				}
				
				
				//System.out.println(final_url);
				//adding the images
		        theimage= new images(final_url,id);
				
				//add the product to the database
				productDbUtil.addProduct(theProduct,theimage);
						
				//send back to main page(the product list)
				listProducts(request,response);
	}



	private void listProducts(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
			//get products from the db util
			
			List<product> products = productDbUtil.getProducts();
			
			//add product to the request
			request.setAttribute("PRODUCT_LIST", products);
			
			//send to the JSP page (view)
			request.getRequestDispatcher("/list-items.jsp").forward(request, response);

	}

}
