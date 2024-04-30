package edu.tucn.scd.serverapp.user;

import edu.tucn.scd.serverapp.exceptions.NotFoundException;
import edu.tucn.scd.serverapp.security.CredentialsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Optional;

@Service // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public UserDTO createUser(UserDTO userDTO) {
        return userMapper.usertoDto(userRepository.save(userMapper.dtoToUser(userDTO)));
    }


    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public UserDTO getUser(CredentialsDTO credentialsDTO) {
        User user = userRepository.findByUsername(credentialsDTO.getUsername());
        if (user != null) {
            return userMapper.usertoDto(user);
        } else throw new NotFoundException("Utilizator negasit!");
    }

    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}
