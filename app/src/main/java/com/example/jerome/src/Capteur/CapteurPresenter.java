package com.example.jerome.src.Capteur;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import sql.Models.Capteur;
import sql.Models.Server;
import sql.Repositories.HttpCapteurRepository;
import sql.Repositories.ServerRepository;
import sql.VolleyCallback;

/**
 * Created by jerome on 26/10/2017.
 */
public class CapteurPresenter implements CapteurContract.Presenter {

    private HttpCapteurRepository mCapteurRepository;
    private CapteurContract.View mServerView;


    public CapteurPresenter(@NonNull HttpCapteurRepository mCapteurRepository, @NonNull CapteurContract.View mServerView) {
        this.mCapteurRepository =  mCapteurRepository;
        this.mServerView = mServerView;
        this.loadCapteurs();
    }

    @Override
    public void loadCapteurs() {
        final VolleyCallback cb;
        mCapteurRepository.getAll(new VolleyCallback(){
            ArrayList<Capteur> capteurs = new ArrayList<Capteur>();

            @Override
            public void onSuccessResponse(String result) {
                JSONArray jsonarray = null;
                try {
                    jsonarray = new JSONArray(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = null;
                    try {
                        jsonobject = jsonarray.getJSONObject(i);
                        try {
                            String id =jsonobject.getString("_id");
                            String name = jsonobject.getString("name");
                            capteurs.add(new Capteur(id,name));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                mServerView.showCapteurs(capteurs);


            }

        });
    }

    @Override
    public void addNewCapteur() {

    }

    @Override
    public void deleteCapteur(String id) {
        mCapteurRepository.delete(id);
        this.loadCapteurs();
    }





}
