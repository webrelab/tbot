package org.tbot.bot.query;

import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.springframework.data.repository.Repository;

import java.util.*;

public class RestSearch {
    private static final String REST_URI = "http://localhost:8080/";
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

    public static <T> List<Pair<T>> searchList(
            final Class<? extends Repository<T, String>> repository,
            final Class<T> tClass,
            final QueryNames queryName,
            final String... values
    ) {
        Assert.assertTrue(queryName.isMultiple());
        final Optional<HttpResponse> response = getResponse(repository, queryName, values);
        Assert.assertTrue(response.isPresent());
        return getEntityList(repository, tClass, RestRequest.getResponseContent(response.get()));

    }

    public static <T> Optional<Pair<T>> searchSingle(
            final Class<? extends Repository<T, String>> repository,
            final Class<T> tClass,
            final QueryNames queryNames,
            final String... values
    ) {
        Assert.assertFalse(queryNames.isMultiple());
        final Optional<HttpResponse> response = getResponse(repository, queryNames, values);
        return response.map(r -> getPair(RestRequest.getResponseContent(r), tClass));
    }

    private static <T> Optional<HttpResponse> getResponse(
            final Class<? extends Repository<T, String>> repository,
            final QueryNames queryNames,
            final String... values
    ) {
        Assert.assertEquals(queryNames.getParam().length, values.length);
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final RequestBuilder builder = RequestBuilder.get(
                REST_URI + String.join("/", new String[]{
                        RestRequest.getSlashedRepositoryPath(repository),
                        "search",
                        queryNames.getQueryName()
                }));
        for (int i = 0; i < values.length; i++) {
            builder.addParameter(queryNames.getParam()[i], values[i]);
        }
        return RestRequest.execute(httpClient, builder.build());
    }

    private static <T> List<Pair<T>> getEntityList(
            final Class<? extends Repository<T, String>> repository,
            final Class<T> tClass,
            final JsonElement jsonElement
    ) {
        final List<Pair<T>> entityList = new ArrayList<>();
        final JsonElement embedded = jsonElement
                .getAsJsonObject()
                .getAsJsonObject("_embedded");
        if (embedded.getAsJsonObject().has(RestRequest.getRepositoryPath(repository))) {
            for (final JsonElement element : embedded.getAsJsonObject().getAsJsonArray(RestRequest.getRepositoryPath(repository))) {
                entityList.add(getPair(element, tClass));
            }
        }
        return entityList;
    }

    private static Map<String, String> mapOf(final String key, final String value) {
        final Map<String, String> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    private static <T> Pair<T> getPair(final JsonElement jsonElement, final Class<? extends T> tClass) {
        final String[] parts = jsonElement
                        .getAsJsonObject()
                        .getAsJsonObject("_links")
                        .getAsJsonObject("self")
                        .getAsJsonPrimitive("href")
                        .getAsString()
                .split("/");
        return new Pair<>(parts[parts.length - 1], gson.fromJson(jsonElement, tClass));
    }
}
