package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "formed_groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FormedGroup {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String formedGroupId;
    private String cityId;
    private String actionId;
    private double formedGroupStartLocationLon;
    private double formedGroupStartLocationLat;
    private String formedGroupStartAddress;
    private double formedGroupDestinationLocationLon;
    private double formedGroupDestinationLocationLat;
    private String formedGroupDestinationAddress;
    private int formedGroupPeopleNumber;

    public FormedGroup(
            final String cityId,
            final String actionId,
            final double formedGroupStartLocationLon,
            final double formedGroupStartLocationLat,
            final String formedGroupStartAddress,
            final double formedGroupDestinationLocationLon,
            final double formedGroupDestinationLocationLat,
            final String formedGroupDestinationAddress,
            final int formedGroupPeopleNumber
    ) {
        this.cityId = cityId;
        this.actionId = actionId;
        this.formedGroupStartLocationLon = formedGroupStartLocationLon;
        this.formedGroupStartLocationLat = formedGroupStartLocationLat;
        this.formedGroupStartAddress = formedGroupStartAddress;
        this.formedGroupDestinationLocationLon = formedGroupDestinationLocationLon;
        this.formedGroupDestinationLocationLat = formedGroupDestinationLocationLat;
        this.formedGroupDestinationAddress = formedGroupDestinationAddress;
        this.formedGroupPeopleNumber = formedGroupPeopleNumber;
    }
}
