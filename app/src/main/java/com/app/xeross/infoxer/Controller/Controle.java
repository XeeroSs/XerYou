package com.app.xeross.infoxer.Controller;

import android.content.Context;

import com.app.xeross.infoxer.Model.AccesDistant;
import com.app.xeross.infoxer.View.ItemClassement;

import org.json.JSONArray;

/**
 * Created by XeroSs on 22/09/2018.
 */
public final class Controle {

    private static ItemClassement ic;
    private static Controle instance = null;
    private static AccesDistant accesDistant;

    public Controle() {
        super();
    }

    public static final Controle getInstance(Context context) {

        if (Controle.instance == null) {
            Controle.instance = new Controle();
            accesDistant = new AccesDistant();
            accesDistant.send("dernier", new JSONArray());
        }
        return Controle.instance;
    }

    public void createClassement(String classement, int image, String title, String statut, int color, Context c) {
        ic = new ItemClassement(classement, image, title, statut, color);
        accesDistant.send("enreg", ic.convertToJSONArray());
    }
}
