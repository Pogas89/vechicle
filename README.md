Tasks

Module 1: Auto Delivery service
1. Create project skeleton
    Tech stack: gradle, spring boot
2. Add domain models
    Company (Fields: id, name, description, creation date)
    Subsidiary (Fields: id, parentId, string location, creation date)
3. Implement dao layer + unit tests
    Implement repository layer component (Spring JPA, mongodb)
    Implement unit tests
4. Implement service layer + unit tests
    Implement services
    Implement unit tests
5. Implement REST endpoint
    Implement endpoint (get companies, get company by id, get subsidiaries by company id)

Module 2: Vehicle service
1. Create project skeleton
    Tech stack: gradle, spring mvc, tomcat(add configuration when it is necessary)
2. Add domain models
    Vehicle (Fields: id, model, company id, cost, owner id)
    Owner (Fields: id, name, surname, passport data)
3. Implement dao layer + unit tests
    Implement repository layer component (Spring JPA, mongodb)
    Implement unit tests
4. Implement service layer + unit tests
    Implement services
    Implement unit tests
5. Implement REST endpoint
    Implement endpoint (get vehicles, get vehicle by id, get vehicles by owner id)

Module 3: Service Integration
1. TODO: add description
