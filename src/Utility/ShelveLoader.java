package Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Shelve;
import sample.dbutil.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShelveLoader {
    public static ObservableList<Shelve> load() {
        ObservableList<Shelve> shelves = FXCollections.observableArrayList();

        try {

            Connection connection = dbConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from shelve");

            while (resultSet.next()) {
                shelves.add(new Shelve(resultSet.getInt("id"), resultSet.getString("name")));
            }

        } catch (SQLException e) {
            System.err.println("Error!" + e);

        }

        return shelves;
    }
}
