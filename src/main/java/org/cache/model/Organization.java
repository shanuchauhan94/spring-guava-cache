package org.cache.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;
import java.util.stream.IntStream;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "org_cache")
public class Organization {

    @Id
    @GeneratedValue(generator = "org_id",strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;

    public Organization(Organization organization) {
        this.id = organization.id;
        this.name = organization.name;
        this.description = organization.description;
    }
}
