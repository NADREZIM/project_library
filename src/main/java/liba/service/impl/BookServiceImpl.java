package liba.service.impl;


import liba.Transformer.Transformer;
import liba.dao.api.bookDAO;
import liba.dao.implDB.BookDAOImplDB;
import liba.dto.BookDTO;
import liba.dto.UserDTO;
import liba.model.Book;
import liba.model.User;
import liba.service.api.BookService;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public class BookServiceImpl implements BookService {


    @Override
    public BookDTO findBookByNameWithUser(String name) {
        bookDAO bookDAOCurrent = new BookDAOImplDB();
        BookDTO bookDTO;
        Book book = bookDAOCurrent.findBookByNameWithUser(name);
        List<User> listUser  = book.getUsers();
        List<UserDTO>ListUserDTO = Transformer.BOOK_GET_USERS_DTO(listUser);
        bookDTO = Transformer.TRANSFORM_BOOK_TO_BOOKDTO(book);
        bookDTO.setUsers(ListUserDTO);
        return bookDTO;
    }


    @Override
    public BookDTO addBook(BookDTO bookDTOs) throws NullPointerException {
        Book book = Transformer.TRANSFORM_BOOKDTO_TO_BOOK(bookDTOs);
        bookDAO bookDAOCurrent =  new BookDAOImplDB();
        Book tempBook = bookDAOCurrent.addBook(book);
        return Transformer.TRANSFORM_BOOK_TO_BOOKDTO(tempBook);
    }

    @Override
    public void deleteBook(long id) throws NullPointerException {
        bookDAO bookDAOCurrent =  new BookDAOImplDB();
        bookDAOCurrent.deleteBook(id);
    }

    @Override
    public BookDTO updateBook(long id, BookDTO bookDTOs) throws NullPointerException {
        Book book = Transformer.TRANSFORM_BOOKDTO_TO_BOOK(bookDTOs);
        bookDAO bookDAOCurrent =  new BookDAOImplDB();
        Book tempBook = bookDAOCurrent.updateBook(id, book);
        return Transformer.TRANSFORM_BOOK_TO_BOOKDTO(tempBook);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        bookDAO bookDAOCurrent =  new BookDAOImplDB();
        List<Book> books = bookDAOCurrent.getAllBooks();
        return Transformer.TRANSFORM_BOOKLIST_TO_BOOKDTOLIST(books);
    }

}
