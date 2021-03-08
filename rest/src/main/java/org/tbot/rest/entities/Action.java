package org.tbot.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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

    public static String getCityGoalRelativeName() {
        return "cityGoals";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final Action that = (Action) o;
        return Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionId);
    }
}
