package io.github.adrbloch.FootballDB.controller;

import io.github.adrbloch.FootballDB.exception.FieldsNotMatchException;
import io.github.adrbloch.FootballDB.exception.ResourceAlreadyExistsException;
import io.github.adrbloch.FootballDB.model.user.User;
import io.github.adrbloch.FootballDB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }


    @GetMapping("/registration")
    public String addUserForm(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);

        return "user/registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") @Valid User userToSave,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors())
            return "user/registration";

        try {
            userService.createUser(userToSave);

        } catch (ResourceAlreadyExistsException e) {
            return addInfoToModelAfterExceptionAndReturnRegistrationView(model, e);

        } catch (FieldsNotMatchException e){
            return addInfoToModelAfterExceptionAndReturnRegistrationView(model, e);
        }

        return "user/login";
    }

    private String addInfoToModelAfterExceptionAndReturnRegistrationView(Model model, Exception e) {
        model.addAttribute("occurredUserException", true);
        model.addAttribute("exceptionUserMessage", e.getMessage());
        return "user/registration";
    }


}
