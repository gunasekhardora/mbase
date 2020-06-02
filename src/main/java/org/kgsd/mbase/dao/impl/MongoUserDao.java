package org.kgsd.mbase.dao.impl;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.kgsd.mbase.common.util.MongoUtil;
import org.kgsd.mbase.dao.UserDao;
import org.kgsd.mbase.exception.UserDaoException;
import org.kgsd.mbase.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Sorts.ascending;
import static org.kgsd.mbase.common.Constants.*;

@Log4j2
public class MongoUserDao implements UserDao {

    @Override
    public void createUser(User user) throws UserDaoException {
        try {
            MongoCollection<Document> collection = MongoUtil.getClient()
                    .getDatabase(MONGO_USER_DB).getCollection(MONGO_USER_COLLECTION);
            IndexOptions indexOptions = new IndexOptions().unique(true);
            collection.createIndex(Indexes.ascending(MONGO_USERID), indexOptions);
            Document document = new Document(MONGO_USERID, user.getId())
                    .append(MONGO_USERNAME, user.getName())
                    .append(MONGO_USERCOUNTRY, user.getCountry())
                    .append(MONGO_CREATION_DATE, new Date());
            collection.insertOne(document);
        } catch (MongoException e) {
            log.error("Unable to create user " + user.getId(), e);
            throw new UserDaoException("Unable to create user " + user.getId(), e);
        }

    }

    @Override
    public void deleteUser(String userID) throws UserDaoException {
        try {
            MongoCollection<Document> collection = MongoUtil.getClient()
                    .getDatabase(MONGO_USER_DB).getCollection(MONGO_USER_COLLECTION);
            collection.deleteOne(eq(MONGO_USERID, userID));
        } catch (MongoException e) {
            log.error("Unable to delete user " + userID, e);
            throw new UserDaoException("Unable to delete user " + userID, e);
        }
    }

    @Override
    public List<User> getUsers() throws UserDaoException {
        try {
            MongoCollection<Document> collection = MongoUtil.getClient()
                    .getDatabase(MONGO_USER_DB).getCollection(MONGO_USER_COLLECTION);
            MongoCursor<Document> cursor = collection.find()
                    .sort((ascending(MONGO_CREATION_DATE)))
                    .projection(fields(excludeId())).iterator();
            List<User> users = new ArrayList<>();
            while(cursor.hasNext()) {
                Document document = cursor.next();
                User user = new User();
                user.setId((String) document.get(MONGO_USERID));
                user.setName((String) document.get(MONGO_USERNAME));
                user.setCountry((String) document.get(MONGO_USERCOUNTRY));
                user.setDate((Date) document.get(MONGO_CREATION_DATE));
                users.add(user);
            }
            return users;
        } catch (IllegalArgumentException | MongoException e) {
            log.error("Unable to get users list ", e);
            throw new UserDaoException("Unable to get users list ", e);
        }
    }

    @Override
    public User getUser(String userID) throws UserDaoException {
        try {
            MongoCollection<Document> collection = MongoUtil.getClient()
                    .getDatabase(MONGO_USER_DB).getCollection(MONGO_USER_COLLECTION);
            Document document = collection.find(eq(MONGO_USERID, userID))
                    .projection(fields(excludeId())).first();
            if (document == null) {
                log.error("There is no user with the ID " + userID);
                throw new UserDaoException("There is no user with the ID " + userID);
            }
            User user = new User();
            user.setId((String) document.get(MONGO_USERID));
            user.setName((String) document.get(MONGO_USERNAME));
            user.setCountry((String) document.get(MONGO_USERCOUNTRY));
            user.setDate((Date) document.get(MONGO_CREATION_DATE));
            return user;
        } catch (IllegalArgumentException | MongoException e) {
            log.error("Unable to get user for id " + userID, e);
            throw new UserDaoException("Unable to get user for id " + userID, e);
        }
    }
}
