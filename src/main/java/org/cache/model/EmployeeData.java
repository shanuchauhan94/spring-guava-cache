package org.cache.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {

    private Employee employee;
    private Organization organization;
}
