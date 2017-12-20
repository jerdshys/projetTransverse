package com.example.jerome.src.EditCapteur;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jerome.src.R;

import sql.Models.Capteur;
import sql.Models.Server;
import sql.Repositories.HttpCapteurRepository;
import sql.Repositories.ServerRepository;



public class EditCapteurFragment extends Fragment implements EditCapteurContract.View {

    private EditCapteurContract.Presenter mPresenter;
    private EditText mTitleEdit;
    private EditText mDescriptionEdit;
    private Button mButtonEdit,mButtonBack;

    public EditCapteurFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_capteur_fragment, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        HttpCapteurRepository repo = new HttpCapteurRepository(this.getContext());
        mPresenter = new EditCapteurPresenter(repo , this);
        mTitleEdit = (EditText) getView().findViewById(R.id.nameEdit);
        mDescriptionEdit = (EditText) getView().findViewById(R.id.descriptionEdit);
    }

    public String getServerId() {
        return getActivity().getIntent().getStringExtra("id");
    }

    public void showServerDetail(Capteur capteur) {
        final Capteur s = capteur;
        mTitleEdit = (EditText) getView().findViewById(R.id.nameEdit);
        mDescriptionEdit = (EditText) getView().findViewById(R.id.descriptionEdit);
        mButtonEdit = (Button) getView().findViewById(R.id.buttonEdit);
        System.out.println(capteur.toString());
        mTitleEdit.setText(capteur.getName(), TextView.BufferType.EDITABLE);

        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveEditServer(s.getId(), mTitleEdit.getText().toString());
            }
        });

    }
}