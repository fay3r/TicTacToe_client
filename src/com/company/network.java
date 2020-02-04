package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jdi.connect.Connector;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class network {

    static int recursive = 0;

    public static Board result() throws Exception {

        //budujemy klineta jednorzaowego
        final HttpClient client = HttpClientBuilder.create().build();

        //podajemy link
        final HttpGet request = new HttpGet("http://127.0.0.1:8080/ticktacktoe/result");

        //obiekt do konwersacji json
        final Gson gson = new Gson();

        try
        {
            // Otrzymujemy odpowiedz od serwera.
            final HttpResponse response = client.execute(request);
            final HttpEntity entity = response.getEntity();

            // Na tym etapie odczytujemy JSON'a, ale jako String.
            final String json = EntityUtils.toString(entity);

            final Type type = new TypeToken<Board>() {}.getType();

            final Board files = gson.fromJson(json, type);

            System.out.println("Result - kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

            if (response.getStatusLine().getStatusCode() == 200)
            {
                System.err.println("Aktualizacja planszy");
            }

            return files;

        }
        catch (IOException e)
        {
            throw new Exception("Problem z zwróceniem JSONA");
        }
    }

    public static void reset() {

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/ticktacktoe/reset-game");


        try {

            final CloseableHttpResponse response = client.execute(httpPost);

            System.out.println("Reset - kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

            if (response.getStatusLine().getStatusCode() == 200)
            {
                System.err.println("Gra zresetowana");
            }

            client.close();
        } catch (UnsupportedEncodingException e) {

            System.out.println("Houston, we have a problem with POST unsupported encoding");
            e.printStackTrace();
        } catch (ClientProtocolException e) {

            System.out.println("Houston, we have a problem with POST client protocol");
            e.printStackTrace();
        } catch (IOException e) {

            System.out.println("Houston, we have a problem with POST input output");
            e.printStackTrace();
        }
    }

    public static void bot(char symbol) throws Exception{



        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/ticktacktoe/set-field-by-ai");

        Gson gson = new Gson();

        // Tworzymy obiekt uzytkownika
        final Bot userData = new Bot(symbol);

        // Serializacja obiektu do JSONa
        final String json = gson.toJson(userData);

        try {

            final StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            final CloseableHttpResponse response = client.execute(httpPost);

            System.out.println("Bot - kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

            if(recursive >= 10)
            {
                System.out.println("Bot - recursive problem");
                throw new Exception("Infinity loop");
            }


            if (response.getStatusLine().getStatusCode() == 400)
            {
                recursive++;
                System.err.println("com.company.Bot 404 trying again");
                bot(symbol);
            }
            else if (response.getStatusLine().getStatusCode() == 200)
            {
                System.err.println("Ruch bota");
            }

            client.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Houston, we have a problem with POST unsupported encoding");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("Houston, we have a problem with POST client protocol");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Houston, we have a problem with POST input output");
            e.printStackTrace();
        }
    }

    public static void user(final User userData) throws Exception{

        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/ticktacktoe/set-field-by-user");

        Gson gson = new Gson();

        // Serializacja obiektu do JSONa
        final String json = gson.toJson(userData);

        try {

            final StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            final CloseableHttpResponse response = client.execute(httpPost);

            System.out.println("User - kod odpowiedzi serwera: " + response.getStatusLine().getStatusCode());

            if(recursive >= 10)
            {
                System.out.println("Bot - recursive problem");
                throw new Exception("Infinity loop");
            }

            if (response.getStatusLine().getStatusCode() == 400)
            {
                recursive++;
                System.err.println("User 400 trying again");
                user(userData);
            }
            else if (response.getStatusLine().getStatusCode() == 200)
            {
                System.err.println("Ruch bota");
            }

            client.close();
        } catch (UnsupportedEncodingException e) {

            System.out.println("Houston, we have a problem with POST unsupported encoding");
            e.printStackTrace();
        } catch (ClientProtocolException e) {

            System.out.println("Houston, we have a problem with POST client protocol");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Houston, we have a problem with POST input output");
            e.printStackTrace();
        }
    }
}