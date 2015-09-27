import java.sql.*;
public class domaildatabase
{
	public ResultSet select(String table) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery("select * from "+table);
		return rs;
	}
	public ResultSet selectuser(String table,String value1) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery("select * from "+table+" where uname='"+value1+"'");
		return rs;
	}
	
	public void insertuserinfo(String table,int value1,String value2,String value3,String value4,String value5,String value6,String value7,String value8) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		st.execute("insert into "+table+" values("+value1+",'"+value2+"','"+value3+"','"+value4+"','"+value5+"','"+value6+"','"+value7+"','"+value8+"')");
		
	}
	public void insertmsg(String table,int value1,int value7,String value2,String value3,String value4,String value5,String value6) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		st.execute("insert into "+table+" values("+value1+","+value7+",'"+value2+"','"+value3+"','"+value4+"','"+value5+"','"+value6+"')");		
	}
	public void trashinsert(String table,int value1,int value7,String value2,String value3,String value4,String value5,String value6,String value8) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		st.execute("insert into "+table+" values("+value1+","+value7+",'"+value2+"','"+value3+"','"+value4+"','"+value5+"','"+value6+"','"+value8+"')");
	}
	public ResultSet singlerow(String table,String value1,String value2) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery("select * from "+table+" where uname='"+value1+"' and password='"+value2+"'");
		return rs;
	}
	public ResultSet single(String table,int value1) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery("select * from "+table+" where serialno="+value1);
		return rs;
	}
	public ResultSet getrows(String table,String value1) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		System.out.println("Hello");
		ResultSet rs=st.executeQuery("select * from "+table+" where tomail='"+value1+"'");
		System.out.println("Hello1");
		return rs;
	}
	public void selectfrom(String table,String value1) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		st.execute("select * from "+table+" where frommail='"+value1+"'");
	}
	
	public void deletesinglerow(String table,int value1) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		st.execute("delete from "+table+" where serialno="+value1);
	}
	public void deleteto(String table,String value1) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		st.execute("delete from "+table+" where tomail='"+value1+"'");
	}
	public void deletefrom(String table,String value1) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		st.execute("delete from "+table+" where frommail='"+value1+"'");
	}
	public void delete(String table) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/domail?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		st.execute("Delete From "+table);
	}
	
}