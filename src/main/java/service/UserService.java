package service;

import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(final Long userId) {
        return userRepository.findUserById(userId);
    }
}
