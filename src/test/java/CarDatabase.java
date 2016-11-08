import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by ox6185ys on 11/8/2016.
 */
public class CarDatabase {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/car";     //Connection string – where's the database?

    static final String USER = "root";   //TODO replace with your username
    static final String PASSWORD = "itecitec";   //TODO replace with your password

    public static void main(String[] args) throws Exception {      //TODO handle exceptions properly

        Class.forName(JDBC_DRIVER);   //Instantiate the driver class

        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);  //Create a connection to DB
        Statement statement = connection.createStatement();     //A statement object is used to run SQL statements

        statement.execute("CREATE TABLE IF NOT EXISTS vehicles (MY_ID INTEGER, NAME VARCHAR(50))");  //Run some SQL – create table
        statement.execute("INSERT INTO vehicles VALUES (1, 'Ford')  ");      //Add some test data
        statement.execute("INSERT INTO vehicles VALUES (2, 'Toyota')  ");    //And some more test data

        ResultSet rs = statement.executeQuery("SELECT * FROM vehicles");   //Fetch all data; data is returned in a ResultSet

        while (rs.next()) {								//Loop over ResultSet, and print data
            System.out.println("The ID is " + rs.getInt(1));
            System.out.println("The name is " + rs.getString(2));
            System.out.println("*****");
        }

        statement.execute("DROP TABLE vehicles");      //Delete the table (you don't usually do this in your applications :)

        rs.close();          				//Close the result set, statement and connection, release resources
        statement.close();
        connection.close();
    }
}


