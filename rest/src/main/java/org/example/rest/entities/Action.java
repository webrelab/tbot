package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "actions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Action {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String actionId;

    private String cityId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actionFromTimestamp;

    @Temporal(TemporalType.TIMESTAMP)

    private Date actionToTimestamp;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "xref_action_goal",
            joinColumns = @JoinColumn(name = "action_id"),
            inverseJoinColumns = @JoinColumn(name = "city_goal_id")
    )
    private Set<CityGoal> cityGoals = new HashSet<>();

    public Action(
            final String cityId,
            final Date actionFromTimestamp,
            final Date actionToTimestamp
    ) {
        this.cityId = cityId;
        this.actionFromTimestamp = actionFromTimestamp;
        this.actionToTimestamp = actionToTimestamp;
    }
}
