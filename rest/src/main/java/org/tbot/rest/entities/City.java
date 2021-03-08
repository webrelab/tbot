package org.tbot.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class City {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String cityId;
    private String cityName;
    private String federalDistrict;
    private int population;
    private double cityLon;
    private double cityLat;

    public City(
            final String cityName,
            final String federalDistrict,
            final int population,
            final double cityLon,
            final double cityLat
    ) {
        this.cityName = cityName;
        this.federalDistrict = federalDistrict;
        this.population = population;
        this.cityLon = cityLon;
        this.cityLat = cityLat;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final City that = (City) o;
        return Objects.equals(cityId, that.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId);
    }
}
