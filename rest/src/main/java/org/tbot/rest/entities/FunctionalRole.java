package org.tbot.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "xref_functional_role_user",
            joinColumns = @JoinColumn(name = "functional_role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    public FunctionalRole(
            final String functionalRoleName, final String functionalRoleDescription,
            final String functionalRoleGetCondition
    ) {
        this.functionalRoleName = functionalRoleName;
        this.functionalRoleDescription = functionalRoleDescription;
        this.functionalRoleGetCondition = functionalRoleGetCondition;
    }

    public static String getUserRelationName() {
        return "users";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final FunctionalRole that = (FunctionalRole) o;
        return Objects.equals(functionalRoleId, that.functionalRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(functionalRoleId);
    }
}
