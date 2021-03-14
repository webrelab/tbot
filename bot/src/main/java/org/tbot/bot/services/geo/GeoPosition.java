package org.tbot.bot.services.geo;

import com.google.common.net.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.tbot.bot.Secret;
import org.tbot.bot.query.HTTPError;
import org.tbot.bot.query.RestRequest;
import org.tbot.bot.services.resources.ResourceLoader;
import org.tbot.rest.entities.City;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Сервис реализует возможность создания объекта геопозиции по широте и долготе.
 * При создании объекта выполняется запрос в сервис DaData для получения ближайшего к геопозиции адреса
 */
public class GeoPosition {
    private final double lat;
    private final double lon;
    private final int count = 1;
    private final int radius_meters = 100;

    private transient JsonArray addressList;
    private final transient static String REQUEST_URL = "https://suggestions.dadata" +
            ".ru/suggestions/api/4_1/rs/geolocate/address";

    public GeoPosition(final double lat, final double lon) {
        this.lat = lat;
        this.lon = lon;
        request();
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public JsonArray getAddressList() {
        return addressList;
    }

    public String getCity() {
        return addressList.get(0).getAsJsonObject().getAsJsonObject("data").getAsJsonPrimitive("city").getAsString();
    }

    public String getAddress() {
        return addressList.get(0).getAsJsonObject().getAsJsonPrimitive("value").getAsString();
    }

    public String getArea() {
        return addressList.get(0).getAsJsonObject().getAsJsonObject("data").getAsJsonPrimitive("region").getAsString();
    }

    public String getFederalDistrict() {
        return addressList.get(0).getAsJsonObject().getAsJsonObject("data").getAsJsonPrimitive("federal_district").getAsString();
    }

    public City createCityEntity() {
        final Optional<String> population = ResourceLoader.get(ResourceLoader.Res.POPULATION, getCity());
        return new City(
                getCity(),
                getArea(),
                getFederalDistrict(),
                population.map(Integer::parseInt).orElse(-1),
                lon,
                lat
        );
    }

    private void request() {
        final CloseableHttpClient client = HttpClients.createDefault();
        final RequestBuilder builder = RequestBuilder.post(REQUEST_URL);
        builder.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.JSON_UTF_8.toString())
                .addHeader(HttpHeaders.ACCEPT, MediaType.JSON_UTF_8.toString())
                .addHeader(HttpHeaders.AUTHORIZATION, "Token " + Secret.DADATA_API_KEY)
                .setEntity(new StringEntity(getQuery(), StandardCharsets.UTF_8));
        final Optional<HttpResponse> response = RestRequest.execute(client, builder.build());
        final JsonElement jsonElement = RestRequest.getResponseContent(response.orElseThrow(HTTPError::new));
        addressList = jsonElement.getAsJsonObject().getAsJsonArray("suggestions");
    }

    private String getQuery() {
        return new Gson().toJson(this);
    }
}
