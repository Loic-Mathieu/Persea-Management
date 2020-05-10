package be.hers.info.persea.model.contibutor;

import be.hers.info.persea.model.PerseaAuditable;
import be.hers.info.persea.model.address.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "contributor_type")
@Table(name = "T_CONTRIBUTOR")
public abstract class Contributor extends PerseaAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContributor", nullable = false)
    private Long id;

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

    @Column(nullable = false)
    private String mail;

    public Contributor() { }

    public Contributor(String lastName, String firstName, Gender gender, Address address, String phoneNumber, String mail) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;

        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }
}
