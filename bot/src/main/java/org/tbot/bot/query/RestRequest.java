package org.tbot.bot.query;

import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tbot.rest.repositories.*;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RestRequest {
    private static final String REST_URI = "http://localhost:8080/";
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    private static final String CONTENT_TYPE_JSON = MediaType.JSON_UTF_8.toString();
    private static final String CONTENT_TYPE_URI_LIST = "text/uri-list";

    // Генерация GET запроса с набором параметров (ключ / значение)
    public static  <T> T get(
            final Class<? extends Repository<T, String>> repository,
            final Class<T> tClass,
            final Map<String, String> params
    ) {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final RequestBuilder builder = RequestBuilder.get(REST_URI + getRepositoryPath(repository));
        builder.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);
        for (final Map.Entry<String, String> param : params.entrySet()) {
            builder.addParameter(param.getKey(), param.getValue());
        }
        final HttpResponse response = execute(httpClient, builder.build());
        return gson.fromJson(getResponseContent(response), tClass);
    }

    // Генерация GET запроса с генерацией пути из массива частей
    public static <T> T get(
            final Class<? extends Repository<?, String>> repository,
            final Class<T> tClass,
            final String... parts
    ) {
        final String path = REST_URI + getRepositoryPath(repository) + String.join("/", parts);
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final RequestBuilder builder = RequestBuilder.get(path);
        builder.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);
        final HttpResponse response = execute(httpClient, builder.build());
        return gson.fromJson(getResponseContent(response), tClass);
    }

    public static <T> T getById(
            final Class<? extends Repository<T, String>> repository,
            final Class<T> tClass,
            final String id
    ) {
        final String path = REST_URI + getRepositoryPath(repository) + id;
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(path);
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);
        final HttpResponse response = execute(httpClient, httpGet);
        return gson.fromJson(getResponseContent(response), tClass);
    }

    // создание связки сформированной группы и пользователя
    public static void putFormedGroupToUserAssociation(final String formedGroupId, final String userId) {
        put(FormedGroupRepository.class, formedGroupId, "users", UserRepository.class, userId);
    }

    // создание связки функциональной роли и пользователя
    public static void putFunctionalRoleToUserAssociation(final String functionalRoleId, final String userId) {
        put(FunctionalRoleRepository.class, functionalRoleId, "users", UserRepository.class, userId);
    }

    // создание связки запланированной акции и городской цели
    public static void putActionToCityGoalAssociation(final String actionId, final String cityGoalId) {
        put(ActionRepository.class, actionId, "cityGoals", CityGoalRepository.class, cityGoalId);
    }

    // выполнение запроса для связывания двух сущностей
    private static void put(
            final Class<? extends Repository<?, String>> repository1,
            final String id1,
            final String relationFieldName,
            final Class<? extends Repository<?, String>> repository2,
            final String id2
    ) {
        final String rel = REST_URI + getRepositoryPath(repository2) + id2;
        final String path = REST_URI + getRepositoryPath(repository1) + id1 + "/" + relationFieldName;
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final RequestBuilder builder =
                RequestBuilder
                        .put(path)
                        .addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_URI_LIST)
                        .setEntity(new StringEntity(rel, StandardCharsets.UTF_8));
        final HttpResponse response = execute(httpClient, builder.build());
    }

    public static <T> String post(
            final Class<? extends Repository<T, String>> repository,
            final T object
    ) {
        final String path = REST_URI + getRepositoryPath(repository);
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final RequestBuilder builder = RequestBuilder.post(path)
                .addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON)
                .setEntity(new StringEntity(gson.toJson(object), StandardCharsets.UTF_8));
        final HttpResponse response = execute(httpClient, builder.build());
        return getEntityId(response);
    }

    public static void deleteById(
            final Class<? extends Repository<?, String>> repository,
            final String entityId
    ) {
        final String path = REST_URI + getRepositoryPath(repository) + entityId;
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final RequestBuilder builder = RequestBuilder.delete(path);
        execute(httpClient, builder.build());
    }

    private static HttpResponse execute(final CloseableHttpClient httpClient,
            final HttpUriRequest request) {
        final HttpResponse response;
        try {
            response = httpClient.execute(request);
        } catch (final IOException e) {
            throw new HTTPError(e);
        }
        check(response);
        return response;
    }

    public static String getRepositoryPath(final Class<? extends Repository<?, String>> repository) {
        if (repository.isAnnotationPresent(RepositoryRestResource.class)) {
            return repository.getAnnotation(RepositoryRestResource.class).path() + "/";
        }
        throw new RepositoryError("Для репозитория не определён параметр 'path' " + repository.getName());
    }

    private static String getEntityId(
            final HttpResponse response
    ) {
        final String[] parts = response.getFirstHeader("Location").toString().split("/");
        return parts[parts.length - 1];
    }


    private static void check(final HttpResponse response) {
        if (response.getStatusLine().getStatusCode() > HttpStatus.SC_MULTI_STATUS) {
            throw new HTTPError(getResponseContent(response));
        }
    }

    static String toCamelCase(final String value) {
        final StringBuilder output = new StringBuilder();
        boolean capitalizeNext = false;
        for (int i = 0; i < value.length(); i++) {
            final char thisChar = value.charAt(i);
            if (thisChar == '-') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                output.append(String.valueOf(thisChar).toUpperCase());
                capitalizeNext = false;
            } else {
                output.append(thisChar);
                capitalizeNext = false;
            }
        }
        return output.toString();
    }

    private static String getResponseContent(final HttpResponse response) {
        final StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(response.getEntity().getContent(), writer, StandardCharsets.UTF_8);
            return writer.toString();
        } catch (final IOException e) {
            throw new HTTPError(e);
        }
    }
}
