package sample.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private  static final String SQCON = "jdbc:sqlite:database.db";


    public static Connection getConnection() throws SQLException{

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCON);
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

        return null;
    }


}
