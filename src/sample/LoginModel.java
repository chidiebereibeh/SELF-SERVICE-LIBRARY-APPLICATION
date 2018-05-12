package sample;

import sample.dbutil.dbConnection;

import java.sql.*;

public class LoginModel {

    //initialize the connection object
    Connection connection;

    //handle connection creation
    public LoginModel() {
        try {
            this.connection = dbConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }

    //check if database is connected
    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    //check if user has been logged in
    public boolean isLogin(String username, String password, String type) throws Exception {
        ResultSet rs = null;

        String sql = (String.format("SELECT * FROM users WHERE username='%s' and password='%s' and type='%s'", username, password, type));
        //String _sql =(String.format("INSERT INTO users(username,password,option) values('%s','%s','%s')",username,password,option));

        try {
            Statement statement = this.connection.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                java.util.Date dt = new java.util.Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(dt);

                PreparedStatement st = this.connection.prepareStatement("update users set updated_at=? where id=?");
                st.setString(1, currentTime);
                st.setString(2, rs.getString("id"));

                st.execute();

                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
        }

        return false;
    }
}
