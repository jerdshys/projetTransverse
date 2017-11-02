package com.example.jerome.src.Server;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jerome.src.R;

import java.util.ArrayList;

import sql.Models.Server;


public class ServerFragment extends ListFragment implements ServerContract.View {

    private ServerContract.Presenter mPresenter;
    private ViewGroup container;

    public ServerFragment() {
        System.out.println("setting up listadapter");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void showServers(ArrayList<Server> items) {
        //  setContentView(R.layout.activity_list);
        ListAdapter adapter = new ListAdapter(this.container.getContext() , R.layout.custom_listview, items);
        setListAdapter(adapter);
    }

    //   @Override
    //  public void showTasks(ArrayList<Server> servers) {

    //}
}