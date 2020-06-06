package org.kgsd.mbase.common.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static org.kgsd.mbase.common.Constants.MONGO_URL;

public class MongoUtil {
    private static MongoClient mongoClient;

    public static MongoClient getClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(MONGO_URL);
        }
        return mongoClient;
    }
}
