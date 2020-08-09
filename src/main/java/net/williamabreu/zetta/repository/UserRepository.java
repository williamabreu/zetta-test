package net.williamabreu.zetta.repository;

import net.williamabreu.zetta.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
