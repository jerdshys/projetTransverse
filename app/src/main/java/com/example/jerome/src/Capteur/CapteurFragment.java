package com.example.jerome.src.Capteur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.jerome.src.NewServer.NewServerActivity;
import com.example.jerome.src.R;
import com.example.jerome.src.ServerDetail.EditServerActivity;

import java.util.ArrayList;

import sql.Models.Server;
import sql.Repositories.ServerRepository;
import sql.ServerDbHelper;


public class CapteurFragment extends ListFragment implements CapteurContract.View {

    private CapteurContract.Presenter mPresenter;
    private ViewGroup container;
    private SwipeLayout mSwipeLayout;
    private ListAdapter mListAdapter;

    public CapteurFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("setting up listadapter");
        ServerDbHelper helper = new ServerDbHelper(this.getContext());
        ServerRepository repo = new ServerRepository(this.getContext());
        mPresenter = new CapteurPresenter(repo ,this);
        ArrayList<Server> emptyList = new ArrayList<Server>();
        mListAdapter = new ListAdapter(this.getContext() , R.layout.severs_custom_listview, new ArrayList<Server>(),mItemListener);
        return inflater.inflate(R.layout.servers_fragment, container, false);

    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button mButtonAdd = (Button) view.findViewById(R.id.buttonAdd);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewServer();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        mPresenter.loadServers();

    }


    @Override
    public void showServers(ArrayList<Server> servers) {
        ListAdapter adapter = new ListAdapter(this.getContext() , R.layout.severs_custom_listview, servers,mItemListener);
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

    public void replaceData(ArrayList<Server> servers) {
        setList(servers);
        mListAdapter.notifyDataSetChanged(

    );
    }


    private void setList(ArrayList<Server> items) {
        items = items;
    }


    /**
     * Listener for clicks on tasks in the ListView.
     */
    ServerItemListener mItemListener = new ServerItemListener() {
        @Override
        public void onServerClick(Server clickedServer) {
            System.out.println("clickedserver");

            System.out.println(clickedServer.getId().toString());
            Intent i = new Intent(getContext(),EditServerActivity.class);
            i.putExtra("id", clickedServer.getId().toString());
            startActivity(i);
        }
        @Override
        public void onServerDelete(Server clickedServer) {
           mPresenter.deleteServer(clickedServer.getId());
        }
    };



    public class ListAdapter extends ArrayAdapter<Server> {
        Context context;
        ArrayList<Server> items;
        ViewGroup vg;

        private ServerItemListener mItemListener;

        public ListAdapter(Context context, int layoutToBeInflated, final ArrayList<Server> items, ServerItemListener listener) {
            super(context, R.layout.severs_custom_listview, items);
            this.context = context;
            this.items = items;
            this.mItemListener = listener;
        }

        @Override
        public View getView(final int position, View v, final ViewGroup vg) {
            this.vg = vg;
            // Instantiates a layout XML file into its corresponding View objects
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View row = inflater.inflate(R.layout.severs_custom_listview, null);
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
                @Override   //when the SurfaceView totally cover the BottomView.
                public void onClose(SwipeLayout layout) { }
                @Override  //you are swiping.
                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {}
                @Override
                public void onStartOpen(SwipeLayout layout) { }
                @Override //when the BottomView totally show.
                public void onOpen(SwipeLayout layout) { }
                @Override
                public void onStartClose(SwipeLayout layout) { }
                @Override //when user's hand released.
                public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
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


