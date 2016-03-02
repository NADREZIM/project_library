package liba.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 06.01.2016.
 */
public class Report {
    private long id;
    private Book book;
    private User user;
    private Date rentBook;
    private Date returnBook;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRentBook() {
        return rentBook;
    }

    public void setRentBook(Date rentBook) {
        this.rentBook = rentBook;
    }

    public Date getReturnBook() {
        return returnBook;
    }

    public void setReturnBook(Date returnBook) {
            this.returnBook = returnBook;
    }


    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", rentBook=" + DateFormat.getDateInstance(DateFormat.SHORT).format(rentBook) +
                ", returnBook=" + DateFormat.getDateInstance(DateFormat.SHORT).format(returnBook) +
                '}';
    }

    public Date setStringDate(String birthdayString) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Date bird = null;
        try {
            bird = format.parse(birthdayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bird;
    }
}