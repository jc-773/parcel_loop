package com.parcel_loop.parcel_loop.models.response_objects.ups;

import com.parcel_loop.parcel_loop.models.AddressKeyFormat;

public class AddressValidationResponse {
    private AddressKeyFormat addressKeyFormat;
    private String validAddressIndicator;

    public AddressKeyFormat getAddressKeyFormat() {
        return addressKeyFormat;
    }
    public void setAddressKeyFormat(AddressKeyFormat addressKeyFormat) {
        this.addressKeyFormat = addressKeyFormat;
    }
    public String getValidAddressIndicator() {
        return validAddressIndicator;
    }
    public void setValidAddressIndicator(String validAddressIndicator) {
        this.validAddressIndicator = validAddressIndicator;
    }
}