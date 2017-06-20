package ru.alsem.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.alsem.springmvc.extjs.ExtData;
import ru.alsem.springmvc.extjs.ExtResponse;
import ru.alsem.springmvc.model.User;
import ru.alsem.springmvc.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "/users/view", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ExtResponse getUsers() {

        ExtData responseData = new ExtData();
        List<User> users = userService.getUserList();
        responseData.add(users);
        responseData.setSuccess(true);
        responseData.setMessage("records successfully fetched! Total: " + users.size());
        return responseData;
    }

    //new users
    @RequestMapping(value = "/users/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ExtResponse create(@RequestBody User[] users) {
        ExtData responseData = new ExtData();
        List<User> addedUsers = userService.createUsers(users);
        responseData.add(addedUsers);
        responseData.setSuccess(true);
        responseData.setMessage("records successfully added! Total: " + addedUsers.size());
        return responseData;
    }

    //update users
    @RequestMapping(value = "/users/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ExtResponse update(@RequestBody User[] users) {
        ExtData responseData = new ExtData();
        List<User> addedUsers = userService.updateUsers(users);
        responseData.add(addedUsers);
        responseData.setSuccess(true);
        responseData.setMessage("records successfully updated! Total: " + addedUsers.size());
        return responseData;
    }

    //delete users
    @RequestMapping(value = "/users/destroy", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ExtResponse delete(@RequestBody long[] uids) {
        userService.deleteUsers(uids);
        ExtData responseData = new ExtData();
        responseData.add(uids.length);
        responseData.setSuccess(true);
        responseData.setMessage("items successfully removed!");
        return responseData;
    }


}
