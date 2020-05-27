package org.kgsd.mbase;

import com.google.inject.AbstractModule;
import org.kgsd.mbase.controller.ControllerModule;

public class MModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ControllerModule());
    }
}
