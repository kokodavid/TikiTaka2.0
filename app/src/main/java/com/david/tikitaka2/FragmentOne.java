package com.david.tikitaka2;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class FragmentOne extends Fragment {
    private ArrayList<Event> mEvents = new ArrayList<>();

    private EventListAdapter mAdapter;

    SpotsDialog spotsDialog;

    private FloatingActionButton fab_main, fab1_mail, fab2_share;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView textview_mail, textview_share;

    Boolean isOpen = false;


    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;


    /*private TextView mAppNameTextView;*/

    public static final String TAG = HomeActivity.class.getSimpleName();


    private ViewPager mViewPager;
    ArrayList<Event> mEvent = new ArrayList<>();


    public static FragmentOne newInstance() {
        FragmentOne fragment = new FragmentOne();
        return fragment;
    }


    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getEvents();

        // Instructs fragment to include menu options:
        setHasOptionsMenu(true);


    }

    private void getEvents() {
        final EventBriteService EventBriteService = new EventBriteService();
        EventBriteService.findEvents(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mEvents = EventBriteService.processResults(response);

                if (getActivity() == null)
                    return;
                getActivity().runOnUiThread(new Runnable() {


                    @Override
                    public void run() {

                        mAdapter = new EventListAdapter(getActivity(), mEvents);
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView = (RecyclerView) mRecyclerView.findViewById(R.id.recycler);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }

        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        ButterKnife.bind(this, view);

        return view;

    }


}