package be.hers.info.persea.model.address;

import be.hers.info.persea.model.PerseaAuditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "PERSEA_ADDRESS")
@Table(name = "T_ADDRESS")
public class Address extends PerseaAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAddress", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;

    private String complement;
}