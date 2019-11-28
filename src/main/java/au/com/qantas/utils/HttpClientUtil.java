package au.com.future-airlines.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import net.thucydides.core.Thucydides;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by boses on 4/07/2017.
 */
public class HttpClientUtil {

    private static HttpClient httpclient;
    private static HttpResponse httpResponse;
    private static String authenticate_Part1;
    private static String authenticate_Part2;
    private static String api_part1;
    private static String api_part2;




    public static String getAccessToken(String endPointAuth_part1, String endPointAuth_part2, String memberId, String lastName, String pin) throws Exception {
        final RestTemplate template = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        headers.add(HttpHeaders.AUTHORIZATION, "Basic bWlwc3VpOmZnbGluNDVvOXUz");
        headers.add(HttpHeaders.AUTHORIZATION, "Basic dGVzdEFFTTpzZGZzZGZAM2FzYSQzQCM=");
//        final HttpEntity<String> entity = new HttpEntity("{\"memberPIN\":\"1234\",\"memberSurname\":\"loytest\"}", headers);
        final HttpEntity<String> entity = new HttpEntity("{\"memberPIN\":\"" + pin + "\",\"memberSurname\":\"" + lastName + "\"}", headers);
//        final String result = template.postForObject("https://api.services-dev.future-airlinesloyalty.com/api/channel/identity/member/1900006766/authentication", entity, String.class);
        final String result = template.postForObject(endPointAuth_part1 + memberId + endPointAuth_part2, entity, String.class);
        final ObjectMapper mapper = new ObjectMapper();
        return String.class.cast(mapper.readValue(result, Map.class).get("accessToken"));
    }


    public static ResponseEntity<String> executeGet(String endPoint) {
        httpclient = new DefaultHttpClient();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer 2d2b3690aa1757421a3a6ea5116b37e3d55d567699449f0a1b1d58891d1ea1f6"); //
        headers.set("Authorization", "Bearer fadc6d9ba023acbcccc3dec8c2d78119c56b0c8cef31bcec20c50cca1e32c786");  //

        HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> models = null;
        int statusCode = 0;
        try {
            models = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity, String.class);
        } catch (HttpStatusCodeException exception) {
            statusCode = exception.getStatusCode().value();
            Thucydides.getCurrentSession().remove("models");
            System.out.println(exception.getResponseBodyAsString());
            Thucydides.getCurrentSession().put("ResponseCode",exception.getStatusCode().value());

            System.out.println("API responded with an error !!");
        }

        return models;
    }


    public static ResponseEntity<String> executePost(String endPoint, String payload, String accessToken) {
        // set headers
        httpclient = new DefaultHttpClient();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        // send request and parse result
        HttpEntity<?> httpEntity = new HttpEntity<Object>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, String.class);
        } catch (RestClientException e) {
            HttpStatusCodeException ex = (HttpStatusCodeException)e;
            e.printStackTrace();
            System.out.println(ex.getResponseBodyAsString());

        }

        return response;
    }

    public static ResponseEntity<String> executePostAndFetchResponse(String endPoint, String payload) {
        // set headers
        httpclient = new DefaultHttpClient();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // send request and parse result
        HttpEntity<?> httpEntity = new HttpEntity<Object>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyErrorHandler());

        ResponseEntity<String> response = null;
        int statusCode = 0;
        try {
            response = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, String.class);

        } catch (HttpStatusCodeException exception) {
            statusCode = exception.getStatusCode().value();
        }

        return response;
    }

    public static ResponseEntity<String> executePostWithBasicAuth(String endPoint, String payload) {
        // set headers
        httpclient = new DefaultHttpClient();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add(HttpHeaders.AUTHORIZATION, "Bearer 2d2b3690aa1757421a3a6ea5116b37e3d55d567699449f0a1b1d58891d1ea1f6");  //
        headers.set("Authorization", "Bearer fadc6d9ba023acbcccc3dec8c2d78119c56b0c8cef31bcec20c50cca1e32c786");  //

        // send request and parse result
        HttpEntity<?> httpEntity = new HttpEntity<Object>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyErrorHandler());

        ResponseEntity<String> response = null;
        int statusCode = 0;
        try {
            response = restTemplate.exchange(endPoint, HttpMethod.POST, httpEntity, String.class);

        } catch (HttpStatusCodeException exception) {
            statusCode = exception.getStatusCode().value();
            Assert.fail("Failed to POST to HUB API !!");
        }

        return response;
    }


    public static ResponseEntity<String> executeDelete(String endPoint, String payload, String accessToken) {
        // set headers
        httpclient = new DefaultHttpClient();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        // send request and parse result
        HttpEntity<?> httpEntity = new HttpEntity<Object>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(endPoint, HttpMethod.DELETE, httpEntity, String.class);
        } catch (RestClientException e) {
            HttpStatusCodeException ex = (HttpStatusCodeException)e;
            e.printStackTrace();
            System.out.println(ex.getResponseBodyAsString());

        }

        return response;
    }
}
