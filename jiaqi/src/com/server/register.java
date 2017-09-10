package com.server;
import com.connection.*;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private static final String qry = "INSERT INTO users(name,contact,password) "+
			" VALUES(?,?,?) ";
	
    private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
		con = ConnectionManager.getConnection();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/regist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String contact = request.getParameter("contact");
		String pin = request.getParameter("pin");
		String uname = request.getParameter("username");
		String err = "";
		
		try{
			if(registerCon.check(con, contact, uname)) {
			
	        String recipient = request.getParameter("contact");
	        String subject = request.getParameter("username");
	        String content = request.getParameter("pin");
	     
	        String thank = "";
	        if(content != null)
	        	thank = "3Q you for register";
	        
	        EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    thank);
	        
			con.setAutoCommit(false);
			ps = con.prepareStatement(qry);
			ps.setString(1, uname);
			ps.setString(2, contact);
			ps.setString(3, pin);
			ps.executeUpdate();
			con.commit();
			
			}else{
				err = "email or user name already taken";
			}
			
		}catch(SQLException ex){
			System.out.println(ex.getMessage());

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			err = "invalid email address";
			e.printStackTrace();
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			err = "message not send, plz check if ur email is correct";
			e.printStackTrace();
		}finally{
			try{
				con.setAutoCommit(true);
				ps.close();
			}catch(Exception ex){
				System.out.println(ex.getMessage());

			}
			request.getSession().setAttribute("err", err);
			response.sendRedirect("http://localhost:8080/bulldog/register");
		}
	}

}
