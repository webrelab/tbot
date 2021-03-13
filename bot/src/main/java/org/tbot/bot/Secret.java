package org.tbot.bot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Secret {
    private final static String PRIVATE_URI = Objects.requireNonNull(
            Secret.class.getClassLoader().getResource(
                    "private")).getFile();
    public final static String BOT_TOKEN;
    public final static String BOT_NAME;
    public final static String DADATA_API_KEY;

    static {
        final Path path = Paths.get(PRIVATE_URI);
        final List<String> rows;
        try {
            rows = new ArrayList<>(Files.readAllLines(path));
        } catch (final IOException e) {
            throw new Error(e);
        }
        final Map<String, String> data = rows
                .stream()
                .collect(Collectors
                                 .toMap(
                                         row -> row.split("=", 2)[0].trim(),
                                         row -> row.split("=", 2)[1].trim()
                                 )
                );
        BOT_TOKEN = decode(data.get("token"));
        BOT_NAME = decode(data.get("name"));
        DADATA_API_KEY = decode(data.get("dadata.api"));
    }

    private static String decode(final String encoded) {
        return new String(
                Base64.getDecoder().decode(encoded),
                StandardCharsets.UTF_8
        ).trim();
    }
}
