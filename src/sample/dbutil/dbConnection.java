package sample.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private  static final String SQCON = "jdbc:mysql://localhost:3306/library_data?user=root&password=smilicb2k";
    private  static final String URL = "jdbc:mysql://127.0.0.1:3306/library_data?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private  static final String USER = "root";
    private  static final String PASSWORD = "smilicb2k";


    public static Connection getConnection() throws SQLException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

        return null;
    }




}
