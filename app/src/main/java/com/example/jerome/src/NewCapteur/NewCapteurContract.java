package com.example.jerome.src.NewCapteur;

/**
 * Created by jerome on 25/10/2017.
 */
public interface NewCapteurContract {

    interface View  {
        void clickAdd(String name, String description);

    }

    interface Presenter  {
        void saveServer(String name, String description);
    }
}