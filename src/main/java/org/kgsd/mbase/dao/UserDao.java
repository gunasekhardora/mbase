package org.kgsd.mbase.dao;

import org.kgsd.mbase.exception.UserDaoException;
import org.kgsd.mbase.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user) throws UserDaoException;

    void deleteUser(String userID) throws UserDaoException;

    List<User> getUsers() throws UserDaoException;

    User getUser(String userId) throws UserDaoException;
}
