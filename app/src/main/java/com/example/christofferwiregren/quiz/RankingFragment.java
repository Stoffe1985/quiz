package com.example.christofferwiregren.quiz;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {

    private List<UserPoint> userList = new ArrayList<UserPoint>();
    private RecyclerView recyclerView;
    private RankningAdapter mAdapter;
    private UserPoint userPoint;
    private String gameId="";
    private String uid = "";
    private Button btnAvsluta;
    private ConstraintLayout constraintLayout;
    public RankingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        recyclerView = (RecyclerView) view.findViewById(R.id.rankingRecycler);
        btnAvsluta = (Button)view.findViewById(R.id.btnAvslutaspel);
        constraintLayout = (ConstraintLayout)view.findViewById(R.id.conrankning);
        mAdapter = new RankningAdapter(userList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        btnAvsluta.setEnabled(false);

        constraintLayout.setVisibility(View.VISIBLE);

        getPoints();


        btnAvsluta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                MainFragment mainFragment = new MainFragment();

                try {
                    ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.root,mainFragment).commit();

                }catch (Exception e){

                }



            }
        });

    }



    private void getPoints() {


        uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("GamePoint");


        databaseReference.
                addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot datasnapshot) {
                        userList.clear();




                        for (DataSnapshot ds: datasnapshot.getChildren()){




                            int point = Integer.parseInt(ds.child("score").getValue().toString());
                            String name = (String)ds.child("name").getValue().toString();
                            String id = (String)ds.child("id").getValue().toString();


                             userPoint = new UserPoint(id,name, point);

                             userList.add(userPoint);


                        }

                        Collections.sort(userList);


                        mAdapter.notifyDataSetChanged();
                        constraintLayout.setVisibility(View.INVISIBLE);
                        btnAvsluta.setEnabled(true);
                        btnAvsluta.setVisibility(View.VISIBLE);



                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }
}
