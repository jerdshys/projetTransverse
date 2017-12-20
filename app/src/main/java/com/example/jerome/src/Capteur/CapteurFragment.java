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
import com.example.jerome.src.EditCapteur.EditCapteurActivity;
import com.example.jerome.src.NewCapteur.NewCapteurActivity;
import com.example.jerome.src.NewCapteur.NewCapteurActivity;
import com.example.jerome.src.R;

import java.util.ArrayList;

import sql.Models.Capteur;
import sql.Models.Capteur;
import sql.Repositories.HttpCapteurRepository;



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
        HttpCapteurRepository repo = new HttpCapteurRepository(this.getContext());
        mPresenter = new CapteurPresenter(repo ,this);
        ArrayList<Capteur> emptyList = new ArrayList<Capteur>();
        mListAdapter = new ListAdapter(this.getContext() , R.layout.capteurs_custom_listview, new ArrayList<Capteur>(),mItemListener);
        return inflater.inflate(R.layout.capteurs_fragment, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button mButtonAdd = (Button) view.findViewById(R.id.buttonAdd);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewCapteur();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        mPresenter.loadCapteurs();

    }


    @Override
    public void showCapteurs(ArrayList<Capteur> capteurs) {
        ListAdapter adapter = new ListAdapter(this.getContext() , R.layout.capteurs_custom_listview, capteurs,mItemListener);
        setListAdapter(adapter);
    }

    @Override
    public void openCapteurDetails() {
        Intent i = new Intent(getContext(),EditCapteurActivity.class);
        startActivity(i);
    }

    @Override
    public void openNewCapteur() {
        Intent i = new Intent(getContext(),NewCapteurActivity.class);
        startActivity(i);
    }

    public void replaceData(ArrayList<Capteur> capteurs) {
        setList(capteurs);
        mListAdapter.notifyDataSetChanged();
    }


    private void setList(ArrayList<Capteur> items) {
        items = items;
    }


    /**
     * Listener for clicks on tasks in the ListView.
     */
    CapteurItemListener mItemListener = new CapteurItemListener() {
        @Override
        public void onCapteurClick(Capteur clickedCapteur) {
            System.out.println("clickedCapteur");
            System.out.println(clickedCapteur.getId().toString());
            Intent i = new Intent(getContext(),EditCapteurActivity.class);
            i.putExtra("id", clickedCapteur.getId().toString());
            startActivity(i);
        }

        @Override
        public void onCapteurDelete(Capteur clickedCapteur) {
           mPresenter.deleteCapteur(clickedCapteur.getId());
        }
    };



    public class ListAdapter extends ArrayAdapter<Capteur> {
        Context context;
        ArrayList<Capteur> items;
        ViewGroup vg;

        private CapteurItemListener mItemListener;

        public ListAdapter(Context context, int layoutToBeInflated, final ArrayList<Capteur> items, CapteurItemListener listener) {
            super(context, R.layout.capteurs_custom_listview, items);
            this.context = context;
            this.items = items;
            this.mItemListener = listener;
        }

        @Override
        public View getView(final int position, View v, final ViewGroup vg) {
            this.vg = vg;
            // Instantiates a layout XML file into its corresponding View objects
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View row = inflater.inflate(R.layout.capteurs_custom_listview, null);
            // set label texts
            TextView label = (TextView) row.findViewById(R.id.label);
            Capteur mCapteur = this.items.get(position);
            label.setText("#"+mCapteur.getId()+"   "+mCapteur.getName());
           // TextView label2 = (TextView) row.findViewById(R.id.label2);
           // label2.setText(mCapteur.getDescription());

            Button buttonDelete = (Button) row.findViewById(R.id.buttonDelete);


            final ArrayList<Capteur> list = this.items;
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onCapteurDelete(items.get(position));
                }
            });

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mItemListener.onCapteurClick(items.get(position));
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

    public interface CapteurItemListener {
        void onCapteurClick(Capteur clicked);
        void onCapteurDelete(Capteur clicked);
    }


}


