package liba.servlets.adminServlets;

import liba.dto.BookDTO;
import liba.dto.UserDTO;
import liba.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by User on 04.02.2016.
 */
public class UpdateBooksInDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookDTO> listBooksDto = new ArrayList<BookDTO>();
        List<Long> oldBookID = new ArrayList<Long>();

        List<Long> bookID = new ArrayList<Long>();
        List<String> bookAuthor = new ArrayList<String>();
        List<String> bookTitle = new ArrayList<String>();
        List<Integer> bookCount = new ArrayList<Integer>();

        BookServiceImpl bookService = new BookServiceImpl();

        String[] OldIDBookFromForm = req.getParameterValues("oldID");
        for (String s : OldIDBookFromForm) {
            Long id = Long.parseLong(s);
            oldBookID.add(id);
        }

        String[] IDBookFromForm = req.getParameterValues("bookID");
        if (IDBookFromForm==null){
            req.getRequestDispatcher("/logOut").forward(req,resp);
        }
        for (String s : IDBookFromForm) {
            long id;
            if (s.equals("")) {
                id = 0;
            } else {
                id = Long.parseLong(s);
            }
            bookID.add(id);
        }
        String[] authorBookFromForm = req.getParameterValues("bookAuthor");
        if (authorBookFromForm==null){
            req.getRequestDispatcher("/logOut").forward(req,resp);
        }
        Collections.addAll(bookAuthor, authorBookFromForm);

        String[] titleBookFromForm = req.getParameterValues("bookTitle");
        if (titleBookFromForm==null){
            req.getRequestDispatcher("/logOut").forward(req,resp);
        }
        Collections.addAll(bookTitle, titleBookFromForm);

        String[] countBookFromForm = req.getParameterValues("bookCount");
        if (countBookFromForm==null){
            req.getRequestDispatcher("/logOut").forward(req,resp);
        }
        for (String s : countBookFromForm) {
            int count;
            if (s.equals("")){
            count = 0;
        }else {
            count = Integer.parseInt(s);
            }
            bookCount.add(count);
        }

        Iterator itrID = bookID.iterator();
        Iterator itrAuthor = bookAuthor.iterator();
        Iterator itrTitle = bookTitle.iterator();
        Iterator itrCount = bookCount.iterator();
        while (itrID.hasNext() & itrAuthor.hasNext() & itrTitle.hasNext() & itrCount.hasNext()) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId((Long) itrID.next());
            bookDTO.setAuthor((String) itrAuthor.next());
            bookDTO.setTitle((String) itrTitle.next());
            bookDTO.setCount((Integer) itrCount.next());
            listBooksDto.add(bookDTO);
        }

        Iterator itrListBooksDto = listBooksDto.iterator();
        Iterator itrOldBookID = oldBookID.iterator();
        while (itrOldBookID.hasNext() & itrListBooksDto.hasNext()) {
            bookService.updateBook((Long) itrOldBookID.next(), (BookDTO) itrListBooksDto.next());
        }
        req.getRequestDispatcher("/bookAmount").forward(req, resp);
    }
}
