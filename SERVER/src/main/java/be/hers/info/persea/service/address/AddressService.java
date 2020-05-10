package be.hers.info.persea.service.address;

import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.request.address.CreateAddressRequest;

public interface AddressService {
    long createAddress(CreateAddressRequest body) throws BadRequestException;
}
