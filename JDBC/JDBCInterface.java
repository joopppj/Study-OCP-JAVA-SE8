package test;

import java.sql.*;

public class JDBCInterface{
	public static void main(String[] args) throws SQLException{
		// there are four key JDBC interfaces :Driver, Connection,Statement,ResultSet
		String url="jdbc::derby:zoo";     //assume we have a driver
		try(Connection conn =DriverManager.getConnection(url)){     //getConnection can return a connection instance which connects to database
			Statement stmt=conn.createStatement();      //createStatement() creates a instance of statement
			ResultSet rs= stmt.executeQuery("select name from animal");  //executeQuery can execute the parameter , which is a sql code , then return an instance of resultSet 
			while(rs.next()){
				System.out.println(rs.getString(1));       // this while loop just print  animal names one by one .
			}
		}
	}
}