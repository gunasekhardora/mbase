package org.kgsd.mbase;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.extern.log4j.Log4j2;
import ro.pippo.controller.Controller;
import ro.pippo.controller.ControllerApplication;
import ro.pippo.core.Pippo;

import java.util.Set;

import static org.kgsd.mbase.common.Constants.APP_PORT;

@Log4j2
public class MBase extends ControllerApplication {
    @Inject
    public MBase(Set<Controller> controllers) {
        controllers.forEach(this::addControllers);
    }

    public static void main(String[] args) {
        System.out.println("\n-----------------------------------------");
        System.out.println("\n                MBASE                    ");
        System.out.println("\n-----------------------------------------");

        try {
            Injector injector = Guice.createInjector(new MModule());
            MBase mBase = injector.getInstance(MBase.class);
            (new Pippo(mBase)).start(APP_PORT);
        } catch (Exception e) {
            log.error("Error in initializing MBase App, exiting...", e);
            System.out.println("Error in initializing MBase App, exiting... " + e);
            System.exit(1);
        }
    }
}
