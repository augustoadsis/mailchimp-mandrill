package com.augusto.mandrill;

import com.augusto.mandrill.request.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class MandrillService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${mandrill.apiKey}")
    public String apiKey;
    @Value("${mandrill.baseUrl}")
    public String baseUrl;

    public ResponseEntity<Object> send() {

        To to = To.builder()
                .name("User To")
                .email("to@mail.com").build();

        //Template replace variables
        GlobalMergeVar globalMergeVar = GlobalMergeVar.builder()
                .name("name")
                .content("Test").build();

        Message message = Message.builder()
                .from_email("from@mail.com")
                .subject("Test API")
                .to(Arrays.asList(to))
                .global_merge_vars(Arrays.asList(globalMergeVar))
                .merge_language("mailchimp")
                .build();
        BaseRequest baseRequest = BaseRequest.builder()
                .template_name("Test")
                .template_content(new ArrayList<>())
                .message(message).build();

        baseRequest.setKey(apiKey);

        HttpHeaders headers = generateHeader();
        HttpEntity<BaseRequest> request = new HttpEntity(baseRequest, headers);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(baseRequest);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ResponseEntity<Object> response = restTemplate.exchange(baseUrl + "/1.0/messages/send-template", HttpMethod.POST, request, Object.class);
        return response;

    }

    public HttpHeaders generateHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }
}
