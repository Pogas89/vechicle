package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vehicle.model.Company;
import com.epam.ivanou4.vehicle.to.CompanyDTO;
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
        ResponseEntity<List<CompanyDTO>> responseEntity =
                restTemplate.exchange(createURL("/rest/company"), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<CompanyDTO>>() {
                        });
        List<CompanyDTO> companyDTOS = responseEntity.getBody();
        assertThat(companyDTOS.size(), is(2));
    }

    @Test
    public void getById() {
        CompanyDTO companyDTO =
                restTemplate.getForObject(createURL("/rest/company/{id}"), CompanyDTO.class, "testId");
        assertThat(companyDTO.getId(), is("testId"));
        assertThat(companyDTO.getDescription(), is("testDescription"));
        assertThat(companyDTO.getName(), is("testName"));
    }

    @Test
    public void create() {
        HttpEntity<CompanyDTO> request = new HttpEntity<>(convertToCompanyDTO(createCompany(null, "testName")));
        CompanyDTO createdCompanyDTO = restTemplate.postForObject(createURL("/rest/company"),
                request, CompanyDTO.class);
        assertThat(createdCompanyDTO.getId(), notNullValue());
        assertThat(createdCompanyDTO.getName(), is("testName"));
        assertThat(createdCompanyDTO.getDescription(), is("testDescription"));
    }

    @Test
    public void update() {
        CompanyDTO companyDTO = convertToCompanyDTO(createCompany("testId", "newTestName"));
        HttpEntity<CompanyDTO> request = new HttpEntity<>(companyDTO);
        restTemplate.put(createURL("/rest/company"), request, CompanyDTO.class);
        CompanyDTO updatedCompany = restTemplate.getForObject(createURL("/rest/company/{id}"),
                CompanyDTO.class, "testId");
        assertThat(updatedCompany.getId(), is(companyDTO.getId()));
        assertThat(updatedCompany.getName(), is(companyDTO.getName()));
        assertThat(updatedCompany.getDescription(), is(companyDTO.getDescription()));
        assertThat(updatedCompany.getCreationDate(), is(companyDTO.getCreationDate()));
    }

    @Test
    public void delete() {
        restTemplate.delete(createURL("/rest/company/{id}"), "testId");
        CompanyDTO companyDTO = restTemplate.getForObject(createURL("/rest/company/{id}"),
                CompanyDTO.class, "testId");
        assertNull(companyDTO);
    }

    private Company createCompany(String id, String name) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setDescription("testDescription");
        company.setCreationDate(new Date());
        return company;
    }

    private CompanyDTO convertToCompanyDTO(Company company) {
        return dozerBeanMapper.map(company, CompanyDTO.class);
    }
}
