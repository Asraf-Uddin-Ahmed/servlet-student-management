import java.io.*;
import javax.servlet.*;
import java.util.*;
import java.sql.*;


public class user_info extends GenericServlet
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
		pw.println("<body style=\"background-color:black; font-family:verdana; color:blue\">");
		pw.println("<h1 style=\"color:red; text-align:center\">");
		pw.println("User Information");
		pw.println("</h1>");
		pw.println("<form>");
		pw.println("<table style=\"color:green\">");
		pw.println("<tr>");
		pw.println("<td> <B> User name </td>");
		pw.println("<td> <input type=textbox name=\"username\" size=\"25\" value=\"\" /> </td>");
		pw.println("</tr> ");
		pw.println("<tr> ");
		pw.println("<td><B>Pasword </td> ");
		pw.println("<td> <input type=password name=\"pwd\" size=\"25\" value=\"\" /> </td> ");
		pw.println("</tr> ");
		pw.println("</table> ");
		pw.println("<br/> ");
		pw.println("<input type=submit value=\"Submit\"> ");

		pw.println("<a href=\"http://localhost:8080/project/user_table\" target=\"_blank\"> <input type=submit value=\"View all users\"> </a>");

		pw.println("</form>");

		// Get enumeration of parameter names.
		Enumeration e = req.getParameterNames();
		// Display parameter names and values.
		if(e.hasMoreElements())
		{
			String PwdName = (String)e.nextElement();
			pw.println(PwdName + " = ");
			String PwdValue = req.getParameter(PwdName);
			pw.println(PwdValue +"<br/>");

			String UserName = (String)e.nextElement();
			pw.println(UserName + " = ");
			String UserValue = req.getParameter(UserName);
			pw.println(UserValue +"<br/>");

			if( sq.insert(UserValue, PwdValue)==false )
				pw.println("<b>Name exist!!</b><br/> please reenter your name...");

		}

		//pw.println("good bye");
		pw.println("</body>");
		pw.println("</html>");

		pw.close();
	}
}