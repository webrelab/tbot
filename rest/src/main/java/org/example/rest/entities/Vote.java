package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "votes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String voteId;
    private String votingId;
    private String userId;
    private boolean vote;
    private double voteLat;
    private double voteLon;
    @Temporal(TemporalType.TIMESTAMP)
    private Date voteTimestamp;

    public Vote(
            final String votingId, final String userId, final boolean vote, final double voteLat,
            final double voteLon,
            final Date voteTimestamp
    ) {
        this.votingId = votingId;
        this.userId = userId;
        this.vote = vote;
        this.voteLat = voteLat;
        this.voteLon = voteLon;
        this.voteTimestamp = voteTimestamp;
    }
}
