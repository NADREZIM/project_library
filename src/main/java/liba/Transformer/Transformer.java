package liba.Transformer;

import liba.dto.BookDTO;
import liba.dto.ReportDTO;
import liba.dto.UserDTO;
import liba.model.Book;
import liba.model.Report;
import liba.model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public class Transformer {
    public static UserDTO TRANSFORM_USER_TO_USERDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLogin(user.getLogin());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    public static User TRANSFORM_USERDTO_TO_USER(UserDTO userDTO) throws NullPointerException {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        user.setBirthday(userDTO.getBirthday());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public static List<UserDTO> TRANSFORM_USERLIST_TO_USERDTOLIST(List<User> users) {
        List<UserDTO> ListUserDTO = new ArrayList<UserDTO>();
        for (User oneUser : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(oneUser.getId());
            userDTO.setName(oneUser.getName());
            userDTO.setLogin(oneUser.getLogin());
            userDTO.setBirthday(oneUser.getBirthday());
            userDTO.setPassword(oneUser.getPassword());
            ListUserDTO.add(userDTO);
        }
        return ListUserDTO;
    }

    public static BookDTO TRANSFORM_BOOK_TO_BOOKDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setCount(book.getCount());
        return bookDTO;
    }

    public static Book TRANSFORM_BOOKDTO_TO_BOOK(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setCount(bookDTO.getCount());
        book.setId(bookDTO.getId());
        return book;
    }

    public static List<BookDTO> TRANSFORM_BOOKLIST_TO_BOOKDTOLIST(List<Book> books) {
        List<BookDTO> BookDTOList = new ArrayList<BookDTO>();
        for (Book oneBook : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(oneBook.getId());
            bookDTO.setCount(oneBook.getCount());
            bookDTO.setTitle(oneBook.getTitle());
            bookDTO.setAuthor(oneBook.getAuthor());
            BookDTOList.add(bookDTO);
        }
        return BookDTOList;
    }

    public static ReportDTO TRANSFORM_REPORT_TO_REPORTDTO(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setBook(report.getBook());
        reportDTO.setUser(report.getUser());
        reportDTO.setRentBook(report.getRentBook());
        reportDTO.setReturnBook(report.getReturnBook());

        return reportDTO;
    }

    public static Report TRANSFORM_REPORTDTO_TO_REPORT(ReportDTO reportDTO) {
        Report report = new Report();
        report.setId(reportDTO.getId());
        report.setUser(reportDTO.getUser());
        report.setBook(reportDTO.getBook());
        report.setRentBook(reportDTO.getRentBook());
        report.setReturnBook(reportDTO.getReturnBook());

        return report;
    }

    public static List<ReportDTO> TRANSFORM_REPORTLIST_TO_REPORTDTOLIST(List<Report> reports) {
        List<ReportDTO> reportList = new ArrayList<ReportDTO>();
        for (Report oneReport : reports) {
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setId(oneReport.getId());
            reportDTO.setUser(oneReport.getUser());
            reportDTO.setBook(oneReport.getBook());
            reportDTO.setRentBook(oneReport.getRentBook());
            reportDTO.setReturnBook(oneReport.getReturnBook());
            reportList.add(reportDTO);
        }
        return reportList;
    }

    public static List<BookDTO> USER_GET_BOOKS_DTO(List<Book> books) {
        BookDTO bookDTO;
        Book book;
        List<BookDTO> listBookDTO = new ArrayList<BookDTO>();
        Iterator itr = books.iterator();
        while (itr.hasNext()) {
            book = (Book) itr.next();
            bookDTO = Transformer.TRANSFORM_BOOK_TO_BOOKDTO(book);
            listBookDTO.add(bookDTO);
        }
        return listBookDTO;
    }

    public static List<UserDTO> BOOK_GET_USERS_DTO(List<User>users) {
        User user;
        UserDTO userDTO;
        List<UserDTO>listUser = new ArrayList<UserDTO>();
        Iterator itr = users.iterator();
        while (itr.hasNext()){
            user = (User) itr.next();
            userDTO = Transformer.TRANSFORM_USER_TO_USERDTO(user);
            listUser.add(userDTO);
        }
        return listUser;
    }
}