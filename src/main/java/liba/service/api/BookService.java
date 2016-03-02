package liba.service.api;

import liba.dto.BookDTO;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public interface BookService {
    public BookDTO addBook(BookDTO bookDTOs) throws NullPointerException;

    public void deleteBook(long id) throws NullPointerException;

    public BookDTO updateBook(long id, BookDTO bookDTOs) throws NullPointerException;

    public List<BookDTO> getAllBooks();

    public BookDTO findBookByNameWithUser(String name);
}
