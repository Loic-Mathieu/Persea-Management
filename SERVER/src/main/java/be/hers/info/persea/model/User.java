package be.hers.info.persea.model;

import be.hers.info.persea.model.contibutor.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_PERSEA_USER")
// TODO implements 'UserDetails'
// https://blog.invivoo.com/securiser-application-spring-boot-spring-security/
public class User {

    public User() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private long id;

    @Column(nullable = false)
    private Role role;

    @Column(unique=true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "refAddress", nullable = false)
    private Address address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique=true)
    private String mail;
}
