package com.example.jerome.src.Capteur;

import java.util.ArrayList;

import sql.Models.Capteur;
import sql.Models.Server;

/**
 * Created by jerome on 25/10/2017.
 */
public interface CapteurContract {

    interface View  {
        void showCapteurs(ArrayList<Capteur> capteurs);
        void openCapteurDetails();
        void openNewCapteur();
    }

    interface Presenter  {
        void loadCapteurs();
        void addNewCapteur();
        void deleteCapteur(String id);
    }
}