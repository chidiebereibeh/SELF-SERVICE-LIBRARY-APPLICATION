package member;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberController {
    public void loadMemberPage() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load((getClass().getResource("member.fxml").openStream()));

            Scene scene = new Scene(root);

            userStage.setScene(scene);
            userStage.setTitle("LIBRARY MENU");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
