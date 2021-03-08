package org.tbot.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "city_goals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CityGoal {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String cityGoalId;
    private String cityId;
    private String goalName;

    @Type(type = "text")
    private String goalDescription;

    @Temporal(TemporalType.DATE)
    private Date goalDestinationDate;

    @ManyToMany(cascade = {
            CascadeType.ALL
    },
            mappedBy = "cityGoals")
    private Set<Action> actions = new HashSet<>();

    public CityGoal(
            final String cityId,
            final String goalName,
            final String goalDescription,
            final Date goalDestinationDate
    ) {
        this.cityId = cityId;
        this.goalName = goalName;
        this.goalDescription = goalDescription;
        this.goalDestinationDate = goalDestinationDate;
    }

    public static String getActionRelativeName() {
        return "actions";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final CityGoal that = (CityGoal) o;
        return Objects.equals(cityGoalId, that.cityGoalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityGoalId);
    }
}
