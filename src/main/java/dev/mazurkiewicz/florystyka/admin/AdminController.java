package dev.mazurkiewicz.florystyka.admin;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/admin")
@Secured({"ROLE_ADMIN"})
public class AdminController {

    @GetMapping
    public String test() {
        return "test ok";
    }
}
