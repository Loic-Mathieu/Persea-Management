package be.hers.info.persea.model.contibutor;

import be.hers.info.persea.model.courtCase.CourtCase;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("OPPOSITION")
public class Opposition extends Contributor {
    @ManyToMany()
    @JoinTable(
            name = "TJ_OPPOSITION_CASE",
            joinColumns={@JoinColumn(name="refOpposition", referencedColumnName="idContributor")},
            inverseJoinColumns={@JoinColumn(name="refCase", referencedColumnName="idCase")}
    )
    private List<CourtCase> courtCases;
}
