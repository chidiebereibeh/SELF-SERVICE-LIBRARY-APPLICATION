package sample;

import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import member.MemberController;
import menu.menuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    LoginModel loginM = new LoginModel();

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox combobox;

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (this.loginM.isDatabaseConnected()) {
            System.out.println("Connected to Database");
        } else {
            System.out.println("not Connected to Database");
        }

        this.combobox.setItems(FXCollections.observableArrayList(option.values()));

    }


    @FXML
    public void Login(ActionEvent event) {
        try {

            String option = this.combobox.getValue().toString().toLowerCase();

            if (this.loginM.isLogin(this.username.getText(), this.password.getText(), option)) {
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                System.out.println("Login passed ");

                switch (option) {
                    case "admin":
                        AdminController adminController = new AdminController();
                        adminController.loadAdminPage();
                        break;
                    case "member":
                        MemberController memberController = new MemberController();
                        memberController.loadMemberPage();
                        break;
                }
            } else {
                System.out.println("not working");
            }


        } catch (Exception localException) {

            localException.printStackTrace();
        }

    }


}
