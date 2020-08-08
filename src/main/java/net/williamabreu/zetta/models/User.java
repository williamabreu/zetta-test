package net.williamabreu.zetta.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Modelagem do USUÁRIO.
 */
@Entity
public class User extends Person implements Serializable {
    private Date registerDate;

    @ManyToMany
    private List<Profile> profiles;

    @ManyToOne
    private Role role;

    public User() {
        super();
    }

    public User(String cpf, String name, Date birthdate, char sex, Date registerDate, List<Profile> profiles, Role role) {
        super(cpf, name, birthdate, sex);
        this.registerDate = registerDate;
        this.profiles = profiles;
        this.role = role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile);
    }

    public void removeProfile(Profile profile) {
        this.profiles.remove(profile);
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public List<Profile> getProfiles() {
        return Collections.unmodifiableList(profiles);
    }

    public Role getRole() {
        return role;
    }
}
