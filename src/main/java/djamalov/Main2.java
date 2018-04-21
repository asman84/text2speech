package djamalov;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main2 {
    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(new AudioBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
