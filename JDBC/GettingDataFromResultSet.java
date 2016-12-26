package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GettingDataFromResultSet{
	public static void main(String args[]) throws SQLException{
		Connection conn=DriverManager.getConnection("type your URL");
		Statement stmt=conn.createStatement();
		
		ResultSet res=stmt.executeQuery("sql code");
		// we use a method called next() on resultset object, this method return true if the res has next row and it also move its pointer to next row.
		while(res.next()){
			// we can use getXXX() method to get any information we want from that column , this method can take column name or column index(column index starts from 1 rather than 0) 
			int i= res.getInt("column name");
			String s=res.getString(1);
			// if we try to access a column that does not exist (wrong column index/name), it will throw a sql exception
			// there are many other getxxx() methods such as getDouble() that deals with database type  DOUBLE and getObject() that deals with any type.
			// not every primitive has getxxx() , e.x there is no getChar() method , we do not neet it neither.
			// there are 3 getxxx() methods related to time, they are getTime() ,getDate() and getTimestamp(). the objects we can get from these  methods are alod java types such as Date, but we have toLocalDate to convert it.
			
		}
		
	}
}