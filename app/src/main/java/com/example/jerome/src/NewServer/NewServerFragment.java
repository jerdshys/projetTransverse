package com.example.jerome.src.NewServer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jerome.src.R;
import com.example.jerome.src.Server.NewServerContract;
import com.example.jerome.src.Server.ServerActivity;

import sql.Repositories.ServerRepository;

/**
 * Created by jerome on 13/11/2017.
 */
public class NewServerFragment extends Fragment implements NewServerContract.View {

    private NewServerContract.Presenter mPresenter;
    private Button mButtonAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ServerRepository repo = new ServerRepository(this.getContext());
        mPresenter = new NewServerPresenter(repo , this);
        return inflater.inflate(R.layout.new_server_fragment, container, false);
    }



    public void onViewCreated(View view, Bundle savedInstanceState) {
        mButtonAdd = (Button) getView().findViewById(R.id.buttonAdd);
        final EditText mNameAdd = (EditText) getView().findViewById(R.id.nameAdd);
        final EditText mDescriptionAdd = (EditText) getView().findViewById(R.id.descriptionAdd);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAdd(mNameAdd.getText().toString(),mDescriptionAdd.getText().toString());
            }
        });
    }
    @Override
    public void clickAdd(String name, String description) {
        mPresenter.saveServer(name,description);
        Intent i = new Intent(getContext(),ServerActivity.class);
        startActivity(i);

    }
}
