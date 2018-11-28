package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vehicle.model.Subsidiary;
import com.epam.ivanou4.vehicle.to.SubsidiaryDTO;
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
        ResponseEntity<List<SubsidiaryDTO>> responseEntity =
                restTemplate.exchange(createURL("/rest/subsidiary"), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<SubsidiaryDTO>>() {
                        });
        List<SubsidiaryDTO> subsidiaryDTOS = responseEntity.getBody();
        assertThat(subsidiaryDTOS.size(), is(2));
    }

    @Test
    public void getById() {
        SubsidiaryDTO subsidiaryDTO =
                restTemplate.getForObject(createURL("/rest/subsidiary/{id}"), SubsidiaryDTO.class, "testId");
        assertThat(subsidiaryDTO.getId(), is("testId"));
        assertThat(subsidiaryDTO.getLocation(), is("testLocation"));
        assertThat(subsidiaryDTO.getCompanyId(), is("testCompanyId"));
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
        SubsidiaryDTO subsidiaryDTO = convertToSubsidiaryDTO(createSubsidiary("testId", "newTestLocation"));
        HttpEntity<SubsidiaryDTO> request = new HttpEntity<>(subsidiaryDTO);
        restTemplate.put(createURL("/rest/subsidiary"), request, SubsidiaryDTO.class);
        SubsidiaryDTO updatedSubsidiary = restTemplate.
                getForObject(createURL("/rest/subsidiary/{id}"), SubsidiaryDTO.class, "testId");
        assertThat(updatedSubsidiary.getId(), is(subsidiaryDTO.getId()));
        assertThat(updatedSubsidiary.getLocation(), is(subsidiaryDTO.getLocation()));
        assertThat(updatedSubsidiary.getCompanyId(), is(subsidiaryDTO.getCompanyId()));
    }

    @Test
    public void delete() {
        restTemplate.delete(createURL("/rest/subsidiary/{id}"), "testId");
        SubsidiaryDTO subsidiaryDTO = restTemplate.getForObject(createURL("/rest/subsidiary/{id}"),
                SubsidiaryDTO.class, "testId");
        assertNull(subsidiaryDTO);
    }

    @Test
    public void getByCompanyId() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURL("/rest/subsidiary"))
                .queryParam("companyId", "testCompanyId");
        ResponseEntity<List<SubsidiaryDTO>> responseEntity =
                restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<SubsidiaryDTO>>() {
                        });
        List<SubsidiaryDTO> subsidiaryDTOS = responseEntity.getBody();
        assertThat(subsidiaryDTOS.size(), is(2));
    }

    private Subsidiary createSubsidiary(String id, String location) {
        Subsidiary subsidiary = new Subsidiary();
        subsidiary.setId(id);
        subsidiary.setLocation(location);
        subsidiary.setCreationDate(new Date());
        subsidiary.setCompanyId("testCompanyId");
        return subsidiary;
    }

    private SubsidiaryDTO convertToSubsidiaryDTO(Subsidiary subsidiary) {
        return dozerBeanMapper.map(subsidiary,SubsidiaryDTO.class);
    }
}
