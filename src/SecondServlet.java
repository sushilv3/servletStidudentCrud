import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecondServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.write("<p>Hello Servlet Home Page : doGet request ");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write("<p>Hello Servlet Home Page : doPost request ");
		String rollno = request.getParameter("rollNo");
		String name = request.getParameter("name");
		String address = request.getParameter("address");

		try {
			// load the driver
			Class.forName("org.h2.Driver");
			// create connection object
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/collegeappdb", "sushil", "sushil");
			// create the prepared statement object
			PreparedStatement ps = con.prepareStatement("insert into Stu values(?,?,?)");

			ps.setString(1, rollno);
			ps.setString(2, name);
			ps.setString(3, address);

			int i = ps.executeUpdate();
			if (i > 0)
				out.print("You are successfully registered...");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.write("<p>Hello Servlet Home Page : doPut request ");

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.write("<p>Hello Servlet Home Page : doDelete request ");

	}

}
