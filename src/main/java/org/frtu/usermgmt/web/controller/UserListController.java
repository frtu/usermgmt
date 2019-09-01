package org.frtu.usermgmt.web.controller;

import org.frtu.usermgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserListController {
    @Autowired
    private UserService usersService;

    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    public String contacts(Model model) {
        model.addAttribute("users", usersService.getList());
        return "user_list";
    }

}