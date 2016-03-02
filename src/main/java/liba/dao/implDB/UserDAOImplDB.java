package liba.dao.implDB;

import liba.connection.dataSource;
import liba.dao.api.userDAO;
import liba.model.Book;
import liba.model.User;

import java.sql.*;
import java.sql.Date;
import java.sql.Statement;
import java.util.*;

/**
 * Created by User on 06.01.2016.
 */
public class UserDAOImplDB implements userDAO {
    private static final String FIND_ALL_USERS = "select*from user";
    private static final String ADD_USER = "insert into user(name,login,password,birthday) values(?,?,?,?)";
    private static final String ADD_USER_TO_ROLE_DB = "insert into role_data_base (user_id) select user.id_user from user where name = ? and login = ? and password = ? and birthday = ?;";
    private static final String UPDATE_USER = "update user set name=?, login=?, password=?, birthday=?, id_user=? where id_user=?";
    private static final String DELETE_USER = "delete from user where id_user=?";
    private static final String USER_WITH_BOOK = "insert into user_with_books(userID,BookID,rent_book) values (?,?,?)";
    private static final String FIND_USER_BY_NAME_WITH_BOOK = "select * from user inner join user_with_books on user.id_user = user_with_books.userID\n" +
            "left join book on user_with_books.bookID = book.id_book where user.name = ? GROUP by book.title;";
    private static final String CREATE_REPORT = "insert into report(rent_book,id_book,author,title,count,id_user,name,login,password,birthday) SELECT uwb.rent_book, b.id_book,b.author,b.title,b.count-b.count+1,u.id_user,u.name,u.login,u.password,u.birthday from book as b, user as u, user_with_books as uwb  where b.id_book = ? and u.id_user = ? and uwb.rent_book = ? ; ";
    private static final String BOOK_COUNT_DECREMENT = "update book set count=count-1 where id_book=?";
    private static final String TAKE_ROLE_FROM_REQUEST = "select role from role_data_base where user_id=(select id_user from user where login = ? and password = ?)";



    @Override
    public User addUser(User user) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
            preparedStatement.execute();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(ADD_USER_TO_ROLE_DB);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
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
        return user;
    }

    @Override
    public void deleteUser(long id) {
        User user = new User();
        user.setId(id);
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setLong(1, user.getId());
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
    public User updateUser(long id, User user) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
            preparedStatement.setLong(5, user.getId());
            preparedStatement.setLong(6, id);
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
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<User>();
        java.sql.Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(FIND_ALL_USERS);
            while (rs.next()) {
                User us = new User();
                us.setId(rs.getInt(1));
                us.setName(rs.getString(2));
                us.setLogin(rs.getString(3));
                us.setPassword(rs.getString(4));
                us.setBirthday(rs.getDate(5));
                user.add(us);
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
        return user;
    }

    @Override
    public User findUserByNameWithBook(String name) {
        User user = new User();

        List<Book> listBook = new ArrayList<Book>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME_WITH_BOOK);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                user.setId(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday"));
                book.setId(rs.getInt("id_book"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                book.setCount(rs.getInt("count"));
                listBook.add(book);
            }
            user.setBooks(listBook);

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
        return user;
    }

    @Override
    public void connectUserWithBook(long idUser, long idBook) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(USER_WITH_BOOK);
            preparedStatement.setLong(1, idUser);
            preparedStatement.setLong(2, idBook);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(3, ts);
            preparedStatement.execute();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(BOOK_COUNT_DECREMENT);
            preparedStatement.setLong(1, idBook);
            preparedStatement.execute();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(CREATE_REPORT);
            preparedStatement.setLong(1, idBook);
            preparedStatement.setLong(2, idUser);
            preparedStatement.setTimestamp(3,ts);
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

    public String LoginReminder(String password) {
        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        String login = null;
        boolean checking;
        final String LOGIN_REMINDER = "select login from user where password = '" + password + "';";
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(LOGIN_REMINDER);
            checking = rs.first();
            if (checking) {
                login = rs.getString("login");
            } else {
                login = "!!!";
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
        return login;
    }

    public String PasswordReminder(String login) {
        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        String password = null;
        boolean checking;
        final String PASSWORD_REMINDER = "select password from user where login = '" + login + "';";
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(PASSWORD_REMINDER);
            checking = rs.first();
            if (checking) {
                password = rs.getString("password");
            } else {
                password = "!!!";
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
        return password;
    }

    public List<String> takeRole(String login, String password) {
        List<String> roles = new ArrayList<String>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(TAKE_ROLE_FROM_REQUEST);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                roles.add(role);
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
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return roles;
    }

    public boolean userEntranceChecking(String logIn, String loginPassword) {
        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        final String LOGIN_CHECK = "select login, password from user where login = '" + logIn + "' AND password='" + loginPassword + "';";
        boolean login = false;
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(LOGIN_CHECK);
            login = rs.first();
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
        return login;
    }

    public User takeUser(String logIn, String loginPassword) {
        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        final String LOGIN_CHECK_FROM_DB = "select* from user where login = '" + logIn + "' AND password='" + loginPassword + "';";
        User user = new User();
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(LOGIN_CHECK_FROM_DB);
            while (rs.next()) {
                user.setId(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday"));
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

        return user;
    }

    public long findUserID(String login, String password) {
        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        long id = 0;
        final String FIND_USER_ID = "select id_user from user where login = '" + login + "'AND password = '" + password + "';";
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(FIND_USER_ID);
            while (rs.next()) {
                id = rs.getInt("id_user");
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
        return id;
    }

    public Map<java.util.Date, Book> findUserRentBooks(long userID) {
        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        Map<java.util.Date, Long> bookId_rentDate = new HashMap<java.util.Date, Long>();
        Map<java.util.Date, Book> book_rentDate = new HashMap<java.util.Date, Book>();
        final String FIND_RENTS_BOOKS = "select bookID, rent_book from user_with_books where userID = '" + userID + "';";
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(FIND_RENTS_BOOKS);
            while (rs.next()) {
                long id = rs.getInt("bookID");
                java.util.Date timestamp = rs.getTimestamp("rent_book");
                bookId_rentDate.put(timestamp, id);
            }
            statement.close();
            rs.close();
            for (Map.Entry entry : bookId_rentDate.entrySet()) {
                java.util.Date dateFinal = (java.util.Date) entry.getKey();
                Long id = (Long) entry.getValue();
                final String FIND_BOOKS_BY_ID = "select id_book, author, title from book where id_book = '" + id + "';";
                statement = connection.createStatement();
                rs = statement.executeQuery(FIND_BOOKS_BY_ID);
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id_book"));
                    book.setAuthor(rs.getString("author"));
                    book.setTitle(rs.getString("title"));
                    book_rentDate.put(dateFinal, book);
                }
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
        return book_rentDate;
    }


}
