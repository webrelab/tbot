package org.tbot.bot.services.resources;

import org.tbot.bot.Secret;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ResourceLoader {
    private final EnumMap<Res, Object> loadedRes = new EnumMap<>(Res.class);
    private static ResourceLoader instance;

    private ResourceLoader() {

    }

    private static ResourceLoader getInstance() {
        if (instance == null) {
            instance = new ResourceLoader();
        }
        return instance;
    }

    public static Optional<String> get(final Res res, final String key) {
        return getInstance().getMap(res).containsKey(key) ? Optional.of(getInstance().getMap(res).get(key)) : Optional.empty();
    }

    private Map<String, String> getMap(final Res res) {
        if (!loadedRes.containsKey(res)) {
            loadMap(res);
        }
        return (Map<String, String>) loadedRes.get(res);
    }

    private void loadMap(final Res res) {
        final Map<String, String> map = new HashMap<>();
        final List<String> rows;
        try {
           rows = Files.readAllLines(getResPath(res));
        } catch (final IOException e) {
            throw new ResourceLoaderError(e);
        }
        rows.forEach(row -> {
            map.put(row.split("=")[0].trim(), row.split("=")[1].trim());
        });
        loadedRes.put(res, map);
    }

    private Path getResPath(final Res res) {
        return Paths.get(Objects.requireNonNull(
                Secret.class.getClassLoader().getResource(
                        res.path)).getFile());
    }

    public enum Res {
        POPULATION("population.txt");

        private final String path;

        public String getPath() {
            return path;
        }

        Res(final String path) {
            this.path = path;
        }
    }
}
