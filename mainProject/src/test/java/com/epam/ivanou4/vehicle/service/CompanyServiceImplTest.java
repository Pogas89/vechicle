package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Company;
import com.epam.ivanou4.vehicle.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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

    @InjectMocks
    private CompanyServiceImpl service;

    @Mock
    private CompanyRepository repository;

    @Test
    public void create() {
        Company company1 = createCompany(COMPANY1_ID);
        when(repository.save(any(Company.class))).thenReturn(company1);
        Company company = service.create(company1);
        assertThat(company).isEqualTo(company1);
        verify(repository).save(company1);
    }

    @Test
    public void update() {
        Company company1 = createCompany(COMPANY1_ID);
        when(repository.save(any(Company.class))).thenReturn(company1);
        service.update(company1);
        verify(repository).save(company1);
    }

    @Test
    public void delete() {
        doNothing().when(repository).delete(anyString());
        service.delete(COMPANY2_ID);
        verify(repository).delete(COMPANY2_ID);
    }

    @Test
    public void get() {
        Company company1 = createCompany(COMPANY1_ID);
        when(repository.get(anyString())).thenReturn(company1);
        Company company = service.get(COMPANY1_ID);
        assertThat(company).isEqualTo(company1);
        verify(repository).get(COMPANY1_ID);
    }

    @Test
    public void getAll() {
        Company company1 = createCompany(COMPANY1_ID);
        Company company2 = createCompany(COMPANY2_ID);
        when(repository.getAll()).thenReturn(Arrays.asList(company1, company2));
        List<Company> companies = service.getAll();
        assertThat(companies).isEqualTo(Arrays.asList(company1, company2));
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
