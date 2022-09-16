package org.cache.model;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class EmployeeResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private Organization organization;

    public EmployeeResponse(Employee savedData) {
        this.id = savedData.getId();
        this.firstName = savedData.getFirstName();
        this.lastName = savedData.getFirstName();
        this.organization = new Organization(savedData.getOrganization());
    }
}
