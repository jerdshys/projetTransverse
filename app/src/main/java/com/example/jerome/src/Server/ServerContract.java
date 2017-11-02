package com.example.jerome.src.Server;

import java.util.ArrayList;

import sql.Models.Server;

/**
 * Created by jerome on 25/10/2017.
 */
public interface ServerContract {

    interface View  {
        void showServers(ArrayList<Server> servers);
        void openServerDetails();

    }

    interface Presenter  {
        void loadServers();
        void addNewServer();
    }
}