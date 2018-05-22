package Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Author;
import sample.dbutil.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorLoader {
    public static ObservableList<Author> load() {
        ObservableList<Author> authors = FXCollections.observableArrayList();

        try {

            Connection connection = dbConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from author");

            while (resultSet.next()) {
                authors.add(new Author(resultSet.getInt("id"), resultSet.getString("name")));
            }

        } catch (SQLException e) {
            System.err.println("Error!" + e);

        }

        return authors;
    }
}
