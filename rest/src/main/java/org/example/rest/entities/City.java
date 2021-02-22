package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public City(String cityName, String federalDistrict, int population, double cityLon, double cityLat) {
        this.cityName = cityName;
        this.federalDistrict = federalDistrict;
        this.population = population;
        this.cityLon = cityLon;
        this.cityLat = cityLat;
    }
}
