package be.hers.info.persea.request.address;

import lombok.Getter;

@Getter
public class CreateAddressRequest {
    private String number;
    private String street;
    private String city;
    private String postalCode;
    private String complement;
    private String country;
}
