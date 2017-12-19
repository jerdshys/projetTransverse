package com.example.jerome.src.EditCapteur;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jerome.src.R;

import sql.Models.Server;
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

        ServerRepository repo = new ServerRepository(this.getContext());
        mPresenter = new EditCapteurPresenter(repo , this);
        mTitleEdit = (EditText) getView().findViewById(R.id.nameEdit);
        mDescriptionEdit = (EditText) getView().findViewById(R.id.descriptionEdit);

    }

    public String getServerId() {
        return getActivity().getIntent().getStringExtra("id");
    }

    @Override
    public void showServerDetail(Server server) {
        final Server s = server;

        mTitleEdit = (EditText) getView().findViewById(R.id.nameEdit);
        mDescriptionEdit = (EditText) getView().findViewById(R.id.descriptionEdit);
        mButtonEdit = (Button) getView().findViewById(R.id.buttonEdit);

        System.out.println(server.toString());
        mTitleEdit.setText(server.getTitle(), TextView.BufferType.EDITABLE);
        mDescriptionEdit.setText(server.getDescription(), TextView.BufferType.EDITABLE);

        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveEditServer(s.getId(), mTitleEdit.getText().toString(),mDescriptionEdit.getText().toString());
            }
        });




    }
}