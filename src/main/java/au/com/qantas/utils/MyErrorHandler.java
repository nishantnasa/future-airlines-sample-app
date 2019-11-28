package au.com.future-airlines.utils;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by boses on 27/10/2017.
 */
public class MyErrorHandler implements ResponseErrorHandler {

    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return false;
    }

    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

// TODO : Implement diff. handling mechanishms for diff. error codes

    }



}
