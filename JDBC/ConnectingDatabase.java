package test;

public class ConnectingDatabase{
	
	// a standard JDBC url has three parts:
	//1 the protocol jdbc (this one is always same) 
	//2 the name of database product (for example , it can be mysql, postgresql and derby which is inside the JDK)
	//3 the rest of it, which contains the location ( it is possibly omitted )and the name of the database.
	//  good url example can be:jdbc:derby:zoo, jdbc:postgresql://localhost/zoo , jdbc:oracle:thin@123.123.123.123:1521:zoo
	public static void main(String[] args) throws SQLException{
		// there are two ways to get a Connection object: use DriverManager/DataSource, DataSource is better , but exam only includes DriverManager
		// DriverManager belongs to JDK, it offers getConnection to get the Connection, and it uses factory design pattern 
		//Class.forName(className); forName method can check if jar file includes such a driver file.
		Connection conn=DriverManager.getConnection("type your URL");
		System.out.println(conn);
		
		//in order to run sql on database, we need to tell  a Obeject called Statement .
		// get a statement from a database is very easy
		Statement stmt=conn.createStatement();
		
		// another signature takes two parameters 
		//Statement stmt2=conn.createStatement(resultSetType, resultSetConcurrency)
		
		// then we use the method executeUpdate() on the stmt , it takes sql query as parameter and run it on database. it returns the number of rows that are affected
		stmt.executeUpdate("insert into zoo values(10, 'pig','3')");
		// another method called execute() which can handle both query and update
		// if the parameter is select(query) , then it will return ture , otherwise (update)it returns false
		boolean isResultSet=stmt.execute(" any sql");
		if(isResultSet){
			ResultSet rs =stmt.getResultSet();
			// it shows the parameter is select query
		}
		else if(!isResultSet){
			int res= stmt.getUpdateCount();
			// it shows the parameter is an upadate query
		}
		
		// if we have a mismatch between method and query type such as select-executeUpdate()/ delete-executeResultSet() , we will get exception. 
	}
}
