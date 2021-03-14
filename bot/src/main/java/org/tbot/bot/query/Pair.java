package org.tbot.bot.query;

public class Pair<T> {
    private final String id;
    private final T entity;

    public Pair(final String id, final T entity) {
        this.id = id;
        this.entity = entity;
    }

    public String getId() {
        return id;
    }

    public T getEntity() {
        return entity;
    }
}
