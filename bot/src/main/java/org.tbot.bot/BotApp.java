package org.tbot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class BotApp extends TelegramLongPollingBot {

    @Override
    public void onRegister() {

    }

    @Override
    public void onUpdateReceived(final Update update) {

    }

    @Override
    public void onUpdatesReceived(final List<Update> updates) {

    }

    @Override
    public String getBotUsername() {
        return Token.NAME;
    }

    @Override
    public String getBotToken() {
        return Token.TOKEN;
    }
}
