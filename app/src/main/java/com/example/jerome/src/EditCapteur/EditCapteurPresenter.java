package com.example.jerome.src.EditCapteur;

import android.support.annotation.NonNull;

import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 26/10/2017.
 */
public class EditCapteurPresenter implements EditCapteurContract.Presenter {

    private EditCapteurContract.View mEditServerView;
    private ServerRepository mServerRepository;

    // le presenter doit connaître la vue
    public EditCapteurPresenter(@NonNull ServerRepository ServerRepository, @NonNull EditCapteurContract.View editServerView) {
        mEditServerView = editServerView;
        mServerRepository = ServerRepository;
        mEditServerView.showServerDetail( mServerRepository.get(Long.parseLong(mEditServerView.getServerId())));

    }

    @Override
    public void saveEditServer(Long id, String name, String description) {
        mServerRepository.put(id, name, description);
    }

}
