package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty isbn;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty shelve_id;


    public Book(String isbn, String title, String author, String shelve_id){
        this.isbn = new SimpleStringProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.shelve_id = new SimpleStringProperty(shelve_id);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getShelve_id() {
        return shelve_id.get();
    }

    public StringProperty shelve_idProperty() {
        return shelve_id;
    }

    public void setShelve_id(String shelve_id) {
        this.shelve_id.set(shelve_id);
    }
}
