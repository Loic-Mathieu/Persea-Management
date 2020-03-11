package be.hers.info.persea.model.contibutor;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("LAWYER")
public class Lawyer extends Contributor {
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "T_LAWYER_CLIENT",
            joinColumns = @JoinColumn(name = "refLawyer")
    )
    private List<Client> clients;
}
