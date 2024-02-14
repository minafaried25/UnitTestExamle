package UnitTestSpringBoot.User.Controller;

import UnitTestSpringBoot.User.Entity.DTO.UserCreateDTO;
import UnitTestSpringBoot.User.Entity.User;
import UnitTestSpringBoot.User.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public List<User> getAll(){
       return userService.getAll();
    }
    @PostMapping("create")
    public User create(@RequestBody UserCreateDTO userCreateDTO){
        return userService.create(userCreateDTO);
    }
}
