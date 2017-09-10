package com.products;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
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
import javax.sql.DataSource;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.ConnectionManager;

/**
 * Servlet implementation class productControllerServlet
 */
@WebServlet("/displayServlet")
@MultipartConfig
public class displayServlet extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "C:/Users/Jiaqihuang/Desktop/img";
	private static final long serialVersionUID = 1L; 
	private ProductDbUtil productDbUtil;
		
	private Connection dataSource;
	
	
	public displayServlet(){
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


	private void listProducts(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
			//get products from the db util
			List<product> products = productDbUtil.getProducts();
			
			//add product to the request
			request.setAttribute("PRODUCT_LIST", products);
			
			//send to the JSP page (view)
			request.getRequestDispatcher("/home.jsp").forward(request, response);

	}

}
