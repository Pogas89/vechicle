package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Subsidiary;
import com.epam.ivanou4.vehicle.repository.SubsidiaryRepository;
import com.epam.ivanou4.vehicle.to.SubsidiaryDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SubsidiaryServiceImplTest {
    private static final String SUBSIDIARY1_ID = "Subsidiary1TestId";
    private static final String SUBSIDIARY2_ID = "Subsidiary2TestId";
    private static final String COMPANY1_ID = "Company1TestId";
    private ObjectMapper objectMapper = new ObjectMapper();
    private Mapper dozerMapper = new DozerBeanMapper();

    @InjectMocks
    private SubsidiaryServiceImpl service;

    @Mock
    private SubsidiaryRepository repository;

    @Test
    public void create() throws JsonProcessingException {
        Subsidiary subsidiary = createSubsidiary(SUBSIDIARY1_ID);
        when(repository.save(any(Subsidiary.class))).thenReturn(subsidiary);
        SubsidiaryDTO subsidiaryDTO = service.create(objectMapper.writeValueAsString(subsidiary));
        assertThat(convertToSubsidiary(subsidiaryDTO)).isEqualTo(subsidiary);
        verify(repository).save(subsidiary);
    }

    @Test
    public void update() throws JsonProcessingException {
        Subsidiary subsidiary = createSubsidiary(SUBSIDIARY1_ID);
        when(repository.save(any(Subsidiary.class))).thenReturn(subsidiary);
        service.update(objectMapper.writeValueAsString(subsidiary));
        verify(repository).save(subsidiary);
    }

    @Test
    public void delete() {
        doNothing().when(repository).delete(anyString());
        service.delete(SUBSIDIARY2_ID);
        verify(repository).delete(SUBSIDIARY2_ID);
    }

    @Test
    public void get() {
        Subsidiary subsidiary = createSubsidiary(SUBSIDIARY1_ID);
        when(repository.get(anyString())).thenReturn(subsidiary);
        SubsidiaryDTO subsidiaryDTO = service.get(SUBSIDIARY1_ID);
        assertThat(convertToSubsidiary(subsidiaryDTO)).isEqualTo(subsidiary);
        verify(repository).get(SUBSIDIARY1_ID);
    }

    @Test
    public void getAll() {
        Subsidiary firstCreatedSubsidiary = createSubsidiary(SUBSIDIARY1_ID);
        Subsidiary secondCreatedSubsidiary = createSubsidiary(SUBSIDIARY2_ID);
        when(repository.getAll()).thenReturn(Arrays.asList(firstCreatedSubsidiary, secondCreatedSubsidiary));
        List<Subsidiary> subsidiaries = convertToSubsidiaries(service.getAll());
        assertThat(subsidiaries).isEqualTo(Arrays.asList(firstCreatedSubsidiary, secondCreatedSubsidiary));
        verify(repository).getAll();
    }

    @Test
    public void getByCompanyId() {
        Subsidiary subsidiary = createSubsidiary(SUBSIDIARY1_ID);
        when(repository.getByCompanyId(COMPANY1_ID)).thenReturn(Collections.singletonList(subsidiary));
        List<Subsidiary> subsidiaries = convertToSubsidiaries(service.getByCompanyId(COMPANY1_ID));
        assertThat(subsidiaries).isEqualTo(Collections.singletonList(subsidiary));
        verify(repository).getByCompanyId(COMPANY1_ID);
    }

    private Subsidiary createSubsidiary(String id) {
        Subsidiary subsidiary = new Subsidiary();
        subsidiary.setId(id);
        subsidiary.setLocation("TestLocation");
        subsidiary.setCreationDate(new Date());
        subsidiary.setCompanyId(COMPANY1_ID);
        return subsidiary;
    }

    private Subsidiary convertToSubsidiary(SubsidiaryDTO subsidiaryDTO) {
        return dozerMapper.map(subsidiaryDTO,Subsidiary.class);
    }

    private List<Subsidiary> convertToSubsidiaries(List<SubsidiaryDTO> subsidiaryDTOS) {
        List<Subsidiary> subsidiaries = new ArrayList<>();
        for (SubsidiaryDTO subsidiaryDTO : subsidiaryDTOS) {
            subsidiaries.add(dozerMapper.map(subsidiaryDTO,Subsidiary.class));
        }
        return subsidiaries;
    }
}
