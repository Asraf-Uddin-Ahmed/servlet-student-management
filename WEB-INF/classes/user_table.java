import java.io.*;
import javax.servlet.*;
import java.util.*;
import java.sql.*;


public class user_table extends GenericServlet
{
	public void service(ServletRequest req, ServletResponse res) throws IOException
	{
		// HTML Sercvlet
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		//DB connection
		SQL sq=null;
		try
		{
			sq = new SQL( "root", "123", "test" );
		}
		catch(Exception e)
		{
			pw.println(e);
		}

		pw.println("<html>");
		pw.println("<head> </head>");
		pw.println("<body style=\"background-color:black; font-family:verdana; color:yellow\">");
		pw.println("<center>");
		pw.println("<h1 style=\"color:red; text-align:center\">");
		pw.println("User Information");
		pw.println("</h1>");
		pw.println("<h2 style=\"color:grey\">");
		pw.println("User database (userinfo)");
		pw.println("</h2>");

		pw.println("<table border=\"2\" cellpadding=\"25\" cellspacing=\"2\" frame=\"border\">");
		pw.println("<thead style=\"color:blue\">");
		pw.println("<th> User Name </th>");
		pw.println("<th> Password </th>");
		pw.println("</thead>");
		pw.println("<tbody>");

		try
		{
			sq.rs = sq.stmt.executeQuery("select * from userinfo;");

			while( sq.rs.next() )
			{
				String Name = sq.rs.getString( "username" );
				String pwd = sq.rs.getString( "pwd" );
				pw.println("<tr>");
				pw.println("<td>" +Name+ "</td>");
				pw.println("<td>" +pwd+ "</td>");
				pw.println("</tr>");

			}
		}
		catch( SQLException e)
		{
			pw.println("<p style=\"color:red\"> Database connection Error !! </p>");
		}

		pw.println("</tbody>");
		pw.println("</table>");

		pw.println("</center>");
		pw.println("</body>");
		pw.println("</html>");

		pw.close();
	}
}