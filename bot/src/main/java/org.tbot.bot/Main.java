package org.tbot.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(final String[] args) {
        try {
            final TelegramBotsApi api =
                    new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new BotApp());
        } catch (final TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
