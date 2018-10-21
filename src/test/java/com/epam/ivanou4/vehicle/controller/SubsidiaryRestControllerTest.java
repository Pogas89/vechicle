package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vehicle.VehicleApplication;
import com.epam.ivanou4.vehicle.model.Subsidiary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VehicleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubsidiaryRestControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getAll() {
        HttpEntity<Subsidiary> request = new HttpEntity<>(createSubsidiary("testId", "testName"));
        restTemplate.postForObject(createURL("/rest/subsidiary"),
                request, Subsidiary.class);
        request = new HttpEntity<>(createSubsidiary("testId2", "testName2"));
        restTemplate.postForObject(createURL("/rest/subsidiary"),
                request, Subsidiary.class);
        List subsidiaries = restTemplate.getForObject(createURL("/rest/subsidiary"),
                List.class);
        assertThat(subsidiaries.size(), is(2));
    }

    @Test
    public void get() {
        HttpEntity<Subsidiary> request = new HttpEntity<>(createSubsidiary("testId", "testLocation"));
        restTemplate.postForObject(createURL("/rest/subsidiary"),
                request, Subsidiary.class);
        Subsidiary subsidiary =
                restTemplate.getForObject(createURL("/rest/subsidiary/testId"), Subsidiary.class);
        assertThat(subsidiary.getId(), is("testId"));
    }

    @Test
    public void create() {
        HttpEntity<Subsidiary> request = new HttpEntity<>(createSubsidiary(null, "testLocation"));
        Subsidiary createdSubsidiary = restTemplate.postForObject(createURL("/rest/subsidiary"),
                request, Subsidiary.class);
        assertThat(createdSubsidiary.getId(), notNullValue());
        assertThat(createdSubsidiary.getLocation(), is("testLocation"));
    }

    @Test
    public void update() {
        Subsidiary subsidiary = createSubsidiary("testId", "testLocation");
        HttpEntity<Subsidiary> request = new HttpEntity<>(subsidiary);
        restTemplate.postForObject(createURL("/rest/subsidiary"),
                request, Subsidiary.class);
        subsidiary.setLocation("newTestLocation");
        request = new HttpEntity<>(subsidiary);
        restTemplate.put(createURL("/rest/subsidiary"), request, Subsidiary.class);
        Subsidiary updatedsubsidiary = restTemplate.
                getForObject(createURL("/rest/subsidiary/testId"), Subsidiary.class);
        assertThat(updatedsubsidiary.getLocation(), is(subsidiary.getLocation()));
    }

    @Test
    public void delete() {
        HttpEntity<Subsidiary> request = new HttpEntity<>(createSubsidiary("testId", "testLocation"));
        restTemplate.postForObject(createURL("/rest/subsidiary"),
                request, Subsidiary.class);

        restTemplate.delete(createURL("/rest/subsidiary/testId"));
        if (restTemplate.getForObject(createURL("/rest/subsidiary/testId"), Subsidiary.class) != null) {
            fail();
        }
    }

    @Test
    public void getByCompanyId() {
        HttpEntity<Subsidiary> request = new HttpEntity<>(createSubsidiary("testId", "testLocation"));
        restTemplate.postForObject(createURL("/rest/subsidiary"),
                request, Subsidiary.class);
        List subsidiaries = restTemplate.getForObject(createURL("/rest/subsidiary/by/testCompanyId"),
                List.class);
        assertThat(subsidiaries.size(), is(1));
    }

    private String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }


    private Subsidiary createSubsidiary(String id, String location) {
        Subsidiary subsidiary = new Subsidiary();
        subsidiary.setId(id);
        subsidiary.setLocation(location);
        subsidiary.setCreationDate(new Date());
        subsidiary.setCompanyId("testCompanyId");
        return subsidiary;
    }
}
