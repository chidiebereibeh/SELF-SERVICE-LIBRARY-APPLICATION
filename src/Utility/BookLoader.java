package Utility;

import model.Book;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.dbutil.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookLoader {
    public static boolean loadLoans = true;

    public static void load(ObservableList<Book> data, TableColumn<Book, String> isbnColumn, TableColumn<Book,
            String> titleColumn, TableColumn<Book, String> authorNameColumn, TableColumn<Book, String> shelveIDColumn, TableColumn<Book, String> loanstatColumn) {

        try {

            String sql;

            if (loadLoans) {
                sql = "select b.isbn as isbn, b.title as title, a.name as author, s.name as shelve, b.onLoan as loan from books b left join author a on b.author_id=a.id left join shelve s on b.shelve_id=s.id";
            } else {
                sql = "select b.isbn as isbn, b.title as title, a.name as author, s.name as shelve, b.onLoan as loan from books b left join author a on b.author_id=a.id left join shelve s on b.shelve_id=s.id where b.onLoan=0";
            }

            data.removeAll(data);

            Connection connection = dbConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                data.add(new Book(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getBoolean(5)));
            }

        } catch (SQLException e) {
            System.err.println("Error!" + e);

        }

        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        shelveIDColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("shelve"));
        loanstatColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("loanStatus"));



    }

    public static void refreshBookList(TableView<Book> list, ObservableList<Book> data) {
        list.setItems(null);
        list.setItems(data);
    }
}
