package sample;

import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import member.MemberController;
import member.memberToUse;
import registration.RegitrationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    LoginModel loginM = new LoginModel();

    RegitrationController regitrationController = new RegitrationController();

    memberToUse mem = new memberToUse();


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
    public boolean Login(ActionEvent event) {
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
                Alert dialogue = new Alert(Alert.AlertType.ERROR, "Invalid login credentials", ButtonType.OK);
                dialogue.show();
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
            return false;
        }

        return false;
    }

    public void createAccount(ActionEvent event) throws Exception{


        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load((getClass(RegitrationController.class).getResource("registration.fxml").openStream()));

            Scene scene = new Scene(root);

            userStage.setScene(scene);
            userStage.setTitle("registration");
            userStage.setResizable(true);
            userStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private Class<RegitrationController> getClass(Class<RegitrationController> regitrationControllerClass) {
        return regitrationControllerClass;
    }


}







