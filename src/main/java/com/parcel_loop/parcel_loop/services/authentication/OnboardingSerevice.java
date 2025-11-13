package com.parcel_loop.parcel_loop.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.parcel_loop.parcel_loop.models.AddressKeyFormat;
import com.parcel_loop.parcel_loop.models.ShipperProfile;
import com.parcel_loop.parcel_loop.models.request_objects.ups.XavRequest;
import com.parcel_loop.parcel_loop.models.response_objects.usps.AddressValidateResponse;
import com.parcel_loop.parcel_loop.services.external.usps.UspsExternalServices;

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
@Service
public class OnboardingSerevice {

    private UspsExternalServices uspsExternal;

    @Autowired
    public OnboardingSerevice(UspsExternalServices uspsExternal) {
        this.uspsExternal = uspsExternal;
    }

    public AddressValidateResponse isAddressValid(ShipperProfile shipperProfile, String jwtToken) {
        var xavReq = buildXavRequest_UPS(shipperProfile);
        var response = uspsExternal.verifyAddressUsps(xavReq.getAddressKeyFormat(), jwtToken);
        var addressResponse = parseJsonToAddressValidationResponse(response);
        return addressResponse;
    }

    private XavRequest buildXavRequest_UPS(ShipperProfile shipperProfile) {
        XavRequest verifyAddressRequest = new XavRequest();
        AddressKeyFormat addressKeyFormat = new AddressKeyFormat();
        verifyAddressRequest.setAddressKeyFormat(addressKeyFormat);
        addressKeyFormat.setAddressLine(shipperProfile.getStreetAddressOne());
        addressKeyFormat.setPoliticalDivisionOne(shipperProfile.getState());
        addressKeyFormat.setPoliticalDivisionTwo(shipperProfile.getCity());
        addressKeyFormat.setPostCodePrimaryLow(shipperProfile.getZip());
        // addressKeyFormat.setCountryCode(shipperProfile.getCountry());
        return verifyAddressRequest;
    }

    private String buildRegionFromAddress(ShipperProfile shipperProfile) {
        StringBuilder sb = new StringBuilder();
        sb.append(shipperProfile.getCity()).append(shipperProfile.getState()).append(shipperProfile.getZip());
        return sb.toString();
    }

    private AddressValidateResponse parseJsonToAddressValidationResponse(ResponseEntity<String> response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String cleanJson = response.getBody().trim();
            return objectMapper.readValue(cleanJson, AddressValidateResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse USPS JSON response", e);
        }
    }

}
