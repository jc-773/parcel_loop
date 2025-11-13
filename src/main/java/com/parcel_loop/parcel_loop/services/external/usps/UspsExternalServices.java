package com.parcel_loop.parcel_loop.services.external.usps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.parcel_loop.parcel_loop.models.AddressKeyFormat;

@Service
public class UspsExternalServices {

    private static final String BASE_VERIFY_ADDRESS_URL = "https://apis-tem.usps.com/addresses/v3/address";
//   private static final String BASE_VERIFY_ADDRESS_URL =
//     "https://secure.shippingapis.com/ShippingAPI.dll" +
//     "?API=Verify&XML=" +
//     "<AddressValidateRequest USERID=\"parcel_loop\">" +
//         "<Address ID=\"0\">" +
//             "<Address1></Address1>" +
//             "<Address2>123 Main Street</Address2>" +
//             "<City>Atlanta</City>" +
//             "<State>GA</State>" +
//             "<Zip5>30309</Zip5>" +
//             "<Zip4></Zip4>" +
//         "</Address>" +
//     "</AddressValidateRequest>";

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> verifyAddressUsps(AddressKeyFormat userAddress, String jwString) {
        String verifyAddressFinalUrl = buildVerifyAddressRequest(userAddress);
        HttpHeaders headers = createUspsVerifyHttpHeaders(jwString);
        //HttpEntity<String> entity = new HttpEntity<>("Bearer " + System.getenv("$USPS_JWT_TOKEN"));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        var response = restTemplate.exchange(verifyAddressFinalUrl, HttpMethod.GET, entity, String.class);
        var statusCode = response.getStatusCode();
        return (statusCode.is2xxSuccessful()) ? ResponseEntity.ok().body("Address validated") : ResponseEntity.badRequest().body("unable to verify address");
    }

    private String buildVerifyAddressRequest(AddressKeyFormat userAddress) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_VERIFY_ADDRESS_URL)
        .append("?streetAddress=").append(userAddress.getAddressLine())
        .append("&state=").append(userAddress.getPoliticalDivisionOne()).append("&city=")
        .append(userAddress.getPoliticalDivisionTwo()).append("&ZIPCode=").append(userAddress.getPostCodePrimaryLow());
        return sb.toString();
    }

    private HttpHeaders createUspsVerifyHttpHeaders(String jwString) {
        Map<String, String> customHeaders = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwString);
        if (customHeaders != null) {
            Set<String> headerKeys = customHeaders.keySet();
            for (String headerKey : headerKeys) {
                headers.add(headerKey, customHeaders.get(headerKey));
            }
        }
        return headers;
    }
}
