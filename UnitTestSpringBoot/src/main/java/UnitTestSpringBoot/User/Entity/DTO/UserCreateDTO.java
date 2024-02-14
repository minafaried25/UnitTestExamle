package UnitTestSpringBoot.User.Entity.DTO;

import UnitTestSpringBoot.User.Entity.User;

public record UserCreateDTO(String name) {
    public User toUser(){
        return new User(this.name);
    }
}
