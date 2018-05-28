package Admin;

import Utility.AuthorLoader;
import Utility.BookLoader;
import Utility.ShelveLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Author;
import model.Book;
import model.Shelve;
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
    private ComboBox<Author> authorComboBox;

    @FXML
    private ComboBox<Shelve> shelveComboBox;

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

    private dbConnection dc;

    private ObservableList<Book> data = FXCollections.observableArrayList();

    private Connection connection;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dc = new dbConnection();
        loadAllBooks();

        this.shelveComboBox.setItems(ShelveLoader.load());
        this.shelveComboBox.setCellFactory(shelveCellFactory);

        this.authorComboBox.setItems(AuthorLoader.load());
        this.authorComboBox.setCellFactory(authorCellFactory);
    }

    Callback<ListView<Author>, ListCell<Author>> authorCellFactory = lv -> new ListCell<Author>() {

        @Override
        protected void updateItem(Author item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.getName());
        }
    };

    Callback<ListView<Shelve>, ListCell<Shelve>> shelveCellFactory = lv -> new ListCell<Shelve>() {

        @Override
        protected void updateItem(Shelve item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? "" : item.getName());
        }
    };

    @FXML
    private void loadBook(ActionEvent event) throws SQLException {
        loadAllBooks();
    }

    private void loadAllBooks() {
        BookLoader.load(this.data, this.isbnColumn, this.titleColumn, this.authorNameColumn, this.shelveIDcolumn, this.landsatColumn);
        BookLoader.refreshBookList(this.bookListTab, this.data);
    }

    public void addBooks(ActionEvent event) {

        String sqlInsert = "insert into books(isbn,title,author_id,shelve_id) values(?, ?, ?, ?)";

        try {
            this.connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlInsert);

            String isbn = this.isbnText.getText();
            String title = this.titleText.getText();
            String author = Integer.toString(this.authorComboBox.getValue().getId());
            String shelveID = Integer.toString(this.shelveComboBox.getValue().getId());

            statement.setString(1, isbn);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.setString(4, shelveID);

            statement.execute();
            connection.close();

            this.data.add(new Book(isbn, title, this.authorComboBox.getValue().getName(), this.shelveComboBox.getValue().getName(), false));
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

        this.authorComboBox.setValue(null);
        this.shelveComboBox.setValue(null);
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
