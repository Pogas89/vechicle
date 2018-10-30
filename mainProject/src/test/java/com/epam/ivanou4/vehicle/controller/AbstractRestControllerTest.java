package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vehicle.VehicleApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VehicleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractRestControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    protected MongoTemplate mongoTemplate;

    protected TestRestTemplate restTemplate = new TestRestTemplate();

    public String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }

}
