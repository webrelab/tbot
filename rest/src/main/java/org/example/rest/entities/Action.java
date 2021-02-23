package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity(name = "actions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    public Action(String cityId, Date actionFromTimestamp, Date actionToTimestamp) {
        this.cityId = cityId;
        this.actionFromTimestamp = actionFromTimestamp;
        this.actionToTimestamp = actionToTimestamp;
    }
}
