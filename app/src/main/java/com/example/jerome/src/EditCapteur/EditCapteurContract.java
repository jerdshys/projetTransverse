package com.example.jerome.src.EditCapteur;

import android.support.annotation.NonNull;

import sql.Models.Server;

/**
 * Created by jerome on 25/10/2017.
 */
public interface EditCapteurContract {

    interface View  {
        void showServerDetail(@NonNull Server server);
        String getServerId();
    }

    interface Presenter  {
        void saveEditServer(Long id, String name, String description);
    }
}