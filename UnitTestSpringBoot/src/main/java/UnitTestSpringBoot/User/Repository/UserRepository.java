package UnitTestSpringBoot.User.Repository;

import UnitTestSpringBoot.User.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
