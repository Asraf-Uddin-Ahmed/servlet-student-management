import java.util.*;
import java.sql.*;

class SQL
{
	Connection con;
	Statement stmt;
	ResultSet rs;

	SQL(String UserName, String Password, String DBname)  throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBname, UserName, Password);
		stmt = con.createStatement();

// checking existance of table userinfo

		//net
		/*boolean exist = false;
		DatabaseMetaData meta = con.getMetaData();
		rs = meta.getTables(null, null, null, new String[]{"TABLE"});
		while (rs.next())
		{
			String tableName = rs.getString("TABLE_NAME");
			//System.out.println("tableName=" + tableName);
			if( tableName=="userinfo" )
				exist = true;
		}
		if( exist==false )
		{
			stmt.executeUpdate("create table userinfo(username char(25), pwd char(25), primary key(username) );");
		}*/

		//own
		/*rs = stmt.executeQuery("show tables;");

		boolean exist = false;
		//System.out.println("    Table   ");
		//System.out.println("------------");
		while( rs.next() )
		{
			String Name = rs.getString( "Tables_in_test" );
			//System.out.println(Name);
			if( Name=="userinfo" )
				exist = true;
		}

		if( exist==false )
		{
			stmt.executeUpdate("create table userinfo(username char(25), pwd char(25), primary key(username) );");
		}*/

	}

	boolean isConnected() throws SQLException
	{
		return !con.isClosed();
	}

	void close() throws SQLException
	{
		con.close();
	}

	void showTable() throws SQLException
	{
		rs = stmt.executeQuery("select * from userinfo;");

		System.out.println(" Name"+"\t"+" pwd");
		System.out.println("------------");
		while( rs.next() )
		{
			String Name = rs.getString( "username" );
			String pwd = rs.getString( "pwd" );
			System.out.println(Name+"\t"+pwd);
		}
	}

	boolean insert( String username, String pwd )
	{
		try
		{
			stmt.executeUpdate("insert into userinfo values('"+username+"','"+pwd+"');");
			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	}
}

public class DBconnector
{
	public static void main( String args[] ) throws Exception
	{
		SQL sq = new SQL( "root", "2011", "test" );

		if ( sq.isConnected() )
			System.out.println("SQL is connected\n");

		System.out.println( sq.insert("mad","dad") );
		sq.showTable();  // username, pwd
		sq.close();
		if ( !sq.isConnected() )
			System.out.println("\nSQL is disconnected");

	}
}
