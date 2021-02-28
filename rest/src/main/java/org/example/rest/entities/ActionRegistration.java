package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
