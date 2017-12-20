package com.example.jerome.src.NewCapteur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jerome.src.R;
import com.example.jerome.src.Server.ServerActivity;

import sql.Repositories.HttpCapteurRepository;
import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 13/11/2017.
 */
public class NewCapteurFragment extends Fragment implements NewCapteurContract.View {

    private NewCapteurContract.Presenter mPresenter;
    private Button mButtonAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HttpCapteurRepository repo = new HttpCapteurRepository(this.getContext());
        mPresenter = new NewCapteurPresenter(repo, this);
        return inflater.inflate(R.layout.new_capteur_fragment, container, false);
    }



    public void onViewCreated(View view, Bundle savedInstanceState) {
        mButtonAdd = (Button) getView().findViewById(R.id.buttonAdd);
        final EditText mNameAdd = (EditText) getView().findViewById(R.id.nameAdd);
        final EditText mDescriptionAdd = (EditText) getView().findViewById(R.id.descriptionAdd);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAdd(mNameAdd.getText().toString());
            }
        });
    }
    @Override
    public void clickAdd(String name) {
        mPresenter.saveServer(name);
        Intent i = new Intent(getContext(),ServerActivity.class);
        startActivity(i);

    }
}
