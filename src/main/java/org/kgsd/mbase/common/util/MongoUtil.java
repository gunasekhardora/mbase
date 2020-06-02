package org.kgsd.mbase.common.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.kgsd.mbase.common.Constants;

public class MongoUtil {
    private static MongoClient mongoClient;

    public static MongoClient getClient() {
        if (mongoClient == null) {
            mongoClient = new MongoClient(new MongoClientURI(Constants.MONGO_URL));
        }
        return mongoClient;
    }
}
