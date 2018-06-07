package com.example.esgi.jmdoudoux_android;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WebService {

    private final String URI = "http://10.33.3.96:8080/ngrams";

    Gson gson;

    public WebService() {
        gson = new Gson();
    }

    private InputStream sendRequest(URL url) throws Exception {

        try {
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            Log.e("WebService",  e.getMessage());
        }
        return null;
    }

    public List<NGram> getNGRAm() {

        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(URI));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                return gson.fromJson(reader, new TypeToken<List<NGram>>(){}.getType());
            }

        } catch (Exception e) {
            Log.e("WebService", "error : " + e.getMessage() +"\n" + e.getStackTrace().toString());
        }
        return null;
    }
}