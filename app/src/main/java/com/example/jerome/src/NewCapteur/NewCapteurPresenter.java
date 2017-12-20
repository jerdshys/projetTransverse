package com.example.jerome.src.NewCapteur;

import android.support.annotation.NonNull;

import sql.Repositories.HttpCapteurRepository;
import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 13/11/2017.
 */
public class NewCapteurPresenter implements NewCapteurContract.Presenter {

    private HttpCapteurRepository mCapteurRepository;
    private NewCapteurContract.View mServerView;

    // le presenter doit connaître la vue
    public NewCapteurPresenter(@NonNull HttpCapteurRepository mCapteurRepository, @NonNull NewCapteurContract.View mNewServerView) {
        mNewServerView = mNewServerView;
        mCapteurRepository = mCapteurRepository;
    }

    @Override
    public void saveServer(String name) {
        mCapteurRepository.post(name);
    }
}
