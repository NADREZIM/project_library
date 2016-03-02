package liba.dao.implDB;

import liba.connection.dataSource;
import liba.dao.api.bookDAO;
import liba.model.Book;
import liba.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public class BookDAOImplDB implements bookDAO {
    private static final String FIND_ALL_BOOKS = "select*from book";
    private static final String ADD_BOOK = "insert into book(author,title,count) values(?,?,?)";
    private static final String UPDATE_BOOK = "update book set author=IF(?='',author,?), title=IF(?='',title,?), count=IF(?=0,count,?), id_book=IF(?=0,id_book,?) where id_book=?";
    private static final String DELETE_BOOK = "delete from book where id_book=?";
    private static final String FIND_BOOK_BY_TITLE_WITH_USER = "select*from book inner join user_with_books on book.id_book = user_with_books.bookID left join user on user_with_books.userID = user.id_user where book.title = ? group by user.name";
    private static final String REMOVE_BOOKS = "delete from user_with_books where bookID = ? and rent_book = ?;";
    private static final String ADD_ONE_TO_BOOK_COUNT = "update book set count = count+1 where id_book = ?";
    private static final String CURRENT_DATE_FOR_BOOK_RETURNING = "update report set retbook = ? where id_book = ? and rent_book = ?";
    private static final String BOOK_COUNT_CHECKING = "select count from book where id_book = ?";


    @Override
    public Book addBook(Book book) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(ADD_BOOK);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getCount());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return book;
    }

    @Override
    public void deleteBook(long id) {
        Book book = new Book();
        book.setId(id);
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_BOOK);
            preparedStatement.setLong(1, book.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

    }

    @Override
    public Book updateBook(long id, Book book) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_BOOK);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getTitle());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setInt(5, book.getCount());
            preparedStatement.setInt(6, book.getCount());
            preparedStatement.setLong(7, book.getId());
            preparedStatement.setLong(8, book.getId());
            preparedStatement.setLong(9, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> book = new ArrayList<Book>();
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(FIND_ALL_BOOKS);
            while (rs.next()) {
                Book bk = new Book();
                bk.setId(rs.getInt(1));
                bk.setAuthor(rs.getString(2));
                bk.setTitle(rs.getString(3));
                bk.setCount(rs.getInt(4));
                book.add(bk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return book;
    }

    @Override
    public Book findBookByNameWithUser(String name) {
        Book book = new Book();
        List<User> listUser = new ArrayList<User>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(FIND_BOOK_BY_TITLE_WITH_USER);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                book.setId(rs.getInt("id_book"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                book.setCount(rs.getInt("count"));

                user.setId(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday"));
                listUser.add(user);
            }
            book.setUsers(listUser);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return book;
    }
    public void returnBook(long id, Timestamp currentDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(REMOVE_BOOKS);
            preparedStatement.setLong(1, id);
            preparedStatement.setTimestamp(2, currentDate);
            preparedStatement.execute();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(ADD_ONE_TO_BOOK_COUNT);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(CURRENT_DATE_FOR_BOOK_RETURNING);
            preparedStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.setLong(2, id);
            preparedStatement.setTimestamp(3,currentDate);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
    public int bookCountChecking(long bookID){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int count = 99999;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(BOOK_COUNT_CHECKING);
            preparedStatement.setLong(1, bookID);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return count;
    }
}
