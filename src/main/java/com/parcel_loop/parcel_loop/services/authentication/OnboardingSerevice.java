package com.parcel_loop.parcel_loop.services.authentication;

import com.parcel_loop.parcel_loop.models.AddressKeyFormat;
import com.parcel_loop.parcel_loop.models.ShipperProfile;
import com.parcel_loop.parcel_loop.models.request_objects.ups.XavRequest;

/*
 * 
 * Onboarding Form
 * Full Name:
 * Phone Number:
 * Email (optional):
 * Street Address:
 * Apt/Suite (optional):
 * City:
 * State:
 * Zip:
 * Country:
 * Residential or Business: [select]
 * Preferred Pickup Spot (optional):
 * 
 * 
 *  The primary objective is to build a profile for a user
 * 
 * 
            Responsibility:
            1️⃣ Validate user input
                Ensure address, phone, and name are valid (non-empty, formatted).
            2️⃣ Create or update user profile
            Save ShipperProfile (name, phone, address) to DB.
            3️⃣ Optional verification
            Call UPS Address Validation API or Google Maps Geocoding to confirm location.
            4️⃣ Link to user
            Attach the shipper info to the authenticated user’s record.

 */

public class OnboardingSerevice {
    
    public boolean isAddressValid(ShipperProfile shipperProfile) {
        /*
            {
                "XAVRequest": {
                    "AddressKeyFormat": 
                        {
                            "ConsigneeName": "RITZ CAMERA CENTERS-1749", ✅
                            "BuildingName": "Innoplex", ✅
                            "AddressLine": [],✅
                            "Region": "ROSWELL,GA,30076-1521", ✅
                            "PoliticalDivision2": "ALISO VIEJO", ✅
                            "PoliticalDivision1": "CA", ✅
                            "PostcodePrimaryLow": "92656",
                            "PostcodeExtendedLow": "1521",
                            "Urbanization": "porto arundal",
                            "CountryCode": "US" ✅
                        }
                }

                make API request to UPS to /verify to validate address
        */
        var xavReq = buildVerifyAddressRequest_UPS(shipperProfile);
        //pass the xavReq to /verify API
        //UPS' response will be held in a field named 'ValidAddressIndicator' Y or N
        //var response = ??

    }

    private XavRequest buildVerifyAddressRequest_UPS(ShipperProfile shipperProfile) {
        XavRequest verifyAddressRequest = new XavRequest();
        AddressKeyFormat addressKeyFormat = new AddressKeyFormat();
        verifyAddressRequest.setAddressKeyFormat(addressKeyFormat);
        addressKeyFormat.setAddressLine(shipperProfile.getStreetAddressOne()); 
        addressKeyFormat.setPoliticalDivisionOne(shipperProfile.getState());
        addressKeyFormat.setPoliticalDivisionTwo(shipperProfile.getCity());
        addressKeyFormat.setPostCodePrimaryLow(shipperProfile.getZip());
        addressKeyFormat.setCountryCode(shipperProfile.getCountry());
        return verifyAddressRequest;
    }

    private String buildRegionFromAddress(ShipperProfile shipperProfile) {
        StringBuilder sb = new StringBuilder();
        sb.append(shipperProfile.getCity()).append(shipperProfile.getState()).append(shipperProfile.getZip());
        return sb.toString();
    }

}
