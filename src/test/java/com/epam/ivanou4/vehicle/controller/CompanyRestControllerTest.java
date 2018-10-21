package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vehicle.VehicleApplication;
import com.epam.ivanou4.vehicle.model.Company;
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
public class CompanyRestControllerTest {
    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getAll() {
        HttpEntity<Company> request = new HttpEntity<>(createCompany("testId", "testName"));
        restTemplate.postForObject(createURL("/rest/company"),
                request, Company.class);
        request = new HttpEntity<>(createCompany("testId2", "testName2"));
        restTemplate.postForObject(createURL("/rest/company"),
                request, Company.class);
        List companies = restTemplate.getForObject(createURL("/rest/company"),
                List.class);
        assertThat(companies.size(), is(2));
    }

    @Test
    public void get() {
        HttpEntity<Company> request = new HttpEntity<>(createCompany("testId", "testName"));
        restTemplate.postForObject(createURL("/rest/company"),
                request, Company.class);
        Company company =
                restTemplate.getForObject(createURL("/rest/company/testId"), Company.class);
        assertThat(company.getId(), is("testId"));
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
        Company company = createCompany("testId", "testName");
        HttpEntity<Company> request = new HttpEntity<>(company);
        restTemplate.postForObject(createURL("/rest/company"),
                request, Company.class);
        company.setName("newTestName");
        request = new HttpEntity<>(company);
        restTemplate.put(createURL("/rest/company"), request, Company.class);
        Company updatedCompany = restTemplate.getForObject(createURL("/rest/company/testId"), Company.class);
        assertThat(updatedCompany.getName(), is(company.getName()));
    }

    @Test
    public void delete() {
        HttpEntity<Company> request = new HttpEntity<>(createCompany("testId", "testName"));
        restTemplate.postForObject(createURL("/rest/company"),
                request, Company.class);

        restTemplate.delete(createURL("/rest/company/testId"));
        if (restTemplate.getForObject(createURL("/rest/company/testId"), Company.class) != null) {
            fail();
        }
    }

    private String createURL(String uri) {
        return "http://localhost:" + port + uri;
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
