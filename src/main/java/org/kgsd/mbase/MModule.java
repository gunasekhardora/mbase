package org.kgsd.mbase;

import com.google.inject.AbstractModule;
import org.kgsd.mbase.controller.ControllerModule;
import org.kgsd.mbase.dao.DaoModule;

public class MModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ControllerModule());
        install(new DaoModule());
    }
}
