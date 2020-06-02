package org.kgsd.mbase.dao;

import com.google.inject.AbstractModule;
import org.kgsd.mbase.dao.impl.MongoUserDao;

public class DaoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserDao.class).to(MongoUserDao.class).asEagerSingleton();
    }
}
