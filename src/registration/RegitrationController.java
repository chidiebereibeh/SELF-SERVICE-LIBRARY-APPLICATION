package registration;

import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import member.MemberController;
import sample.option;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.dbutil.dbConnection.getConnection;

public class RegitrationController implements Initializable{

    Connection connection;


    @FXML
            public TextField createusername;
    @FXML
            public TextField createpassword;
    @FXML
            public ComboBox accountcombobox;


    //handle connection creation
     public void create(ActionEvent event){

        try {
            String option = this.accountcombobox.getValue().toString().toLowerCase();
            this.registerUser(this.createusername.getText(), this.createpassword.getText(), option);
        }catch (SQLException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //check if database is connected
    public boolean isDatabaseConnected(){
        return this.connection != null;
    }

    //check if user has been logged in
    public boolean registerUser( String username, String password, String type)throws Exception{
        ResultSet rs = null;

        //String sql =(String.format("SELECT * FROM users WHERE username='%s' and password='%s' and type='%s'",username,password,option));
        String _sql =("INSERT INTO users(username,password, type) values(?,?,?)");

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(_sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3, type);

            preparedStatement.execute();
            if(type.equals("member")){
                MemberController con = new MemberController();
                con.loadMemberPage();
            }   else{
                AdminController con = new AdminController();
                con.loadAdminPage();
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        return false;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.accountcombobox.setItems(FXCollections.observableArrayList(option.values()));
    }
}






