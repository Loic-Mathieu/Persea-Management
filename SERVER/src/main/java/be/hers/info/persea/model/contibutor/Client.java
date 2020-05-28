package be.hers.info.persea.model.contibutor;

import be.hers.info.persea.model.courtCase.CourtCase;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Contributor {
    @ManyToMany()
    @JoinTable(
            name = "TJ_CLIENT_CASE",
            joinColumns={@JoinColumn(name="refClient", referencedColumnName="idContributor")},
            inverseJoinColumns={@JoinColumn(name="refCase", referencedColumnName="idCase")}
    )
    private List<CourtCase> courtCases;
}
