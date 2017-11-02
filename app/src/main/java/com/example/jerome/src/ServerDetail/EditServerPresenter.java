package com.example.jerome.src.ServerDetail;

/**
 * Created by jerome on 26/10/2017.
 */
public class EditServerPresenter implements EditServerContract.Presenter {

    private EditServerContract.View mEditServerView;

    // le presenter doit connaître la vue
    public EditServerPresenter(EditServerContract.View editServerView) {
        mEditServerView = editServerView;
    }

    @Override
    public void saveEditServer() {

    }

}
