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

    //private static final String BASE_VERIFY_ADDRESS_URL = "https://apis-tem.usps.com/addresses/v3/address";
  private static final String BASE_VERIFY_ADDRESS_URL =
    "https://secure.shippingapis.com/ShippingAPI.dll" +
    "?API=Verify&XML=" +
    "<AddressValidateRequest USERID=\"parcel_loop\">" +
        "<Address ID=\"0\">" +
            "<Address1></Address1>" +
            "<Address2>123 Main Street</Address2>" +
            "<City>Atlanta</City>" +
            "<State>GA</State>" +
            "<Zip5>30309</Zip5>" +
            "<Zip4></Zip4>" +
        "</Address>" +
    "</AddressValidateRequest>";

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> verifyAddressUsps(AddressKeyFormat userAddress) {
        String verifyAddressFinalUrl = buildVerifyAddressRequest(userAddress);
        HttpHeaders headers = createUspsVerifyHttpHeaders();
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

    private HttpHeaders createUspsVerifyHttpHeaders() {
        Map<String, String> customHeaders = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("eyJraWQiOiJIdWpzX2F6UnFJUzBpSE5YNEZIRk96eUwwdjE4RXJMdjNyZDBoalpNUnJFIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJlbnRpdGxlbWVudHMiOltdLCJzdWIiOiIiLCJjcmlkIjoiIiwic3ViX2lkIjoiIiwicm9sZXMiOltdLCJwYXltZW50X2FjY291bnRzIjoiIiwiaXNzIjoiaHR0cHM6Ly9hcGlzLXRlbS51c3BzLmNvbSIsImNvbnRyYWN0cyI6e30sImF1ZCI6WyJwYXltZW50cyIsInByaWNlcyIsInN1YnNjcmlwdGlvbnMtdHJhY2tpbmciLCJvcmdhbml6YXRpb25zIl0sImF6cCI6IkFKZWhXR1pZSHFxRGpKSmpqY05TMG9IS3hWcHFhVkVNR1dteHJ1aEljME1ZcXFZdyIsIm1haWxfb3duZXJzIjpbXSwic2NvcGUiOiJhZGRyZXNzZXMiLCJjb21wYW55X25hbWUiOiIiLCJleHAiOjE3NjMwMTgwOTgsImlhdCI6MTc2Mjk4OTI5OCwianRpIjoiN2U5NDBkM2YtODczZi00ODdhLTgyNWMtNjM4OTE5YmUxMjNlIn0.Nk3IotdITolWIZGPKVP-7GWzkILjVzc-AJTMyKEpFrubNJ5j9kV6UduuO6fY4JXT7_DqGIxcsKEVIrusYqQe6Fer61HbE6uV_hQct6thb_DsWL9rDSBX9Nmq8Qc3HxqLl1tYh46ZnOnyl_XK0vfLw--rVeXDLIX-RHhl9OTE31vgiV-ZDYN-DfmOopTCc07O0lm4CN4rMp-hExxIuPVMkC044vAVvU0CuvoaoZCU1auSz0NJCk99QhrwlmLMDuFsPebcBRzMXfvZNckQtrp6iQ-wTKllhqvHpEnZjKswkYDfVb3J9tI5VW0AZG-VZG-tPBb-FuSz6MAoAg5x-lezww");
        if (customHeaders != null) {
            Set<String> headerKeys = customHeaders.keySet();
            for (String headerKey : headerKeys) {
                headers.add(headerKey, customHeaders.get(headerKey));
            }
        }
        return headers;
    }
}
