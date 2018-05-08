package Admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    public void loadAdminPage() {

        try {
            Stage adminStage = new Stage();
            FXMLLoader adminloader = new FXMLLoader();
            Pane root = adminloader.load(getClass().getResource("admin.fxml").openStream());

            Scene scene = new Scene(root);

            adminStage.setScene(scene);
            adminStage.setTitle("ADMIN DASHBOAD");
            adminStage.setResizable(true);
            adminStage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
