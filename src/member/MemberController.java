package member;

import Utility.BookLoader;
import Utility.LoanLoader;
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
import model.Book;
import model.Loan;
import sample.dbutil.dbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class MemberController implements Initializable {
    memberToUse mem = new memberToUse();

    @FXML
    public TableColumn loanid;
    @FXML
    public TableColumn loanbooktitle;
    @FXML
    public TableColumn loantime;
    @FXML
    public TableColumn loanisbn;
    @FXML
    public TableColumn loanreturntime;


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
//    @FXML
//    private TableColumn<Loan, String> loanidColumn;
//    @FXML
//    private TableColumn<Loan, String> loanbooktitleColumn;
//    @FXML
//    private TableColumn<Loan, String> loantimeColumn;
//    @FXML
//    private TableColumn<Loan, String> loanisbnColumn;
//    @FXML
//    private TableColumn<Loan, String> loanreturntimeColumn;


    private ObservableList<Book> bookData = FXCollections.observableArrayList();
    private ObservableList<Loan> loanData = FXCollections.observableArrayList();
    private Connection connection;
    @FXML
    private TableView<Loan> loanListTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loadBooks();
    }


    @FXML
    private void loadAllBooks() {
        loadBooks();
    }

    private void loadBooks() {
        BookLoader.loadLoans = false;
        BookLoader.load(this.bookData, this.isbnColumn, this.titleColumn, this.authorNameColumn, this.shelveIDcolumn);
        BookLoader.refreshBookList(this.bookListTab, this.bookData);
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
    private void checkOutBook(ActionEvent event) {
        //get user details from preferences
        Preferences userPreference = Preferences.userRoot();

        try {

            String sqloan = "insert into loan(books_isbn,users_id,loaned_at) values(?,?,?)";
            String book_isbn = bookListTab.getSelectionModel().getSelectedItem().isbnProperty().getValue();
            String user_id = userPreference.get("id", "");

            if (!user_id.equals("")) {
                String currentTime = getCurrentDate();

                Connection conn = dbConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqloan);
                ps.setString(1, book_isbn);
                ps.setString(2, user_id);
                ps.setString(3, currentTime);
                ps.execute();

                //update book loan status
                PreparedStatement updateBook = conn.prepareStatement("Update books set onLoan=1 where isbn=?");
                updateBook.setString(1, book_isbn);
                updateBook.execute();

                //remove book from list
                bookListTab.getItems().removeAll(bookListTab.getSelectionModel().getSelectedItems());

            } else {
                //TODO:: alert("Sorry! You can only checkout after logging in.");
                //TODO:: redirect user to the login page;
                System.out.println("Checkout failed for " + user_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private String getCurrentDate() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

    @FXML
    private void loanButton(ActionEvent event) {
        LoanLoader.loadMemberLoan(this.loanData, this.loanid, this.loanisbn, this.loanbooktitle, this.loantime,
                this.loanreturntime);
        LoanLoader.refreshBookList(this.loanListTab, this.loanData);

    }

    @FXML
    private void checkin(ActionEvent event) {

        try {
            Connection conn = dbConnection.getConnection();

            PreparedStatement checkInLoan = conn.prepareStatement("update loan set returned_at=? where id=?");
            checkInLoan.setString(1, getCurrentDate());
            checkInLoan.setString(2, loanListTab.getSelectionModel().getSelectedItem().getId().toString());
            checkInLoan.execute();

            PreparedStatement updateBooLoanStatus = conn.prepareStatement("update books set onLoan=0 where isbn=?");
            updateBooLoanStatus.setString(1, loanListTab.getSelectionModel().getSelectedItem().getIsbn().toString());
            updateBooLoanStatus.execute();

            loanListTab.getItems().removeAll(loanListTab.getSelectionModel().getSelectedItem());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
