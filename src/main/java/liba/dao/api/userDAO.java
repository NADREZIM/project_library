package liba.dao.api;

import liba.model.User;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public interface userDAO {
    public User addUser(User user);

    public void deleteUser(long id);

    public User updateUser(long id,User user);

    public List<User> getAllUsers();

    public void connectUserWithBook(long idUser,long idBook);

    public User findUserByNameWithBook(String name);
}
