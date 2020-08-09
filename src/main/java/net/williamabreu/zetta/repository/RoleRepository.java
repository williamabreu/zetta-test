package net.williamabreu.zetta.repository;

import net.williamabreu.zetta.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findById(long id);
}
