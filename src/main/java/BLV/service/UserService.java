package BLV.service;

import BLV.DAO.ConnectionDataBase;
import BLV.DAO.UserDAO;
import BLV.entity.User;
import BLV.exception.EmailAlreadyTakenException;
import BLV.exception.UserEmailCannotBeNullException;
import BLV.exception.UserIsNotSignedUpException;
import BLV.exception.UserNotFoundException;
import BLV.tools.PasswordTools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService extends UserDAO {

    static final Logger LOGGER = LogManager.getLogger();

    private final UserDAO userDao = new UserDAO(ConnectionDataBase.getInstance().getConnection());

    public UserService(Connection conn) {
        super(conn);
    }

    public static UserService getInstance() {
        return UserServiceHolder.instance;
    }

    public List<User> listUsers() {
        return userDao.findAll();
    }

    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public User checkPasswordAndReturnUser(String formEmail, String formPassword) throws UserIsNotSignedUpException {
        User user = getUserByEmail(formEmail);
        if (user == null) {
            throw new UserIsNotSignedUpException(formEmail);
        }
        String userPassword = user.getPassword();
        boolean isPasswordValid = PasswordTools.validatePassword(formPassword, userPassword);
        if (isPasswordValid) {
            return user;
        } else {
            return null;
        }
    }

    public void addUser(User user) throws EmailAlreadyTakenException {
        if (getUserByEmail(user.getEmail()) == null) {
            userDao.create(user);
        } else if (getUserByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyTakenException();
        }
    }

    public int checkRole(String email) {
        User user = getUserByEmail(email);
        return user.getAccessRightsFK();
    }

    public List<User> listUsersWithoutPassword() throws SQLException {
        List<User> users;
        users = userDao.findUsersForManagement();
        return users;
    }

    public void deleteUser(String email) throws UserEmailCannotBeNullException, UserNotFoundException {
        if (email == null) {
            throw new UserEmailCannotBeNullException();
        }
        if (getUserByEmail(email) == null) {
            throw new UserNotFoundException(email);
        }
        userDao.delete(getUserByEmail(email));
    }

    public void modifyUser(User user, boolean isOwner) {
        User prevUser = getUserByEmail(user.getEmail());
        if (!isOwner) {
            user.setPasswordWithoutEncoding(prevUser.getPassword());
        } else {
            //A voir ce que l'on ne souhaite pouvoir modifier
        }
        userDao.update(user);
    }

    public User getUserById(int userId) {
        return userDao.findById(userId);
    }

    private static class UserServiceHolder {
        private static final UserService instance = new UserService(ConnectionDataBase.getInstance().getConnection());
    }
}
