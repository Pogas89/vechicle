package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vehicle.model.Company;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CompanyRestControllerTest extends AbstractRestControllerTest {

    @Before
    public void setUp() {
        mongoTemplate.save(createCompany("testId", "testName"));
        mongoTemplate.save(createCompany("testId2", "testName2"));
    }

    @After
    public void after() {
        mongoTemplate.dropCollection(Company.class);
    }

    @Test
    public void getAll() {
        ResponseEntity<List<Company>> responseEntity =
                restTemplate.exchange(createURL("/rest/company"), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Company>>() {
                        });
        List<Company> companies = responseEntity.getBody();
        assertThat(companies.size(), is(2));
    }

    @Test
    public void getById() {
        Company company =
                restTemplate.getForObject(createURL("/rest/company/{id}"), Company.class, "testId");
        assertThat(company.getId(), is("testId"));
        assertThat(company.getDescription(), is("testDescription"));
        assertThat(company.getName(), is("testName"));
    }

    @Test
    public void create() {
        HttpEntity<Company> request = new HttpEntity<>(createCompany(null, "testName"));
        Company createdCompany = restTemplate.postForObject(createURL("/rest/company"),
                request, Company.class);
        assertThat(createdCompany.getId(), notNullValue());
        assertThat(createdCompany.getName(), is("testName"));
        assertThat(createdCompany.getDescription(), is("testDescription"));
    }

    @Test
    public void update() {
        Company company = createCompany("testId", "newTestName");
        HttpEntity<Company> request = new HttpEntity<>(company);
        restTemplate.put(createURL("/rest/company"), request, Company.class);
        Company updatedCompany = restTemplate.getForObject(createURL("/rest/company/{id}"),
                Company.class, "testId");
        assertThat(updatedCompany.getId(), is(company.getId()));
        assertThat(updatedCompany.getName(), is(company.getName()));
        assertThat(updatedCompany.getDescription(), is(company.getDescription()));
        assertThat(updatedCompany.getCreationDate(), is(company.getCreationDate()));
    }

    @Test
    public void delete() {
        restTemplate.delete(createURL("/rest/company/{id}"), "testId");
        Company company = restTemplate.getForObject(createURL("/rest/company/{id}"),
                Company.class, "testId");
        assertNull(company);
    }

    private Company createCompany(String id, String name) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setDescription("testDescription");
        company.setCreationDate(new Date());
        return company;
    }
}
