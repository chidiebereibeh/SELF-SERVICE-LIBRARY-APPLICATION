package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty isbn;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty shelve;
    private final StringProperty loanStatus;


    public Book(String isbn, String title, String author, String shelve, boolean loanStatus){
        this.isbn = new SimpleStringProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.shelve = new SimpleStringProperty(shelve);
        this.loanStatus = new SimpleStringProperty(loanStatus ? "On Loan" : "Available");
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

    public String getLoanStatus() {
        return loanStatus.get();
    }

    public StringProperty loanStatusProperty() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus.set(loanStatus);
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

    public String getShelve() {
        return shelve.get();
    }

    public StringProperty shelveProperty() {
        return shelve;
    }

    public void setShelve(String shelve) {
        this.shelve.set(shelve);
    }
}
