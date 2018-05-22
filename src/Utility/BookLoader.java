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

    public static void load(ObservableList<Book> data, TableColumn<Book, String> isbnColumn, TableColumn<Book,
            String> titleColumn, TableColumn<Book, String> authorNameColumn, TableColumn<Book, String> shelveIDColumn) {

        try {

            Connection connection = dbConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from books");

            while (resultSet.next()) {
                data.add(new Book(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4)));
            }

        } catch (SQLException e) {
            System.err.println("Error!" + e);

        }

        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        shelveIDColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("shelve_id"));
    }

    public static void refreshBookList(TableView<Book> list, ObservableList<Book> data) {
        list.setItems(null);
        list.setItems(data);
    }
}
