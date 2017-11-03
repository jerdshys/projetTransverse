package com.example.jerome.src.Server;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.jerome.src.NewServer.NewServerActivity;
import com.example.jerome.src.R;
import com.example.jerome.src.ServerDetail.EditServerActivity;

import java.util.ArrayList;

import sql.Models.Server;
import sql.ServerDbHelper;
import sql.ServerRepository;


public class ServerFragment extends ListFragment implements ServerContract.View {

    private ServerContract.Presenter mPresenter;
    private ViewGroup container;
    private SwipeLayout mSwipeLayout;

    public ServerFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("setting up listadapter");

        ServerDbHelper helper = new ServerDbHelper(this.getContext());
        ServerRepository repo = new ServerRepository(this.getContext());
        mPresenter = new ServerPresenter(repo ,this);


        return inflater.inflate(R.layout.fragment_servers, container, false);
    }


    @Override
    public void showServers(ArrayList<Server> servers) {
        ListAdapter adapter = new ListAdapter(this.getContext() , R.layout.custom_listview, servers,mItemListener);
        setListAdapter(adapter);
    }

    @Override
    public void openServerDetails() {
        Intent i = new Intent(getContext(),EditServerActivity.class);
        startActivity(i);
    }

    @Override
    public void openNewServer() {
        Intent i = new Intent(getContext(),NewServerActivity.class);
        startActivity(i);
    }

    @Override
    public void updateList() {
       // myAdapter.remove(myAdapter.getItem(info.position));
       // myAdapter.notifyDataSetChanged();
    }


    /**
     * Listener for clicks on tasks in the ListView.
     */
    ServerItemListener mItemListener = new ServerItemListener() {
        @Override
        public void onServerClick(Server clickedServer) {
            Intent i = new Intent(getContext(),EditServerActivity.class);
            startActivity(i);
        }

        @Override
        public void onServerDelete(Server clickedServer) {
           mPresenter.deleteServer(clickedServer.getId());
          //  Intent i = new Intent(getContext(),EditServerActivity.class);
         //   startActivity(i);
        }
    };

    public class ListAdapter extends ArrayAdapter<Server> {
        Context context;
        ArrayList<Server> items;
        ViewGroup vg;

        private ServerItemListener mItemListener;

        public ListAdapter(Context context, int layoutToBeInflated, final ArrayList<Server> items, ServerItemListener listener) {
            super(context, R.layout.custom_listview, items);
            this.context = context;
            this.items = items;
            this.mItemListener = listener;
        }

        @Override
        public View getView(final int position, View v, final ViewGroup vg) {
            this.vg = vg;
            // Instantiates a layout XML file into its corresponding View objects
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View row = inflater.inflate(R.layout.custom_listview, null);
            // set label texts
            TextView label = (TextView) row.findViewById(R.id.label);
            Server mServer =this.items.get(position);
            label.setText("#"+Long.toString(mServer.getId())+"   "+mServer.getTitle());
            TextView label2 = (TextView) row.findViewById(R.id.label2);
            label2.setText(mServer.getDescription());

            Button buttonDelete = (Button) row.findViewById(R.id.buttonDelete);


            final ArrayList<Server> list = this.items;
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onServerDelete(items.get(position));
                }
            });

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mItemListener.onServerClick(items.get(position));
                }
            });

            mSwipeLayout =  (SwipeLayout)row.findViewById(R.id.sample1);
            //set show mode.
            mSwipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            //add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
            mSwipeLayout.addDrag(SwipeLayout.DragEdge.Left, row.findViewById(R.id.bottom_wrapper));

            mSwipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
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


    public interface ServerItemListener {

        void onServerClick(Server clickedServ);
        void onServerDelete(Server clickedServ);


    }


}


