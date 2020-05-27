package org.kgsd.mbase.controller;

import ro.pippo.controller.Controller;
import ro.pippo.controller.GET;
import ro.pippo.core.HttpConstants;

public class MonitorController extends Controller {

    @GET("/api/monitor")
    public void monitor() {
        getResponse().status(HttpConstants.StatusCode.OK);
        getResponse().send("Alive!!!");
    }

    //TODO: Make sure downstream is available too
}
