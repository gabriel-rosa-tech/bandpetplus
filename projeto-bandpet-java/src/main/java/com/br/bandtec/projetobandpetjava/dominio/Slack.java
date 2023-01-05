package com.br.bandtec.projetobandpetjava.dominio;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Slack {

    private String url = "https://hooks.slack.com/services/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";

    public void sendMessage(JSONObject message) throws Exception {

        URL obj = new URL(this.url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(message.toString());

        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        System.out.println("Sending 'POST' request to URL: " + this.url);
        System.out.println("POST parameters: " + message.toString());
        System.out.println("Response Code: " + responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        reader.close();
        System.out.println("Success.");
    }
}
