package model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "first_name")
    private String firsName;

    @Column(name = "last_name")
    private String lastName;
}
