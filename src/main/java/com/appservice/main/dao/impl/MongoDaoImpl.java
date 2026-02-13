package com.appservice.main.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import com.appservice.main.dao.MongoDao;

@Repository
public class MongoDaoImpl implements MongoDao {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.mongo-service.url:http://app-mongo-service:8081}")
    private String mongoServiceUrl;

    @Override
    public String getFindByIdPersona(Integer id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.set("my-id", "profe-test");

        System.out.println("mongoServiceUrl: " + mongoServiceUrl);

        // url para docker
        String url = mongoServiceUrl + "/mongoservice/personas/findByIdPersona/" + id;

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
                String.class);

        return responseEntity.getBody();
    }

}
