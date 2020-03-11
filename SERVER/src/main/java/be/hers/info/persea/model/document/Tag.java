package be.hers.info.persea.model.document;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "T_TAG")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTag", nullable = false)
    private Long id;

    @Column(name = "tagName", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tagProprety", nullable = false)
    private Proprety proprety;

    public Tag() {}

    public Tag(String name, Proprety proprety) {
        this.name = name;
        this.proprety = proprety;
    }
}
