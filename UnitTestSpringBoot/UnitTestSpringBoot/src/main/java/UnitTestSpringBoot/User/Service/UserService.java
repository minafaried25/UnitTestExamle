package UnitTestSpringBoot.User.Service;

import UnitTestSpringBoot.User.Entity.DTO.UserCreateDTO;
import UnitTestSpringBoot.User.Entity.User;
import UnitTestSpringBoot.User.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User create(UserCreateDTO userCreateDTO){

        System.out.println("Creating user: " + userCreateDTO.name());
        return userRepository.save(userCreateDTO.toUser());
    }
}
