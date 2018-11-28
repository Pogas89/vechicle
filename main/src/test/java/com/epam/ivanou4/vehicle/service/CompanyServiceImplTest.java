package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Company;
import com.epam.ivanou4.vehicle.repository.CompanyRepository;
import com.epam.ivanou4.vehicle.to.CompanyDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceImplTest {
    private static final String COMPANY1_ID = "Company1TestId";
    private static final String COMPANY2_ID = "Company2TestId";
    private ObjectMapper objectMapper = new ObjectMapper();
    private Mapper dozerMapper = new DozerBeanMapper();

    @InjectMocks
    private CompanyServiceImpl service;

    @Mock
    private CompanyRepository repository;

    @Test
    public void create() throws JsonProcessingException {
        Company createdCompany = createCompany(COMPANY1_ID);
        when(repository.save(any(Company.class))).thenReturn(createdCompany);
        CompanyDTO companyDTO = service.create(objectMapper.writeValueAsString(createdCompany));
        assertThat(dozerMapper.map(companyDTO, Company.class)).isEqualTo(createdCompany);
        verify(repository).save(createdCompany);
    }

    @Test
    public void update() throws JsonProcessingException {
        Company createdCompany = createCompany(COMPANY1_ID);
        when(repository.save(any(Company.class))).thenReturn(createdCompany);
        service.update(objectMapper.writeValueAsString(createdCompany));
        verify(repository).save(createdCompany);
    }

    @Test
    public void delete() {
        doNothing().when(repository).delete(anyString());
        service.delete(COMPANY2_ID);
        verify(repository).delete(COMPANY2_ID);
    }

    @Test
    public void get() {
        Company createdCompany = createCompany(COMPANY1_ID);
        when(repository.get(anyString())).thenReturn(createdCompany);
        CompanyDTO companyDTO = service.get(COMPANY1_ID);
        assertThat(dozerMapper.map(companyDTO, Company.class)).isEqualTo(createdCompany);
        verify(repository).get(COMPANY1_ID);
    }

    @Test
    public void getAll() {
        Company firstCreatedCompany = createCompany(COMPANY1_ID);
        Company secondCreatedCompany = createCompany(COMPANY2_ID);
        when(repository.getAll()).thenReturn(Arrays.asList(firstCreatedCompany, secondCreatedCompany));
        List<CompanyDTO> companyDTOs = service.getAll();
        List<Company> companies = new ArrayList<>();
        for (CompanyDTO companyDTO : companyDTOs) {
            companies.add(dozerMapper.map(companyDTO, Company.class));
        }
        assertThat(companies).isEqualTo(Arrays.asList(firstCreatedCompany, secondCreatedCompany));
        verify(repository).getAll();
    }

    private Company createCompany(String id) {
        Company company = new Company();
        company.setId(id);
        company.setName("TestName");
        company.setDescription("TestDescription");
        company.setCreationDate(new Date());
        return company;
    }
}
