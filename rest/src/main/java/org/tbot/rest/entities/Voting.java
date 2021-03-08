package org.tbot.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "voting")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Voting {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String votingId;
    private String cityId;
    private String entityId;
    private String entityType;
    private String role;
    private String functionalRoleId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date votingStartDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date votingEndDate;
    private int votingNumberOfAccept;
    private int votingNumberOfDecline;
    private int votingNumberOfUsers;
    private boolean votingResult;

    public Voting(
            final String cityId, final String entityId, final String entityType, final String role,
            final String functionalRoleId,
            final Date votingStartDate,
            final Date votingEndDate,
            final int votingNumberOfAccept,
            final int votingNumberOfDecline,
            final int votingNumberOfUsers,
            final boolean votingResult
    ) {
        this.cityId = cityId;
        this.entityId = entityId;
        this.entityType = entityType;
        this.role = role;
        this.functionalRoleId = functionalRoleId;
        this.votingStartDate = votingStartDate;
        this.votingEndDate = votingEndDate;
        this.votingNumberOfAccept = votingNumberOfAccept;
        this.votingNumberOfDecline = votingNumberOfDecline;
        this.votingNumberOfUsers = votingNumberOfUsers;
        this.votingResult = votingResult;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final Voting that = (Voting) o;
        return Objects.equals(votingId, that.votingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(votingId);
    }
}
