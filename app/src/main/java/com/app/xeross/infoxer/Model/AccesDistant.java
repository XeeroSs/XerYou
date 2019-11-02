package com.app.xeross.infoxer.Model;

import android.util.Log;

import com.app.xeross.infoxer.View.AsyncResponse;

import org.json.JSONArray;

/**
 * Created by XeroSs on 22/09/2018.
 */
public class AccesDistant implements AsyncResponse {

    // Constance.168.1.1
    private static final String SERVERADDR = "http://1925/infoxer/serveurinfoxer.php";

    public AccesDistant() {
        super();
    }

    // Retour du serveur distant
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "*******************" + output);
        // découpage du message reçu avec %
        String[] message = output.split("%");
        // dans message[0] : "Message", "Dernier", "Erreur !"
        // Dans message[1] : reste du message

        //s'il y a 2 cases
        if (message.length > 1) {
            if (message[0].equals("enreg")) {

                Log.d("enreg", "*****************" + message[1]);

            } else {

                if (message[0].equals("dernier")) {

                    Log.d("dernier", "*****************" + message[1]);

                } else {

                    if (message[0].equals("Erreur !")) {

                        Log.d("Erreur !", "*****************" + message[1]);

                    }
                }
            }
        }
    }

    public void send(String operation, JSONArray lesDonneesJSON) {
        AccesHTTP accesDonnees = new AccesHTTP();
        //lien de délégation
        accesDonnees.delegate = this;
        // ajout paramètre

        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());

        //appel au serveur
        accesDonnees.execute(SERVERADDR);
    }

}
