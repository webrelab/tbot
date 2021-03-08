package org.tbot.bot.query;

public class RepositoryError extends RuntimeException{
    public RepositoryError(final String message) {
        super(message);
    }
}
