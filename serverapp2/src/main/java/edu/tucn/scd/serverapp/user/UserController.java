package edu.tucn.scd.serverapp.user;

import edu.tucn.scd.serverapp.security.CredentialsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController // creates an instance of the current class
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping // maps the '/users' POST requests to this method
    @Operation(summary = "Save a new user") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public UserDTO create(@RequestBody UserDTO userDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userService.createUser(userDTO);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get a user by credentials")
    @GetMapping("/user")
    public UserDTO read(@ModelAttribute CredentialsDTO credentialsDTO) {
        return userService.getUser(credentialsDTO);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Delete a user by ID")
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable String id) {
        userService.deleteUser(id);
    }

}
