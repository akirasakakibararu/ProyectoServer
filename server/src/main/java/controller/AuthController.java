package controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pojos.Usuarios.Rol;
import service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam Rol rol) {
        return userService.registerUser(username, password,email,rol);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.authenticateUser(username, password);
    }
}
