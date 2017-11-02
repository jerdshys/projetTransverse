package com.example.jerome.src.Server;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.jerome.src.ServerDetail.EditServerActivity;
import com.example.jerome.src.R;

import java.util.ArrayList;

import sql.Models.Server;


/**
 * Created by jerome on 18/10/2017.
 */


class ListAdapter extends ArrayAdapter<Server>  {
    Context context;
    ArrayList<Server> items;
    ViewGroup vg;

    public ListAdapter(Context context, int layoutToBeInflated, final ArrayList<Server> items) {
        super(context, R.layout.custom_listview, items);
        this.context = context;
        this.items = items;
    }


    @Override
    public View getView(final int position, View v, final ViewGroup vg) {

        this.vg = vg;
        // Instantiates a layout XML file into its corresponding View objects
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_listview, null);
        // set label texts
        TextView label = (TextView) row.findViewById(R.id.label);
        label.setText(this.items.get(position).getTitle());
        TextView label2 = (TextView) row.findViewById(R.id.label2);
        label2.setText(this.items.get(position).getDescription());

       // Button editButton = (Button) row.findViewById(R.id.button);
        final ArrayList<Server> list = this.items;
      //  editButton.setOnClickListener(new View.OnClickListener() {
       //  @Override
       //  public void onClick(View v) {
        //   Intent intent=new Intent(vg.getContext(), EditServerActivity.class);
         //    intent.putExtra("itemId", list.get(position).getTitle());
          //   vg.getContext().startActivity(intent);
        //   }
        //  });



        SwipeLayout swipeLayout =  (SwipeLayout)row.findViewById(R.id.sample1);
        //set show mode.
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        //add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, row.findViewById(R.id.bottom_wrapper));

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });


        return row;
    }
}


