package liba.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public class UserDTO {
    private long id;
    private String name;
    private String login;
    private String password;
    private Date birthday;
    private List<BookDTO> books;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday){this.birthday = birthday; }


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

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        if (books == null)
            this.books = null;
        else
            this.books = books;

    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + DateFormat.getDateInstance(DateFormat.SHORT).format(birthday) + '\'' +
                ", books=" + books +
                '}';
    }
}
