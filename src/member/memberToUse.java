package member;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class memberToUse {

    public void memberTransaction(){
        try {

            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load((getClass().getResource("memberData.fxml").openStream()));

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Checkout and Checkin");
            userStage.setResizable(true);
            userStage.show();


        }catch (IOException e){
            e.printStackTrace();

        }
    }
}


