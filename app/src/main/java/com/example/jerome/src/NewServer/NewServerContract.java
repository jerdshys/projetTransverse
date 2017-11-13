package com.example.jerome.src.Server;

import java.util.ArrayList;
import java.util.List;

import sql.Models.Server;

/**
 * Created by jerome on 25/10/2017.
 */
public interface NewServerContract {

    interface View  {
        void clickAdd(String name, String description);

    }

    interface Presenter  {
        void saveServer(String name, String description);
    }
}