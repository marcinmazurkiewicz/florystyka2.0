package io.dudek.florystyka.controller;

import io.dudek.florystyka.domain.User;
import io.dudek.florystyka.domain.UserDTO;
import io.dudek.florystyka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepository repository;
    private PasswordEncoder encoder;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserController(UserRepository repository, PasswordEncoder encoder, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.encoder = encoder;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping(value = "/account", method= RequestMethod.GET)
    public String viewAccount() {
        return "account";
    }

    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/signup", method=RequestMethod.GET)
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "sign_up";
    }

    @RequestMapping(value = "/signup", method=RequestMethod.POST)
    public String registerUser(@Valid UserDTO user, Errors errors, WebRequest request) {
        System.out.println(errors.hasErrors());
        if(errors.hasErrors()) {
            return "sign_up";
        }
        User userDB = new User();
        userDB.setMail(user.getMail());
        userDB.setPassword(encoder.encode(user.getPassword()));
        userDB = repository.save(userDB);


        return "redirect:/";
    }

    @RequestMapping(value = "/tst", method=RequestMethod.GET)
    public String testMail(@RequestParam("mail") String mail, Model model) {
        User user = repository.findByMail(mail);
        System.out.println(user);
        return "/";
    }



}
