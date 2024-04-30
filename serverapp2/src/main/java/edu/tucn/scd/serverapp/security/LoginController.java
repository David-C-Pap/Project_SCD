package edu.tucn.scd.serverapp.security;

import edu.tucn.scd.serverapp.exceptions.UnauthorizedException;
import edu.tucn.scd.serverapp.user.UserController;
import edu.tucn.scd.serverapp.user.UserDTO;
import edu.tucn.scd.serverapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Radu Miron
 * @version 1
 */
@RestController // creates an instance of the current class
@RequestMapping("/login") // maps the requests starting with '/login' to this controller
public class LoginController {
    private final UserController userController;
    @Autowired
    public LoginController(UserController userController) {
        this.userController = userController;
    }
    @PostMapping
    public JwtTokenDTO login(@RequestBody CredentialsDTO credentialsDTO) {
        // todo: validate credentials; you need to create a User entity and save users in DB;
        // the users should have at least the 'username' and the 'password' fields;
        // the password should be encrypted with BCrypt before saving it into the DB.
        // at the moment this endpoint returns a valid token for any credentials;
        // return the token if credentials are valid; throw UnauthorizedException otherwise
        UserDTO user = userController.read(credentialsDTO);
        if (user != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword())) {
                JwtTokenDTO jwtTokenDTO = new JwtTokenDTO();
                jwtTokenDTO.setToken(JwtUtil.generateToken(credentialsDTO.getUsername()));
                return jwtTokenDTO;
            } else
                throw new UnauthorizedException("Credentialele nu sunt valide!");
        }
       throw new UnauthorizedException("Credentialele nu sunt valide!");
    }
}
