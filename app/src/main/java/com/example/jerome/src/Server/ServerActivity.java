package com.example.jerome.src.Server;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jerome.src.R;
import com.example.jerome.src.ServerDetail.EditServerActivity;

import java.util.ArrayList;

import sql.Models.Server;
import sql.ServerDbHelper;
import sql.ServerRepository;


public class ServerActivity extends ListActivity implements ServerContract.View {
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServerDbHelper helper = new ServerDbHelper(this);
        ListView listView = (ListView) this.findViewById(android.R.id.list);
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        setContentView(R.layout.activity_list);
        ServerRepository repo = new ServerRepository(this);
        ServerPresenter mEditServerPresenter = new ServerPresenter(repo ,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), EditServerActivity.class);
        startActivity(i);
        setContentView(R.layout.activity_edit_item);
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showServers(ArrayList<Server> servers) {
        ListAdapter adapter = new ListAdapter(this , R.layout.custom_listview, servers);
        setListAdapter(adapter);
    }

    @Override
    public void openServerDetails() {
        Intent i = new Intent(getApplicationContext(),EditServerActivity.class);
        startActivity(i);
        setContentView(R.layout.activity_list);
    }

    public interface ServerItemListener {
        void onServerClick(Server clickedServer);
    }

}
