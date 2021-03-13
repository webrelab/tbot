package org.tbot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        updates.forEach(update -> {
            if (update.hasMessage() && update.getMessage().hasText()) {
                final SendMessage message = new SendMessage(); // Create a
                // SendMessage object with mandatory fields
                message.setChatId(update.getMessage().getChatId().toString());
                message.setText(update.getMessage().getText());
                try {
                    execute(message); // Call method to send the message
                } catch (final TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String getBotUsername() {
        return Secret.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Secret.BOT_TOKEN;
    }
}
