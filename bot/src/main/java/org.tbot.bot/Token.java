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

public class Token {
    private final static String PRIVATE_URI = Objects.requireNonNull(
            Token.class.getClassLoader().getResource(
                    "private")).getFile();
    public final static String TOKEN;
    public final static String NAME;

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
        TOKEN = decode(data.get("token"));
        NAME = decode(data.get("name"));
    }

    private static String decode(final String encoded) {
        return new String(
                Base64.getDecoder().decode(encoded),
                StandardCharsets.UTF_8
        ).trim();
    }
}
