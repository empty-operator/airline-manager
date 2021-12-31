package org.leniv.airlinemanager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leniv.airlinemanager.entity.User;
import org.leniv.airlinemanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/login")
    public String login() {
        log.trace("Login page has been accessed");
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        log.trace("Signup page has been accessed");
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String save(@ModelAttribute("user") User user) {
        log.trace("User successfully signed up");
        service.save(user);
        return "redirect:/login";
    }

}
