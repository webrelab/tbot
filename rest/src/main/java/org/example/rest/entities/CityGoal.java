package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "city_goals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    public CityGoal(String cityId, String goalName, String goalDescription, Date goalDestinationDate) {
        this.cityId = cityId;
        this.goalName = goalName;
        this.goalDescription = goalDescription;
        this.goalDestinationDate = goalDestinationDate;
    }
}
