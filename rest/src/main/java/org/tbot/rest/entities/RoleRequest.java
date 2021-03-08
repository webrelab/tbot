package org.tbot.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "role_requests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoleRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String roleRequestId;
    private String role;
    private String userId;
    private String roleRequestFilePath;

    @Type(type = "text")
    private String roleRequestDescription;

    public RoleRequest(
            final String role,
            final String userId,
            final String roleRequestFilePath,
            final String roleRequestDescription
    ) {
        this.role = role;
        this.userId = userId;
        this.roleRequestFilePath = roleRequestFilePath;
        this.roleRequestDescription = roleRequestDescription;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final RoleRequest that = (RoleRequest) o;
        return Objects.equals(roleRequestId, that.roleRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleRequestId);
    }
}
