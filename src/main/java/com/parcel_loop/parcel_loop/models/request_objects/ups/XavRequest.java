package com.parcel_loop.parcel_loop.models.request_objects.ups;

import com.parcel_loop.parcel_loop.models.AddressKeyFormat;

public class XavRequest {
    private AddressKeyFormat addressKeyFormat;

    public AddressKeyFormat getAddressKeyFormat() {
        return addressKeyFormat;
    }

    public void setAddressKeyFormat(AddressKeyFormat addressKeyFormat) {
        this.addressKeyFormat = addressKeyFormat;
    }
}
