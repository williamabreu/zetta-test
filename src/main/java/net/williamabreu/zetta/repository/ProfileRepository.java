package net.williamabreu.zetta.repository;

import net.williamabreu.zetta.models.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    Profile findById(long id);
}
