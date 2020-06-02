package org.kgsd.mbase.controller;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import ro.pippo.controller.Controller;

public class ControllerModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<Controller> multiBinder = Multibinder.newSetBinder(binder(), Controller.class);
        multiBinder.addBinding().to(MonitorController.class).asEagerSingleton();
        multiBinder.addBinding().to(UserController.class);
    }
}
