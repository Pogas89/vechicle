package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Company;
import com.epam.ivanou4.vehicle.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.epam.ivanou4.vehicle.CompanyTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CompanyServiceImplTest {
    @InjectMocks
    CompanyServiceImpl service;

    @Mock
    private CompanyRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        when(repository.save(any(Company.class))).thenReturn(COMPANY1);
        Company company = service.create(COMPANY1);
        assertThat(company).isEqualTo(COMPANY1);
    }

    @Test
    void update() {
        when(repository.save(any(Company.class))).thenReturn(COMPANY1);
        service.update(COMPANY1);
        verify(repository, times(1)).save(COMPANY1);
    }

    @Test
    void delete() {
        doNothing().when(repository).delete(anyString());
        service.delete(COMPANY1_ID);
        verify(repository, times(1)).delete(COMPANY1_ID);
    }

    @Test
    void get() {
        when(repository.get(anyString())).thenReturn(COMPANY1);
        Company company = service.get(COMPANY1_ID);
        assertThat(company).isEqualTo(COMPANY1);
    }

    @Test
    void getAll() {
        when(repository.getAll()).thenReturn(Arrays.asList(COMPANY1, COMPANY2));
        List<Company> companies = service.getAll();
        assertThat(companies).isEqualTo(Arrays.asList(COMPANY1, COMPANY2));
    }
}