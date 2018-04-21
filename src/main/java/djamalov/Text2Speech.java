package djamalov;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Text2Speech {
    private static final String TEXT_TO_SPEECH_SERVICE =
            "https://tts.voicetech.yandex.net/generate";
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:11.0) " +
                    "Gecko/20100101 Firefox/11.0";


    public void go(String text, String fileName) throws Exception {
        // Create url based on input params
        if (text != null) {
            String strUrl = TEXT_TO_SPEECH_SERVICE + "?" +
                    "text=" + text + "&format=mp3&lang=ru-RU&speaker=oksana&emotion=good&key=8070a1c5-bcb8-4b93-8d51-1b8bd63efb24";
            URL url = new URL(strUrl);

            // Etablish connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Get method
            connection.setRequestMethod("GET");
            // Set User-Agent to "mimic" the behavior of a web browser. In this
            // example, I used my browser's info
            connection.addRequestProperty("User-Agent", USER_AGENT);
            connection.connect();

            // Get content
            BufferedInputStream bufIn =
                    new BufferedInputStream(connection.getInputStream());
            byte[] buffer = new byte[1024];
            int n;
            ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
            while ((n = bufIn.read(buffer)) > 0) {
                bufOut.write(buffer, 0, n);
            }
            File output = new File("D:\\"+ fileName +".mp3");
            BufferedOutputStream out =new BufferedOutputStream(new FileOutputStream(output));
            // Done, save data;
            out.write(bufOut.toByteArray());
            out.flush();
            bufOut.close();
            out.close();
            System.out.println("Done");
        }
    }
}
