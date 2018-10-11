package com.epam.ivanou4.vehicle;

import com.epam.ivanou4.vehicle.model.Subsidiary;

import java.util.Date;

import static com.epam.ivanou4.vehicle.CompanyTestData.COMPANY1_ID;
import static com.epam.ivanou4.vehicle.CompanyTestData.COMPANY2_ID;

public class SubsidiaryTestData {
    public static String SUBSIDIARY1_ID = "Subsidiary1TestId";
    public static String SUBSIDIARY2_ID = "Subsidiary2TestId";
    public static Subsidiary SUBSIDIARY1 = new Subsidiary();
    public static Subsidiary SUBSIDIARY2 = new Subsidiary();

    static {
        SUBSIDIARY1.setId(SUBSIDIARY1_ID);
        SUBSIDIARY1.setLocation("testlocation1");
        SUBSIDIARY1.setCreationDate(new Date(1100000000000L));
        SUBSIDIARY1.setCompanyId(COMPANY1_ID);
        SUBSIDIARY2.setId(SUBSIDIARY2_ID);
        SUBSIDIARY2.setLocation("testlocation2");
        SUBSIDIARY2.setCreationDate(new Date(1110000000000L));
        SUBSIDIARY2.setCompanyId(COMPANY2_ID);
    }
}
