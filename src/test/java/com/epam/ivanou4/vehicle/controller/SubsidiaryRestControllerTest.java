package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vehicle.model.Subsidiary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SubsidiaryRestControllerTest extends AbstractRestControllerTest {

    @Before
    public void setUp() {
        mongoTemplate.save(createSubsidiary("testId", "testLocation"));
        mongoTemplate.save(createSubsidiary("testId2", "testLocation2"));
    }

    @After
    public void after() {
        mongoTemplate.dropCollection(Subsidiary.class);
    }

    @Test
    public void getAll() {
        ResponseEntity<List<Subsidiary>> responseEntity =
                restTemplate.exchange(createURL("/rest/subsidiary"), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Subsidiary>>() {
                        });
        List<Subsidiary> subsidiaries = responseEntity.getBody();
        assertThat(subsidiaries.size(), is(2));
    }

    @Test
    public void getById() {
        Subsidiary subsidiary =
                restTemplate.getForObject(createURL("/rest/subsidiary/{id}"), Subsidiary.class, "testId");
        assertThat(subsidiary.getId(), is("testId"));
        assertThat(subsidiary.getLocation(), is("testLocation"));
        assertThat(subsidiary.getCompanyId(), is("testCompanyId"));
    }

    @Test
    public void create() {
        HttpEntity<Subsidiary> request = new HttpEntity<>(createSubsidiary(null, "testLocation"));
        Subsidiary createdSubsidiary = restTemplate.postForObject(createURL("/rest/subsidiary"),
                request, Subsidiary.class);
        assertThat(createdSubsidiary.getId(), notNullValue());
        assertThat(createdSubsidiary.getLocation(), is("testLocation"));
        assertThat(createdSubsidiary.getCompanyId(), is("testCompanyId"));
    }

    @Test
    public void update() {
        Subsidiary subsidiary = createSubsidiary("testId", "newTestLocation");
        HttpEntity<Subsidiary> request = new HttpEntity<>(subsidiary);
        restTemplate.put(createURL("/rest/subsidiary"), request, Subsidiary.class);
        Subsidiary updatedSubsidiary = restTemplate.
                getForObject(createURL("/rest/subsidiary/{id}"), Subsidiary.class, "testId");
        assertThat(updatedSubsidiary.getId(), is(subsidiary.getId()));
        assertThat(updatedSubsidiary.getLocation(), is(subsidiary.getLocation()));
        assertThat(updatedSubsidiary.getCompanyId(), is(subsidiary.getCompanyId()));
    }

    @Test
    public void delete() {
        restTemplate.delete(createURL("/rest/subsidiary/{id}"), "testId");
        Subsidiary subsidiary = restTemplate.getForObject(createURL("/rest/subsidiary/{id}"),
                Subsidiary.class, "testId");
        assertNull(subsidiary);
    }

    @Test
    public void getByCompanyId() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURL("/rest/subsidiary"))
                .queryParam("companyId", "testCompanyId");
        ResponseEntity<List<Subsidiary>> responseEntity =
                restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Subsidiary>>() {
                        });
        List<Subsidiary> subsidiaries = responseEntity.getBody();
        assertThat(subsidiaries.size(), is(2));
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
