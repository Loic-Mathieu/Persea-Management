package be.hers.info.persea.dto.address;

import be.hers.info.persea.model.address.Address;
import lombok.Getter;

@Getter
public class AddressDto {

    private final long id;

    private final String number;
    private final String street;
    private final String city;
    private final String postalCode;
    private final String complement;
    private final String country;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.number = address.getNumber();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.complement = address.getComplement();
        this.country = address.getCountry();
    }
}
