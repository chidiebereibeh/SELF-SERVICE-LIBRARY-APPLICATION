package Admin;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.dbutil.dbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    @FXML
    private TextField isbnText;

    @FXML
    private TextField titleText;

    @FXML
    private TextField authorName;
    @FXML
    private TextField shelveID;

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

    private dbConnection dc;

    private ObservableList<Book> data = FXCollections.observableArrayList();
    ;

    private Connection connection;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dc = new dbConnection();
        loadAllBooks();
    }

    @FXML
    private void loadBook(ActionEvent event) throws SQLException {

        loadAllBooks();
    }

    private void loadAllBooks() {
        BookLoader.load(this.data, this.isbnColumn, this.titleColumn, this.authorNameColumn, this.shelveIDcolumn);
        BookLoader.refreshBookList(this.bookListTab, this.data);
    }

    public void addBooks(ActionEvent event) {

        String sqlInsert = "insert into books(isbn,title,author,shelve_id) values(?, ?, ?, ?)";

        try {
            this.connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlInsert);

            String isbn = this.isbnText.getText();
            String title = this.titleText.getText();
            String author = this.authorName.getText();
            String shelveID = this.shelveID.getText();

            statement.setString(1, isbn);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, shelveID);

            statement.execute();
            connection.close();

            this.data.add(new Book(isbn, title, author, shelveID));
            BookLoader.refreshBookList(this.bookListTab, this.data);
            clearFormFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void clearField(ActionEvent event) {

        clearFormFields();

    }

    private void clearFormFields() {
        this.isbnText.setText("");
        this.titleText.setText("");
        this.authorName.setText("");
        this.shelveID.setText("");
    }

    @FXML
    private void deleteBooks(ActionEvent event) {


        try {
            String sql = "delete from books where isbn=?";
            String isbn = bookListTab.getSelectionModel().getSelectedItem().isbnProperty().getValue();

            Connection conn = dbConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, isbn);
            ps.execute();

            bookListTab.getItems().removeAll(bookListTab.getSelectionModel().getSelectedItems());
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    public void loadAdminPage() {

        try {

            this.connection = dbConnection.getConnection();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
