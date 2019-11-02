package com.app.xeross.infoxer.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.app.xeross.infoxer.View.AsyncResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by XeroSs on 16/09/2018.
 */
public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> param;
    private String ret = null;
    public AsyncResponse delegate = null;

    // Constructeur
    public AccesHTTP() {
        param = new ArrayList<NameValuePair>();
    }

    // ajout d'un paramètre post
    public void addParam(String name, String val) {

        param.add(new BasicNameValuePair(name, val));

    }

    // Connexion en tâche de fon dans un thread séparé
    @Override
    protected Long doInBackground(String... strings) {

        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {

            // Encodage des paramètre
            paramCnx.setEntity(new UrlEncodedFormEntity(param));
            //Connexion et envoie de paramètre, attente de réponse
            HttpResponse rep = cnxHttp.execute(paramCnx);

            // Transformation de la réponse
            ret = EntityUtils.toString(rep.getEntity());

        } catch (IOException e) {
            Log.d("Erreur I/C", "**************************" + e.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Long result) {
        delegate.processFinish((ret.toString()));
    }

}
