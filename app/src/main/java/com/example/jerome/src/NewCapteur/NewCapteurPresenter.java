package com.example.jerome.src.NewCapteur;

import android.support.annotation.NonNull;

import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 13/11/2017.
 */
public class NewCapteurPresenter implements NewCapteurContract.Presenter {

    private ServerRepository mServerRepository;
    private NewCapteurContract.View mServerView;

    // le presenter doit connaître la vue
    public NewCapteurPresenter(@NonNull ServerRepository ServerRepository, @NonNull NewCapteurContract.View mNewServerView) {
        mNewServerView = mNewServerView;
        mServerRepository = ServerRepository;
    }

    @Override
    public void saveServer(String name, String description) {
        mServerRepository.post(name, description);
    }
}
