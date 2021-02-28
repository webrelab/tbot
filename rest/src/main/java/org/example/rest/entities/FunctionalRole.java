package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "functional_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FunctionalRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String functionalRoleId;
    private String functionalRoleName;
    private String functionalRoleDescription;
    private String functionalRoleGetCondition;

    public FunctionalRole(
            final String functionalRoleName, final String functionalRoleDescription,
            final String functionalRoleGetCondition
    ) {
        this.functionalRoleName = functionalRoleName;
        this.functionalRoleDescription = functionalRoleDescription;
        this.functionalRoleGetCondition = functionalRoleGetCondition;
    }
}
