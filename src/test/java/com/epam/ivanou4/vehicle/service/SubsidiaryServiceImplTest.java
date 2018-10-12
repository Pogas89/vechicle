package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Subsidiary;
import com.epam.ivanou4.vehicle.repository.SubsidiaryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubsidiaryServiceImplTest {
    private static final String SUBSIDIARY1_ID = "Subsidiary1TestId";
    private static final String SUBSIDIARY2_ID = "Subsidiary2TestId";
    private static final String COMPANY1_ID = "Company1TestId";

    @InjectMocks
    private SubsidiaryServiceImpl service;

    @Mock
    private SubsidiaryRepository repository;

    private Subsidiary createSubsidiary(String id, String location, Date creationDate, String companyId) {
        Subsidiary subsidiary = new Subsidiary();
        subsidiary.setId(id);
        subsidiary.setLocation(location);
        subsidiary.setCreationDate(creationDate);
        subsidiary.setCompanyId(companyId);
        return subsidiary;
    }

    @Test
    public void create() {
        Subsidiary subsidiary1 = createSubsidiary(SUBSIDIARY1_ID, "testlocation1", new Date(1100000000000L), COMPANY1_ID);
        when(repository.save(any(Subsidiary.class))).thenReturn(subsidiary1);
        Subsidiary subsidiary = service.create(subsidiary1);
        assertThat(subsidiary).isEqualTo(subsidiary1);
        verify(repository).save(subsidiary1);
    }

    @Test
    public void update() {
        Subsidiary subsidiary1 = createSubsidiary(SUBSIDIARY1_ID, "testlocation1", new Date(1100000000000L), COMPANY1_ID);
        when(repository.save(any(Subsidiary.class))).thenReturn(subsidiary1);
        service.update(subsidiary1);
        verify(repository).save(subsidiary1);
    }

    @Test
    public void delete() {
        doNothing().when(repository).delete(anyString());
        service.delete(SUBSIDIARY2_ID);
        verify(repository).delete(SUBSIDIARY2_ID);
    }

    @Test
    public void get() {
        Subsidiary subsidiary1 = createSubsidiary(SUBSIDIARY1_ID, "testlocation1", new Date(1100000000000L), COMPANY1_ID);
        when(repository.get(anyString())).thenReturn(subsidiary1);
        Subsidiary subsidiary = service.get(SUBSIDIARY1_ID);
        assertThat(subsidiary).isEqualTo(subsidiary1);
        verify(repository).get(SUBSIDIARY1_ID);
    }

    @Test
    public void getAll() {
        Subsidiary subsidiary1 = createSubsidiary(SUBSIDIARY1_ID, "testlocation1", new Date(1100000000000L), COMPANY1_ID);
        Subsidiary subsidiary2 = createSubsidiary(SUBSIDIARY2_ID, "testlocation2", new Date(1110000000000L), COMPANY1_ID);
        when(repository.getAll()).thenReturn(Arrays.asList(subsidiary1, subsidiary2));
        List<Subsidiary> subsidiaries = service.getAll();
        assertThat(subsidiaries).isEqualTo(Arrays.asList(subsidiary1, subsidiary2));
        verify(repository).getAll();
    }

    @Test
    public void getByCompanyId() {
        Subsidiary subsidiary1 = createSubsidiary(SUBSIDIARY1_ID, "testlocation1", new Date(1100000000000L), COMPANY1_ID);
        when(repository.getByCompanyId(COMPANY1_ID)).thenReturn(Collections.singletonList(subsidiary1));
        List<Subsidiary> subsidiaries = service.getByCompanyId(COMPANY1_ID);
        assertThat(subsidiaries).isEqualTo(Collections.singletonList(subsidiary1));
        verify(repository).getByCompanyId(COMPANY1_ID);
    }
}
