package com.br.bandtec.projetobandpetjava.dominio;

import java.io.IOException;

import com.br.bandtec.projetobandpetjava.dominio.Slack;
import org.json.JSONObject;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException, Exception {

        Slack slack = new Slack();
        JSONObject json = new JSONObject();

        json.put("text", " roda ai, namoral ? :shrug:");

        slack.sendMessage(json);
    }
}