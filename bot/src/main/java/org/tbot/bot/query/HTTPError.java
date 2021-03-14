package org.tbot.bot.query;

public class HTTPError extends RuntimeException {
    public HTTPError() {
        super();
    }
    public HTTPError(final String message) {
        super(message);
    }

    public HTTPError(final Throwable e) {
        super(e);
    }
}
