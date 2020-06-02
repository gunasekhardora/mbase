package org.kgsd.mbase.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.kgsd.mbase.common.Constants;
import org.kgsd.mbase.common.util.JacksonUtil;
import org.kgsd.mbase.model.User;
import org.kgsd.mbase.service.UserService;
import ro.pippo.controller.Controller;
import ro.pippo.controller.DELETE;
import ro.pippo.controller.GET;
import ro.pippo.controller.POST;
import ro.pippo.core.HttpConstants;

import java.util.List;

@Log4j2
public class UserController extends Controller {
    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST("/api/user")
    public void createUser() {
        String userBody = getRequest().getBody();
        try {
            User user = JacksonUtil.getMapper().readValue(userBody, User.class);
            userService.createUser(user);
            getResponse().status(HttpConstants.StatusCode.OK);
            getResponse().json().send("Created user successfully!!");
        } catch (Exception e) {
            log.error("Failed to create user:" + userBody, e);
            getResponse().status(HttpConstants.StatusCode.INTERNAL_ERROR);
            getResponse().json().send("Failed to create user " + userBody);
        }
    }

    @GET("/api/user/{id}")
    public void getUser() {
        String userID = getRequest().getParameter(Constants.USER_ID).toString();
        try {
            User user = userService.getUser(userID);
            getResponse().status(HttpConstants.StatusCode.OK);
            getResponse().json().send(new Gson().toJson(user));
        } catch (Exception e) {
            log.error("Failed to get user for id " + userID, e);
            getResponse().status(HttpConstants.StatusCode.INTERNAL_ERROR);
            getResponse().json().send("Failed to get user for id " + userID);
        }
    }

    @GET("/api/users")
    public void getUsers() {
        try {
            List<User> users = userService.getUsers();
            getResponse().status(HttpConstants.StatusCode.OK);
            getResponse().json().send(new Gson().toJson(users));
        } catch (Exception e) {
            log.error("Failed to list users ", e);
            getResponse().send(HttpConstants.StatusCode.INTERNAL_ERROR);
            getResponse().json().send("List users failed!!", e);
        }
    }

    @DELETE("/api/user/{id}")
    public void deleteUser() {
        String userID = getRequest().getParameter(Constants.USER_ID).toString();
        try {
            userService.deleteUser(userID);
            getResponse().status(HttpConstants.StatusCode.OK);
            getResponse().json().send("Deleted user successfully!!");
        } catch (Exception e) {
            log.error("Failed to delete user:" + userID, e);
            getResponse().status(HttpConstants.StatusCode.INTERNAL_ERROR);
            getResponse().json().send("Failed to create user " + userID);
        }
    }
}
