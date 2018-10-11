package com.epam.ivanou4.vehicle;

import com.epam.ivanou4.vehicle.model.Company;

import java.util.Date;

public class CompanyTestData {
    public static String COMPANY1_ID = "Company1TestId";
    public static String COMPANY2_ID = "Company2TestId";
    public static Company COMPANY1 = new Company();
    public static Company COMPANY2 = new Company();

    static {
        COMPANY1.setId(COMPANY1_ID);
        COMPANY1.setName("testname1");
        COMPANY1.setDescription("testdescription1");
        COMPANY1.setCreationDate(new Date(1000000000000L));
        COMPANY2.setId(COMPANY2_ID);
        COMPANY2.setName("testname2");
        COMPANY2.setDescription("testdescription2");
        COMPANY2.setCreationDate(new Date(1002000000000L));
    }
}
