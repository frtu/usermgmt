/*
 * =============================================================================
 * 
 *   Copyright (c) 2011, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.frtu.usermgmt.web.controller;

import org.frtu.usermgmt.model.PaymentType;
import org.frtu.usermgmt.model.User;
import org.frtu.usermgmt.security.SecurityUserDetailsService;
import org.frtu.usermgmt.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserRegisterController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserRegisterController.class);

    @Autowired
    private UserService usersService;

    public UserRegisterController() {
        super();
    }

    @ModelAttribute("allTypes")
    public PaymentType[] populateTypes() {
        return new PaymentType[] { PaymentType.FREE, PaymentType.ALIPAY };
    }

    @RequestMapping({"/users/registration"})
    public String showRegistration(final User user) {
        return "user_registration";
    }

    @RequestMapping(value="/users/registration", params={"save"})
    public String register(final User user, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "user_registration";
        }

        final String clearPassword = user.getPassword();
        final String hashedPassword = SecurityUserDetailsService.hashPassword(clearPassword);
        user.setPassword(hashedPassword);
        usersService.save(user);

        return "user_registration_feedback";

//        model.clear();
//        return "redirect:/users/registration";
    }
}
