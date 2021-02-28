package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "formed_groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "xref_formed_group_user",
            joinColumns = @JoinColumn(name = "formed_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

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
