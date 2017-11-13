package com.example.jerome.src.NewServer;

import android.support.annotation.NonNull;

import com.example.jerome.src.Server.NewServerContract;
import com.example.jerome.src.ServerDetail.EditServerContract;

import sql.ServerRepository;

/**
 * Created by jerome on 13/11/2017.
 */
public class NewServerPresenter implements NewServerContract.Presenter {

    private ServerRepository mServerRepository;
    private NewServerContract.View mServerView;

    // le presenter doit connaître la vue
    public NewServerPresenter(@NonNull ServerRepository ServerRepository, @NonNull NewServerContract.View mNewServerView) {
        mNewServerView = mNewServerView;
        mServerRepository = ServerRepository;
    }


    @Override
    public void saveServer(String name, String description) {
        mServerRepository.post(name, description);
    }
}
