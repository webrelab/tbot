package org.tbot.bot.services;

import org.junit.Assert;
import org.junit.Test;
import org.tbot.bot.services.geo.GeoPosition;

public class GeoPositionServicesTest {
    @Test
    public void BackGeoCode() {
        final GeoPosition geoPosition = new GeoPosition(
                55.031558,
                82.923532
        );
        Assert.assertEquals("Новосибирск", geoPosition.getCity());
        Assert.assertEquals("г Новосибирск, ул Орджоникидзе, д 31", geoPosition.getAddress());
    }
}
