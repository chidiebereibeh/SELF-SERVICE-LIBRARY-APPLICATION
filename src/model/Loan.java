package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loan {
    private final StringProperty id;
    private final StringProperty isbn;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty loanStatus;
    private final StringProperty member;
    private final StringProperty loaned_at;
    private final StringProperty returned_at;
    private final StringProperty due_date;


    public Loan(String id, String isbn, String title, String author, String member, String return_at, String loaned_at, String due_date) {
        this.id = new SimpleStringProperty(id);
        this.isbn = new SimpleStringProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.member = new SimpleStringProperty(member);
        this.loaned_at = new SimpleStringProperty(loaned_at);
        this.returned_at = new SimpleStringProperty(return_at);
        this.loanStatus = new SimpleStringProperty(return_at == null ? "Active" : "Inactive");
        this.due_date = new SimpleStringProperty(due_date);
    }

    public String getId() {
        return id.get();
    }

    public String getDue_date() {
        return due_date.get();
    }

    public StringProperty due_dateProperty() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date.set(due_date);
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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

    public String getLoanStatus() {
        return loanStatus.get();
    }

    public StringProperty loanStatusProperty() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus.set(loanStatus);
    }

    public String getMember() {
        return member.get();
    }

    public StringProperty memberProperty() {
        return member;
    }

    public void setMember(String member) {
        this.member.set(member);
    }

    public String getLoaned_at() {
        return loaned_at.get();
    }

    public StringProperty loaned_atProperty() {
        return loaned_at;
    }

    public void setLoaned_at(String loaned_at) {
        this.loaned_at.set(loaned_at);
    }

    public String getReturned_at() {
        return returned_at.get();
    }

    public StringProperty returned_atProperty() {
        return returned_at;
    }

    public void setReturned_at(String returned_at) {
        this.returned_at.set(returned_at);
    }


}
