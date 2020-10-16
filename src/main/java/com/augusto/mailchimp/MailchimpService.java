package com.augusto.mailchimp;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Slf4j
@Service
public class MailchimpService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${mailchimp.apiKey}")
    public String apiKey;
    @Value("${mailchimp.baseUrl}")
    public String baseUrl;

    public ResponseEntity<String> ping() {

        HttpHeaders authorization = new HttpHeaders() {{
            String auth = "mailchimp" + ":" + apiKey;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/ping" , HttpMethod.GET, new HttpEntity<>(authorization), String.class);
        log.info(response.getBody());
        return response;

    }
}
