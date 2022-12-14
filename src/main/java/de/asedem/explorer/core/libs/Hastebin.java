package de.asedem.explorer.core.libs;

import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Hastebin {

    @NotNull
    public String post(@NotNull String text, boolean raw) throws IOException {

        byte[] postData = text.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        String requestURL = "https://hastebin.com/documents";
        URL url = new URL(requestURL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Hastebin Java Api");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connection.setUseCaches(false);

        String response = null;
        DataOutputStream outputStream;

        try {
            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(postData);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        assert response != null;
        if (response.contains("\"key\"")) {
            response = response.substring(response.indexOf(":") + 2, response.length() - 2);

            String postURL = raw ? "https://hastebin.com/raw/" : "https://hastebin.com/";
            response = postURL + response;
        }

        return response;
    }
}
