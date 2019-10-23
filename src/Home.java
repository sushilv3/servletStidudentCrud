
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class home
 */

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<StudentEntity> students = new ArrayList<>();

	public Home() {
		super();
		this.students = fetchAll();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		ObjectMapper objMap = new ObjectMapper();

		final String jsonString = objMap.writerWithDefaultPrettyPrinter()
		        .writeValueAsString(this.students);
		response.setContentType("application/json");
		 final PrintWriter writer = response.getWriter();
		    writer.write(jsonString);
		    writer.close();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.write("<p>Hello Servlet Home Page : doGet request ");

		String rollno = request.getParameter("rollno");
		String name = request.getParameter("name");
		String address = request.getParameter("address");

		try {
			// load the driver
			Class.forName("org.h2.Driver");
			// create connection object
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/collegeappdb", "sushil", "sushil");
			// create the prepared statement object
			PreparedStatement stmt = con.prepareStatement("insert into Stu values(?,?,?)");

			stmt.setString(1, rollno);
			stmt.setString(2, name);
			stmt.setString(3, address);

			boolean flag = stmt.execute();
			System.out.println(" records inserted");
//			if (i > 0)
//				out.print("You are successfully registered...");
		} catch (Exception ex) {
			System.out.println("roll no already exist ");
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("DO PUT CALLED :");
		out.write("<p>Hello Servlet Home Page : doPut request ");

		String name = request.getParameter("name");
		String rollno = request.getParameter("rollno");
		System.out.println("@@@@@@@@ : rollno " + rollno);
		System.out.println("@@@@@@@@ : name " + name);
//		String address = request.getParameter("address");

		try {
			// load the driver
			Class.forName("org.h2.Driver");
			// create connection object
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/collegeappdb", "sushil", "sushil");
			// create the prepared statement object
			// write update query
			PreparedStatement ps = con.prepareStatement("UPDATE STU SET NAME=? WHERE ROLLNO=?");
			System.out.println("stmt :" + ps);
			ps.setString(1, name);
			ps.setString(2, rollno);
//			ps.setString(3, address);

			int i = ps.executeUpdate();
//			if (i > 0)
			out.print("You are successfully updated...");
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DO DELETE CALLED :");
		PrintWriter out = response.getWriter();
		String rollno = request.getParameter("rollno");
		System.out.println("@@@@@@@@ : rollno " + rollno);
		try {
			// load the driver
			Class.forName("org.h2.Driver");
			// create connection object
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/collegeappdb", "sushil", "sushil");
			// create the prepared statement object

			PreparedStatement pstmt = con.prepareStatement("DELETE FROM STU WHERE ROLLNO=?");
			System.out.println("stmt :" + pstmt);

			pstmt.setString(1, rollno);

			int i = pstmt.executeUpdate();
//			if (i > 0)
			out.print("You are successfully registered...");
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private List<StudentEntity> fetchAll() {
		final List<StudentEntity> students = Arrays.asList(new StudentEntity("11", "Rakesh", "Delhi"),
				new StudentEntity("2", "Atul", "lko"), new StudentEntity("3", "Manish", "Kanpur"),
				new StudentEntity("4", "Satish", "Sitapur"));

		return students;
	}

}
