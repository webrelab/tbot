package org.tbot.bot.services.resources;

public class ResourceLoaderError extends RuntimeException {
    public ResourceLoaderError(final Throwable e) {
        super(e);
    }
}
