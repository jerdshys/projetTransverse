package com.example.jerome.src.Capteur;

import java.util.ArrayList;

import sql.Models.Server;

/**
 * Created by jerome on 25/10/2017.
 */
public interface CapteurContract {

    interface View  {
        void showServers(ArrayList<Server> servers);
        void openServerDetails();
        void openNewServer();
    }

    interface Presenter  {
        void loadServers();
        void addNewServer();
        void deleteServer(Long id);
    }
}