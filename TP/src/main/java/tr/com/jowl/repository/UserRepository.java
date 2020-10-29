package tr.com.jowl.repository;

import org.springframework.data.repository.CrudRepository;

import tr.com.jowl.entity.User;

/**
 * The UserRepository class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}