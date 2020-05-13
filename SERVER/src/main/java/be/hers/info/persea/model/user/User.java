package be.hers.info.persea.model.user;

import be.hers.info.persea.model.PerseaAuditable;
import be.hers.info.persea.model.address.Address;
import be.hers.info.persea.model.contibutor.Gender;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.representation.LegalRepresentation;
import be.hers.info.persea.model.time.TimePeriod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "PERSEA_USER")
@Table(name = "T_PERSEA_USER")
// TODO implements 'UserDetails'
// https://blog.invivoo.com/securiser-application-spring-boot-spring-security/
public class User extends PerseaAuditable {

    public User() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
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

    @OneToMany(mappedBy = "owner")
    private List<TimePeriod> timePeriods;

    @ManyToMany
    @JoinTable(
            name = "TJ_USER_CASE",
            joinColumns={@JoinColumn(name="refUser", referencedColumnName="idUser")},
            inverseJoinColumns={@JoinColumn(name="refCase", referencedColumnName="idCase")}
    )
    private List<CourtCase> courtCases;
}
