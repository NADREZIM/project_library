package liba.service.impl;


import liba.Transformer.Transformer;
import liba.dao.api.userDAO;
import liba.dao.implDB.UserDAOImplDB;
import liba.dto.BookDTO;
import liba.dto.UserDTO;
import liba.model.Book;
import liba.model.User;
import liba.service.api.UserService;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public class UserServiceImpl implements UserService {


    @Override
    public void connectUserWithBook(long idUser, long idBook) {
        userDAO userDAOCurrent = new UserDAOImplDB();
        userDAOCurrent.connectUserWithBook(idUser, idBook);
    }

    @Override
    public UserDTO findUserByNameWithBook(String name) {
        userDAO userDAOCurrent = new UserDAOImplDB();
        UserDTO userDTO;
        User user = userDAOCurrent.findUserByNameWithBook(name);
        List<Book> listBook = user.getBooks();
        List<BookDTO> ListBookDTO = Transformer.USER_GET_BOOKS_DTO(listBook);
        userDTO = Transformer.TRANSFORM_USER_TO_USERDTO(user);
        userDTO.setBooks(ListBookDTO);
        return userDTO;

    }

    @Override
    public UserDTO takeUser(String login, String password) {
        UserDAOImplDB userDAOImplDB = new UserDAOImplDB();
        User user = userDAOImplDB.takeUser(login, password);
        return Transformer.TRANSFORM_USER_TO_USERDTO(user);
    }

    @Override
    public UserDTO addUser(UserDTO userDTOs) throws NullPointerException {
        User user = Transformer.TRANSFORM_USERDTO_TO_USER(userDTOs);
        userDAO userDAOCurrent = new UserDAOImplDB();
        User tempUser = userDAOCurrent.addUser(user);
        return Transformer.TRANSFORM_USER_TO_USERDTO(tempUser);
    }

    @Override
    public void deleteUser(long id) throws NullPointerException {
        userDAO userDAOCurrent = new UserDAOImplDB();
        userDAOCurrent.deleteUser(id);

    }

    @Override
    public UserDTO updateUser(long id, UserDTO userDTOs) throws NullPointerException {
        User user = Transformer.TRANSFORM_USERDTO_TO_USER(userDTOs);
        userDAO userDAOCurrent = new UserDAOImplDB();
        User tempUser = userDAOCurrent.updateUser(id, user);
        return Transformer.TRANSFORM_USER_TO_USERDTO(tempUser);

    }

    @Override
    public List<UserDTO> getAllUsers() {
        userDAO userDAOCurrent = new UserDAOImplDB();
        List<User> users = userDAOCurrent.getAllUsers();
        return Transformer.TRANSFORM_USERLIST_TO_USERDTOLIST(users);
    }
}
