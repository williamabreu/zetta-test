package net.williamabreu.zetta.models;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Modelagem da PESSOA abstrata.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Check(constraints = "sex IN ('M' ,'F')")
public abstract class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cpf;
    private String name;
    private Date birthdate;
    private char sex;

    public Person() {

    }

    public Person(String cpf, String name, Date birthdate, char sex) {
        this.cpf = cpf;
        this.name = name;
        this.birthdate = birthdate;
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public char getSex() {
        return sex;
    }
}
