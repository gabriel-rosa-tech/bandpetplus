package com.br.bandtec.projetobandpetjava.dominio;

import org.json.JSONObject;

import java.io.IOException;

public class ChamadaSlack {

    public void enviarMensagemPronto() throws IOException, InterruptedException, Exception{
        Slack slack = new Slack();

        JSONObject json = new JSONObject();

        String mensagem = String.format("Seu pet esta pronto! ja pode ir retirar");
        json.put("text", mensagem);

        slack.sendMessage(json);
    }

    public void enviarMsnCartao(String cliente)throws IOException, InterruptedException, Exception{
        Slack slack = new Slack();

        JSONObject json = new JSONObject();

        String mensagem = String.format("O cliente, %s, atigiu completou o cartao fidelidade," +
                " ele merece seu premio", cliente);
        json.put("text", mensagem);

        slack.sendMessage(json);
    }
}
