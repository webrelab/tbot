package org.tbot.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "ambush_warnings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AmbushWarning {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String ambushWarningId;
    private String formedGroupId;
    private String ambushAddress;
    private double ambushLocationLat;
    private double ambushLocationLon;

    public AmbushWarning(
            final String formedGroupId, final String ambushAddress,
            final double ambushLocationLat,
            final double ambushLocationLon
    ) {
        this.formedGroupId = formedGroupId;
        this.ambushAddress = ambushAddress;
        this.ambushLocationLat = ambushLocationLat;
        this.ambushLocationLon = ambushLocationLon;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final AmbushWarning that = (AmbushWarning) o;
        return Objects.equals(ambushWarningId, that.ambushWarningId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ambushWarningId);
    }
}
