package com.epam.ivanou4.vechicle.validation;

import org.junit.Test;

public class ValidationTest {
    public static final String COMPANY_JSON_PASS =
            "{\"id\":\"testId\",\"name\":\"testName\",\"description\":\"description1\"" +
                    ",\"creationDate\":\"2018-10-02T20:44:41.233+0000\"}";
    public static final String SUBSIDIARY_JSON_PASS =
            "{\"id\":\"testId\",\"companyId\":\"testCompanyId\",\"location\":\"newTestLocation\"," +
                    "\"creationDate\":\"2018-10-21T18:17:12.718+0000\"}";
    public static final String COMPANY_JSON_UNPASS =
            "{\"id\":\"testId\",\"name\":\"testName\",\"description\":\"description1\"" +
                    ",\"creationDate\":\"2018-10-1.233+0000\"}";
    public static final String SUBSIDIARY_JSON_UNPASS =
            "{\"id\":\"testId\",\"companyId\":\"testCompanyId\",\"location\":\"newTestLocation\"," +
                    "\"creationDate\":\"2018-T18:17:12.718+0000\"}";
    public static final String COMPANY_SCHEMA = "company_shema.json";
    public static final String SUBSIDIARY_SCHEMA = "subsidiary_shema.json";


    @Test
    public void validateCompanyPass() {
        Validation validation = new Validation();
        Boolean validate = validation.validate(COMPANY_JSON_PASS, COMPANY_SCHEMA);
        assert (validate);
    }

    @Test
    public void validateCompanyUnpass() {
        Validation validation = new Validation();
        Boolean validate = validation.validate(COMPANY_JSON_UNPASS, COMPANY_SCHEMA);
        assert (!validate);
    }

    @Test
    public void validateSubsidiaryPass() {
        Validation validation = new Validation();
        Boolean validate = validation.validate(SUBSIDIARY_JSON_PASS, SUBSIDIARY_SCHEMA);
        assert (validate);
    }

    @Test
    public void validateSubsidiaryUnpass() {
        Validation validation = new Validation();
        Boolean validate = validation.validate(SUBSIDIARY_JSON_UNPASS, SUBSIDIARY_SCHEMA);
        assert (!validate);
    }
}