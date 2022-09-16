package org.cache.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "emp_cache")
public class Employee {

    @Id
    @GeneratedValue(generator = "emp_id",strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private Organization organization;

    public Employee(EmployeeData data) {

        if (Objects.nonNull(data)) {
            this.firstName = data.getEmployee().firstName;
            this.lastName = data.getEmployee().lastName;
            if (Objects.nonNull(data.getOrganization())) {
                this.organization = new Organization(data.getOrganization());
            }
        }
    }
}
