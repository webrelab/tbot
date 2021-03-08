package org.tbot.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "action_registrations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActionRegistration {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String actionRegistrationId;
    private String actionId;
    private String userId;
    private double actionRegistrationLon;
    private double actionRegistrationLat;
    private String cityId;

    public ActionRegistration(
            final String actionId,
            final String userId,
            final double actionRegistrationLon,
            final double actionRegistrationLat,
            final String cityId
    ) {
        this.actionId = actionId;
        this.userId = userId;
        this.actionRegistrationLon = actionRegistrationLon;
        this.actionRegistrationLat = actionRegistrationLat;
        this.cityId = cityId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final ActionRegistration that = (ActionRegistration) o;
        return Objects.equals(actionRegistrationId, that.actionRegistrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionRegistrationId);
    }
}
