package member;

import model.Book;
import Utility.BookLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class MemberController implements Initializable {
    memberToUse mem = new memberToUse();

    @FXML
    public TableView<Book> bookListTab;

    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorNameColumn;
    @FXML
    private TableColumn<Book, String> shelveIDcolumn;
    @FXML
    private TableColumn<Book, String> landsatColumn;


    private ObservableList<Book> data = FXCollections.observableArrayList();
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void loadAllBooks() {
        BookLoader.loadLoans = false;
        BookLoader.load(this.data, this.isbnColumn, this.titleColumn, this.authorNameColumn, this.shelveIDcolumn, this.landsatColumn);
        BookLoader.refreshBookList(this.bookListTab, this.data);
    }


    public void loadMemberPage() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load((getClass().getResource("member.fxml").openStream()));

            Scene scene = new Scene(root);

            userStage.setScene(scene);
            userStage.setTitle("LIBRARY MENU");
            userStage.setResizable(true);
            userStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


    public void gotoBooks(ActionEvent event) {
        mem.memberTransaction();
    }



    @FXML
    private void checkOutBook(ActionEvent event){

        String sqlbook = "";


    }
}
