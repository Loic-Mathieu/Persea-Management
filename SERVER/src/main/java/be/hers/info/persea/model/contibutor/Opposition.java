package be.hers.info.persea.model.contibutor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OPPOSITION")
public class Opposition extends Contributor {
}
