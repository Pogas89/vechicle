package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vechicle.validation.Validation;
import com.epam.ivanou4.vehicle.model.Subsidiary;
import com.epam.ivanou4.vehicle.service.SubsidiaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static com.epam.ivanou4.vehicle.controller.SubsidiaryRestController.REST_URL;

@RestController
@RequestMapping(REST_URL)
public class SubsidiaryRestController {
    public static final String REST_URL = "/rest/subsidiary";
    public static final String SUBSIDIARY_SCHEMA = "subsidiary_shema.json";

    @Autowired
    private SubsidiaryService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subsidiary> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Subsidiary get(@PathVariable("id") String id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subsidiary> createWithLocation(@RequestBody String subsidiaryJson) {
        if (new Validation().validate(subsidiaryJson, SUBSIDIARY_SCHEMA)) {
            Subsidiary subsidiary = mapSubsidiary(subsidiaryJson);
            Subsidiary createdSubsidiary = service.create(subsidiary);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(createdSubsidiary.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(createdSubsidiary);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody String subsidiaryJson) {
        if (new Validation().validate(subsidiaryJson, SUBSIDIARY_SCHEMA)) {
            Subsidiary subsidiary = mapSubsidiary(subsidiaryJson);
            service.update(subsidiary);
        }
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "companyId")
    public List<Subsidiary> getByCompanyId(@RequestParam("companyId") String companyId) {
        return service.getByCompanyId(companyId);
    }

    private Subsidiary mapSubsidiary(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Subsidiary subsidiary = null;
        try {
            subsidiary = mapper.readValue(json, Subsidiary.class);
        } catch (IOException ignored) {
        }
        return subsidiary;
    }
}
