package engine.repository;

import engine.businesslayer.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Override
    Optional<User> findById(String s);
}
