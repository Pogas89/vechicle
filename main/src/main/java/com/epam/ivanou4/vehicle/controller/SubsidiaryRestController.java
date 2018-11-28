package com.epam.ivanou4.vehicle.controller;

import com.epam.ivanou4.vehicle.service.SubsidiaryService;
import com.epam.ivanou4.vehicle.to.SubsidiaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.epam.ivanou4.vehicle.controller.SubsidiaryRestController.REST_URL;

@RestController
@RequestMapping(REST_URL)
public class SubsidiaryRestController {
    static final String REST_URL = "/rest/subsidiary";

    @Autowired
    private SubsidiaryService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubsidiaryDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SubsidiaryDTO get(@PathVariable("id") String id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubsidiaryDTO> createWithLocation(@RequestBody String subsidiaryJson) {
        SubsidiaryDTO createdSubsidiaryDTO = service.create(subsidiaryJson);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdSubsidiaryDTO.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(createdSubsidiaryDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody String subsidiaryJson) {
        service.update(subsidiaryJson);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "companyId")
    public List<SubsidiaryDTO> getByCompanyId(@RequestParam("companyId") String companyId) {
        return service.getByCompanyId(companyId);
    }
}
