package com.example.jerome.src.NewCapteur;

/**
 * Created by jerome on 25/10/2017.
 */
public interface NewCapteurContract {

    interface View  {
        void clickAdd(String name);

    }

    interface Presenter  {
        void saveServer(String name);
    }
}