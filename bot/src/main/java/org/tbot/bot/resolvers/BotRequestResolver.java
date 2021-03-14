package org.tbot.bot.resolvers;

import org.telegram.telegrambots.meta.api.objects.Update;

public class BotRequestResolver {

    public static String resolveRequest(final Update update) {
        final Commands command = Commands.of(update.getMessage().getText());
        switch (command) {
            case START:
                final UserBotResolver resolver = new UserBotResolver(update);
                final String userId = resolver.registerUser();
        }

        return "";
    }

    public enum Commands {
        START("start"),
        UNDEFINED("undefined");

        private final String command;

        Commands(final String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }

        public static Commands of(final String name) {
            for (final Commands command : Commands.values()) {
                if (command.command.equalsIgnoreCase(name)) {
                    return command;
                }
            }
            return UNDEFINED;
        }
    }
}
