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
		
		// There is another type of statement , scroll type. as you see above, the default type of statement is forward-inly, which means you can only access the line which is next to the current line.
		// while scroll type allows you go to any line of resultSset 
		Statement stmt1=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs= stmt1.executeQuery("any select sql");
		rs.afterLast();// the method afterLast() can move pointer to point to the position after the last line .
		rs.beforeFirst();// the method afterLast() can move pointer to point to the line before the first line.
		rs.previous(); // the method previous() can move the pointer to point to previous line .(which is opposite to next())
		rs.first(); // the method first() can move the pointer to point to first line.
		rs.last(); // the method last() can move the pointer to point to last line.
		rs.absolute(5);// absolute() can help us access any line of resultSet. E.X : we access 5th line here.
		rs.absolute(-1);// absolute() can also take negative number, which count from last line.E.X: we access last line here.
		rs.relative(-2); // relative() can move the pointer to the position that relative to current line.E.X: we move the pointer two row backward here .
		
		// like file , we also need to close database after we use it, try-with-resources can automatically close all resources. 
		// the closing order is normally  ResultSet -> Statement -> Connection
		// but we can also close Connection/Statement directly, it will close ResultSet/ResultSet and Stament automatically. 
		// another important rule is that , JDBC automatically close a ResultSet when running another sql from the same Statement
	}
}
