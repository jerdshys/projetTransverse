package com.example.jerome.src.ServerDetail;

/**
 * Created by jerome on 25/10/2017.
 */
public interface EditServerContract {

    interface View  {
        void showServer();
    }

    interface Presenter  {
        void saveEditServer();
    }
}