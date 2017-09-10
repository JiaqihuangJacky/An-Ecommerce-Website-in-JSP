package com.products;
import com.connection.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.sql.DataSource;




public class ProductDbUtil {
//	String sku = myRs.getString("sku");	
//	String name = myRs.getString("name");		
//	double price = myRs.getDouble("price");	
//	double DelieverFee = myRs.getDouble("delieverfee");	
//	String Descript = myRs.getString("descript");			
//	int Seller = myRs.getInt("seller");	
//	String Date = myRs.getString("postdate");
	private static final String sql = "select p.id as id,p.sku as sku, p.name as name, "+
			"p.price as price, p.delieverfee as delieverfee,p.descript as descript, "+
			"p.seller as seller, p.postdate as postdate, im.pic as pic from "+
			"products p, images im where im.prod = p.id ";

	private Connection dataSource;

	
	public ProductDbUtil(Connection c){
		this.dataSource = c;
	}



	public List<product> getProducts() throws Exception{
		
		List<product> productList = new ArrayList<>();
		Statement myStmt = null;
		ResultSet myRs = null;
		

		String imageurl = null;
		try{
			//get a connection
			//create sql statement
			
			//execute query
			myStmt = dataSource.createStatement();
			//process result set
			myRs = myStmt.executeQuery(sql);
	
			//chose JDBC objects

			while(myRs.next()){
				
				//read products info from the data
				//id,sku,pic,name,pict,price,delieverFee,descript,seller,categ,postDate
				int id = myRs.getInt("id");
	
				
				String sku = myRs.getString("sku");	
				String name = myRs.getString("name");		
				double price = myRs.getDouble("price");	
				double DelieverFee = myRs.getDouble("delieverfee");	
				String Descript = myRs.getString("descript");			
				int Seller = myRs.getInt("seller");	
				String Date = myRs.getString("postdate");
				imageurl = myRs.getString("pic");
				//create new product object
				product theProduct = new product(id,sku,name,price,DelieverFee,Descript,Seller,Date,imageurl);
				
				//add it to the list of products
				productList.add(theProduct);
			}
			
		}catch(Exception exc){
				
			exc.printStackTrace();
		}
		
		finally{
			//close JDBC objects
			try{
				myStmt.close();
				myRs.close();
			}catch(Exception ex){

				System.out.println(ex.getMessage());

			}
		}
		return productList;
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try{
			if(myRs != null){
				myRs.close();
			}
			
			if(myStmt != null){
				myStmt.close();
			}
			
			if(myConn != null){
				//not really close it
				//just puts back in connection pool
				myConn.close();
			}
			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
			
	}

	public void addProduct(product theProduct,images theImages)throws ServletException, IOException  {
		
		PreparedStatement myStmt = null;
		ResultSet rs = null;
		int prod = 0;
		int  risultato = 0;
	
		try{
			
			//get db connection
			dataSource.setAutoCommit(false);
			//create sql for insert product
			String sql = "INSERT INTO products(sku,name,price,DelieverFee,Descript,Seller) "+
					" VALUES(?,?,?,?,?,?) returning ID";
			
			
			//set the param values for product
			myStmt = dataSource.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
			//execute sql insert
			myStmt.setString(1,theProduct.getSku());
			myStmt.setString(2, theProduct.getName());
			myStmt.setDouble(3, theProduct.getPrice());
			myStmt.setDouble(4,theProduct.getDelieverFee());
			myStmt.setString(5, theProduct.getDescript());
			myStmt.setInt(6, theProduct.getSeller());
	
			//get resultsert
			prod  = myStmt.executeUpdate();
			dataSource.commit();
			//result set
			rs = myStmt.getGeneratedKeys();
			
			//get id
			if (rs.next()){
			    risultato = rs.getInt(1);
			}
			else{
				risultato = 0;
			}
			
			//create sql for insert
			sql = "INSERT INTO images(pic,prod)"+
					" VALUES(?,?) ";
			
			
			//System.out.println(theImages.getPicurl());
	
			//set the param values for product
			myStmt = dataSource.prepareStatement(sql);
			
			//execute sql insert
			//myStmt.setString(1, theImages.getPicurl());
			//myStmt.setInt(2,theImages.getProd());
		
			//execute sql insert
			myStmt.setString(1, theImages.getPicurl());
			myStmt.setInt(2, risultato);
			
			//execute sql insert
			myStmt.executeUpdate();
			dataSource.commit();
			
	
		}
		catch(Exception ex){
			System.out.println("Error" + "	"+  risultato);
			System.out.println(ex.getMessage());
		}
		finally{
			try{
				dataSource.setAutoCommit(true);
				if(myStmt != null)	myStmt.close();
				if(rs != null) rs.close();
			}catch(Exception ex){
				System.out.println("Error" + "	"+  risultato);
				System.out.println(ex.getMessage());
			}
			
			
		}
		
	}

	public product getProduct(String theProductId) throws Exception {
		

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int productId;
		product theProduct = null;
		
		try{
			
			//convert product id to int
			productId = Integer.parseInt(theProductId);
			
			//get connection to databse
			
			//create sql to get selected product
			String sql = "select * from products where id=?";
			
			//create preapared product
			myStmt = dataSource.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1,productId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next()){
				//read products info from the data
				int id = myRs.getInt("id");
				String sku = myRs.getString("sku");	
				String name = myRs.getString("name");		
				double price = myRs.getDouble("price");	
				double DelieverFee = myRs.getDouble("delieverFee");	
				String Descript = myRs.getString("descript");			
				int Seller = myRs.getInt("seller");	
				String Date = myRs.getString("postdate");
				//create a new product object
				theProduct = new product(id,sku,name,price,DelieverFee,Descript,Seller,Date);
				
			}
			else{
				throw new Exception("Could not find product id:	" + productId);
			}
			
		}catch(Exception exc){
			
			exc.printStackTrace();
		}
		
		
		finally{
			//close JDBC objects
			try{
				if(myStmt != null)	myStmt.close();
				if(myRs != null) myRs.close();
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		return theProduct;

		
		
	}

	public void updateProduct(product theProduct) throws Exception {
		
		PreparedStatement myStmt = null;
		
		
		try{
			//get db connection
			dataSource.setAutoCommit(false);
			//create SQL update statement
			String sql = "UPDATE products "
					+ "SET sku=?,  name=?, price=?, delieverfee=?, descript=?, seller=?"
					+ "WHERE id=?";

			
			//prepare the statement
			myStmt = dataSource.prepareStatement(sql);
			
			
			//set the params
			myStmt.setString(1,theProduct.getSku());
			myStmt.setString(2, theProduct.getName());
			myStmt.setDouble(3, theProduct.getPrice());
			myStmt.setDouble(4,theProduct.getDelieverFee());
			myStmt.setString(5, theProduct.getDescript());
			myStmt.setInt(6, theProduct.getSeller());
			myStmt.setInt(7, theProduct.getId());
			
			//execute SQL statement
			myStmt.execute();
			dataSource.commit();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		finally{
			//close JDBC objects
			try{
				dataSource.setAutoCommit(true);
				if(myStmt != null)	myStmt.close();
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		
	}

	public void deleteProduct(String theProductId)  throws Exception {

		PreparedStatement myStmt = null;
		
		
		try{
				
			dataSource.setAutoCommit(false);
			
			//convert theProductId id to int
			int productId = Integer.parseInt(theProductId);
						
			//get connection to database
					
			
			//create sql to delete product
		    String sql = "delete from images where prod=?";		
			//prepare statement
			myStmt = dataSource.prepareStatement(sql);
					
			//set params
			myStmt.setInt(1, productId);
				
			//execute sql statement
			myStmt.execute();
			
			//create sql to delete product
			sql = "delete from products where id=?";		
			//prepare statement
			myStmt = dataSource.prepareStatement(sql);
					
			//set params
    		myStmt.setInt(1, productId);
			
			//execute sql statement
			myStmt.execute();
			
			dataSource.commit();
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		finally{
			//close JDBC objects
			try{
				dataSource.setAutoCommit(true);
				if(myStmt != null)	myStmt.close();
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		
	}

	public List<product> searchProducts(String theSearchName) throws SQLException {
        List<product> products = new ArrayList<>();
        
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
      
        
        try {
            
            // get connection to database
            
            //
            // only search by name if theSearchName is not empty
            //
            if (theSearchName != null && theSearchName.trim().length() > 0) {
                // create sql to search for students by name
                String sql = "select * from products where lower(name) like ?";
                // create prepared statement
                myStmt = dataSource.prepareStatement(sql);
                // set params
                String theSearchNameLike = theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
            }  
            
            else {
				// create sql to get all students
				String sql = "select * from products order by name";

				// create prepared statement
				myStmt = dataSource.prepareStatement(sql);
			}
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
				//read products info from the data
				int id = myRs.getInt("id");
				String sku = myRs.getString("sku");	
				String name = myRs.getString("name");		
				double price = myRs.getDouble("price");	
				double DelieverFee = myRs.getDouble("delieverFee");	
				String Descript = myRs.getString("descript");			
				int Seller = myRs.getInt("seller");	
				String Date = myRs.getString("postdate");
				//create a new product object
				product theProduct = new product(id,sku,name,price,DelieverFee,Descript,Seller,Date);
                
                // add it to the list of students
				products.add(theProduct);           
            }
            
        }catch(Exception ex){
			System.out.println(ex.getMessage());
		}
        finally {
            // clean up JDBC objects
        	try{
				if(myStmt != null)	myStmt.close();
				if(myRs != null)	myRs.close();
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
        return products;

        }
	}
	
	

