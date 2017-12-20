package com.example.jerome.src.Capteur;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import sql.Models.Server;
import sql.Repositories.HttpCapteurRepository;
import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 26/10/2017.
 */
public class CapteurPresenter implements CapteurContract.Presenter {

    private HttpCapteurRepository mCapteurRepository;
    private CapteurContract.View mServerView;


    public CapteurPresenter(@NonNull HttpCapteurRepository mCapteurRepository, @NonNull CapteurContract.View mServerView) {
        this.mCapteurRepository =  mCapteurRepository;
        this.mServerView = mServerView;
        this.loadServers();
    }

    @Override
    public void loadServers() {
       ArrayList<Server> servers = mCapteurRepository.getAll();
       this.mServerView.showServers(servers);
    }

    @Override
    public void addNewServer() {

    }

    @Override
    public void deleteServer(Long id) {
        mCapteurRepository.delete(id);
        this.loadServers();
    }





}
