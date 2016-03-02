package liba.dao.api;

import liba.model.Book;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by User on 06.01.2016.
 */
public interface bookDAO {
    public Book addBook(Book book);

    public void deleteBook(long id);

    public Book updateBook(long id, Book book);

    public List<Book> getAllBooks();

    public Book findBookByNameWithUser(String name);
}
