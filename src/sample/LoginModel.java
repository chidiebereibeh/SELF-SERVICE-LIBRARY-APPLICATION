package sample;

import sample.dbutil.dbConnection;

import java.sql.*;

public class LoginModel {

    //initialize the connection object
    Connection connection;

    //handle connection creation
    public LoginModel(){
        try {
            this.connection = dbConnection.getConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        if(this.connection == null){
            System.exit(1);
        }
    }

    //check if database is connected
    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    //check if user has been logged in
    public boolean isLogin(String username, String password, String option)throws Exception{

        ResultSet rs = null;

        String sql = String.format("SELECT * FROM users where username='%s' and password='%s' and option='%s'", username, password, option);

        try {
            Statement statement = this.connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()){
                return  true;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            rs.close();
        }

        return false;
    }
}
