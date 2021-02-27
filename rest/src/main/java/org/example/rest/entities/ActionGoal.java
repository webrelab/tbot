package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "action_goals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ActionGoal {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String actionGoalId;
    private String actionId;
    private String cityGoalId;

    public ActionGoal(final String actionId, final String cityGoalId) {
        this.actionId = actionId;
        this.cityGoalId = cityGoalId;
    }
}
