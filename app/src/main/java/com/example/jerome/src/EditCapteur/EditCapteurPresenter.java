package com.example.jerome.src.EditCapteur;

import android.support.annotation.NonNull;

import sql.Repositories.HttpCapteurRepository;
import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 26/10/2017.
 */
public class EditCapteurPresenter implements EditCapteurContract.Presenter {

    private EditCapteurContract.View mEditServerView;
    private HttpCapteurRepository mCapteurRepository;

    // le presenter doit connaître la vue
    public EditCapteurPresenter(@NonNull HttpCapteurRepository CapteurRepository, @NonNull EditCapteurContract.View editServerView) {
        mEditServerView = editServerView;
        mCapteurRepository = CapteurRepository;
        mEditServerView.showServerDetail( mCapteurRepository.get(mEditServerView.getServerId()));

    }

    @Override
    public void saveEditServer(String id, String name) {
        mCapteurRepository.put(id, name);
    }

}
