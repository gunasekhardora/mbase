package org.kgsd.mbase.service;

import com.google.inject.Inject;
import org.kgsd.mbase.dao.UserDao;
import org.kgsd.mbase.exception.UserDaoException;
import org.kgsd.mbase.exception.UserServiceException;
import org.kgsd.mbase.model.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(User user) throws UserServiceException {
        try {
            userDao.createUser(user);
        } catch (UserDaoException e) {
            throw new UserServiceException(e);
        }
    }

    public void deleteUser(String userID) throws UserServiceException {
        try {
            userDao.deleteUser(userID);
        } catch (UserDaoException e) {
            throw new UserServiceException(e);
        }
    }

    public List<User> getUsers() throws UserServiceException {
        try {
            return userDao.getUsers();
        } catch (UserDaoException e) {
            throw new UserServiceException(e);
        }
    }

    public User getUser(String userID) throws UserServiceException {
        try {
            return userDao.getUser(userID);
        } catch (UserDaoException e) {
            throw new UserServiceException(e);
        }
    }
}
