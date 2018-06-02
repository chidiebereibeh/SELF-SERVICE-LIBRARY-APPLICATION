package Utility;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Loan;
import sample.dbutil.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanLoader {
    public static boolean loadActiveLoans = true;

    public static void loadAllLoans(ObservableList<Loan> data, TableColumn<Loan, String> usernameColumn, TableColumn<Loan, String> isbnColumn, TableColumn<Loan,
            String> titleColumn, TableColumn<Loan, String> authorNameColumn, TableColumn<Loan, String> loanstatColumn,TableColumn<Loan, String> loanDateColumn) {

        loadAndPrepareData(data);

        usernameColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("member"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("title"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("author"));
        loanstatColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("loanStatus"));
        loanDateColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("loaned_at"));
    }

    public static void loadMemberLoan(ObservableList<Loan> data, TableColumn<Loan, String> idColumn, TableColumn<Loan, String> isbnColumn, TableColumn<Loan,
            String> titleColumn, TableColumn<Loan, String> loanTimeColumn, TableColumn<Loan, String> returnTimeColumn) {

        loadAndPrepareData(data);

        idColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("id"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("title"));
        loanTimeColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("loaned_at"));
        returnTimeColumn.setCellValueFactory(new PropertyValueFactory<Loan, String>("r"));
    }

    private static void loadAndPrepareData(ObservableList<Loan> data) {
        try {

            String sql;

            if (loadActiveLoans) {
                sql = "select l.id as id, l.loaned_at as loaned_at, l.users_id as users_id, l.books_isbn as books_isbn, l.returned_at as returned_at, l.books_isbn as isbn, b.title as title, u.username as username, a.name as name from loan l left join books b on b.isbn=l.books_isbn left join users u on users_id=u.id left join author a on a.id=b.author_id where l.returned_at is null";
            } else {
                sql = "select l.id as id, l.loaned_at as loaned_at, l.users_id as users_id, l.books_isbn as books_isbn, l.returned_at as returned_at, l.books_isbn as isbn, b.title as title, u.username as username, a.name as name from loan l left join books b on b.isbn=l.books_isbn left join users u on users_id=u.id left join author a on a.id=b.author_id";
            }

            data.removeAll(data);

            Connection connection = dbConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                data.add(new Loan(
                        resultSet.getString("id"),
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("returned_at"),
                        resultSet.getString("loaned_at")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error!" + e);

        }
    }

    public static void refreshBookList(TableView<Loan> list, ObservableList<Loan> data) {
        list.setItems(null);
        list.setItems(data);
    }
}
