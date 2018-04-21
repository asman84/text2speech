package djamalov;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendAudio;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.*;
import java.net.URLEncoder;

public class AudioBot extends TelegramLongPollingBot {
    String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    Text2Speech convertor = new Text2Speech();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text = message.getText();
        String[] textSplitted = text.split(" ");
        this.fileName = textSplitted[0];
        try {
            text = URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendAudio(message, text,"sizni tekstingiz audiosi: ");
    }

    private void sendAudio(Message message, String text,String s) {
        try {
            convertor.go(text,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SendAudio audio = new SendAudio();
        audio.setChatId(message.getChatId().toString());
        audio.setReplyToMessageId(message.getMessageId());
        audio.setNewAudio(new File("D:\\"+ fileName +".mp3"));
        try {
            sendAudio(audio);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "firstBot";
    }

    @Override
    public String getBotToken() {
        return "514476766:AAFzaSa3p3f9z5IT5fJkkg4VnWKJC6dHTNc";
    }
}
