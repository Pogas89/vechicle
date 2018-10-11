package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Subsidiary;
import com.epam.ivanou4.vehicle.repository.SubsidiaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.epam.ivanou4.vehicle.CompanyTestData.COMPANY1_ID;
import static com.epam.ivanou4.vehicle.SubsidiaryTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SubsidiaryServiceImplTest {
    @InjectMocks
    private SubsidiaryServiceImpl service;

    @Mock
    private SubsidiaryRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        when(repository.save(any(Subsidiary.class))).thenReturn(SUBSIDIARY1);
        Subsidiary subsidiary = service.create(SUBSIDIARY1);
        assertThat(subsidiary).isEqualTo(SUBSIDIARY1);
    }

    @Test
    void update() {
        when(repository.save(any(Subsidiary.class))).thenReturn(SUBSIDIARY1);
        service.update(SUBSIDIARY1);
        verify(repository, times(1)).save(SUBSIDIARY1);
    }

    @Test
    void delete() {
        doNothing().when(repository).delete(anyString());
        service.delete(SUBSIDIARY1_ID);
        verify(repository, times(1)).delete(SUBSIDIARY1_ID);
    }

    @Test
    void get() {
        when(repository.get(anyString())).thenReturn(SUBSIDIARY1);
        Subsidiary subsidiary = service.get(SUBSIDIARY1_ID);
        assertThat(subsidiary).isEqualTo(SUBSIDIARY1);
    }

    @Test
    void getAll() {
        when(repository.getAll()).thenReturn(Arrays.asList(SUBSIDIARY1, SUBSIDIARY2));
        List<Subsidiary> subsidiaries = service.getAll();
        assertThat(subsidiaries).isEqualTo(Arrays.asList(SUBSIDIARY1, SUBSIDIARY2));
    }

    @Test
    void getByCompanyId() {
        when(repository.getByCompanyId(COMPANY1_ID)).thenReturn(Collections.singletonList(SUBSIDIARY1));
        List<Subsidiary> subsidiaries = service.getByCompanyId(COMPANY1_ID);
        assertThat(subsidiaries).isEqualTo(Collections.singletonList(SUBSIDIARY1));
    }
}