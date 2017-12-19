package com.example.jerome.src.EditCapteur;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.jerome.src.R;

import sql.ServerDbHelper;


public class EditCapteurActivity extends FragmentActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_server_activity);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            String s = getIntent().getStringExtra("id");

            ServerDbHelper helper = new ServerDbHelper(this);

            EditCapteurFragment firstFragment = new EditCapteurFragment();
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }
    }



}
