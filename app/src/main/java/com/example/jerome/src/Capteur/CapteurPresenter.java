package com.example.jerome.src.Capteur;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import sql.Models.Server;
import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 26/10/2017.
 */
public class CapteurPresenter implements CapteurContract.Presenter {

    private ServerRepository mServerRepository;
    private CapteurContract.View mServerView;


    public CapteurPresenter(@NonNull ServerRepository mServerRepository, @NonNull CapteurContract.View mServerView) {
        this.mServerRepository =  mServerRepository;
        this.mServerView = mServerView;
        this.loadServers();
    }

    @Override
    public void loadServers() {
       ArrayList<Server> servers = mServerRepository.getAll();
       this.mServerView.showServers(servers);
    }

    @Override
    public void addNewServer() {

    }

    @Override
    public void deleteServer(Long id) {
        mServerRepository.delete(id);
        this.loadServers();
    }





}
