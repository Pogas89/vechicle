package com.epam.ivanou4.vechicle.validation;

import com.github.fge.jackson.JsonLoader;
import org.junit.Test;

import java.io.IOException;

public class ValidationTest {
    public static final String COMPANY_SCHEMA = "company_shema.json";
    public static final String SUBSIDIARY_SCHEMA = "subsidiary_shema.json";

    private Validation validation = new Validation();

    @Test
    public void validateCompanyPass() throws IOException {
        Boolean isValid =
                validation.validate(JsonLoader.fromResource("/valid_company.json").toString(), COMPANY_SCHEMA);
        assert (isValid);
    }

    @Test
    public void validateCompanyUnpass() throws IOException {
        Boolean isValid =
                validation.validate(JsonLoader.fromResource("/invalid_company.json").toString(), COMPANY_SCHEMA);
        assert (!isValid);
    }

    @Test
    public void validateSubsidiaryPass() throws IOException {
        Boolean isValid =
                validation.validate(JsonLoader.fromResource("/valid_subsidiary.json").toString(), SUBSIDIARY_SCHEMA);
        assert (isValid);
    }

    @Test
    public void validateSubsidiaryUnpass() throws IOException {
        Boolean isValid =
                validation.validate(JsonLoader.fromResource("/invalid_subsidiary.json").toString(), SUBSIDIARY_SCHEMA);
        assert (!isValid);
    }
}
