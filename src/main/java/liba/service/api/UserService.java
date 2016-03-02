package liba.service.api;

import liba.dto.UserDTO;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public interface UserService {
    public UserDTO addUser(UserDTO userDTOs) throws NullPointerException;

    public void deleteUser(long id) throws NullPointerException;

    public UserDTO updateUser(long id,UserDTO userDTOs) throws NullPointerException;

    public List<UserDTO> getAllUsers();

    public void connectUserWithBook(long idUser,long idBook);

    public UserDTO findUserByNameWithBook(String name);

    public UserDTO takeUser(String login,String password);
}
