package com.example.christofferwiregren.quiz;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
private Button btnExit, btnGame, btnRanking;
private FirebaseAuth auth;
private User user;
private DatabaseReference database;
private ProgressBar progressBar;
private ConstraintLayout constraintLayout;
    private FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();



    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        btnExit = (Button)view.findViewById(R.id.btnExit);
        btnGame = (Button)view.findViewById(R.id.btnGame);
        btnRanking = (Button)view.findViewById(R.id.btnRanking);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar2);
        constraintLayout = (ConstraintLayout)view.findViewById(R.id.con2);
        btnlock();


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signout();


            }
        });

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

try {


    Bundle bundle = new Bundle();
    bundle.putString("NAME", user.getNickname());
    bundle.putString("ID", user.getId());


    GameOnFragment gameOnFragment = new GameOnFragment();
    gameOnFragment.setArguments(bundle);

    getFragmentManager().beginTransaction().replace(R.id.root,gameOnFragment).addToBackStack(null).commit();


}catch (Exception e){

}



            }
        });

        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RankingFragment rankingFragment = new RankingFragment();

                getFragmentManager().beginTransaction().replace(R.id.root,rankingFragment).addToBackStack(null).commit();



            }
        });



        FirebaseAuth auth = FirebaseAuth.getInstance();


        if (auth.getCurrentUser() == null) {
            LoginFragment loginFragment = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.root,loginFragment).commit();

        }


    }

    private void signout() {

          AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Vill du logga ut?")
                .setTitle("Meddelande").setIcon(R.drawable.exit)
                .setCancelable(false)
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                auth.signOut();
                                LoginFragment loginFragment = new LoginFragment();
                                getFragmentManager().beginTransaction().replace(R.id.root,loginFragment).commit();

                            }
                        })

                        .setNegativeButton("Nej", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();


    }

    @Override
    public void onStart() {

        database = FirebaseDatabase.getInstance().getReference().child("User").child(currentFirebaseUser.getUid());


        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                user = new User();
                user = dataSnapshot.getValue(User.class);
                progressBar.setVisibility(View.INVISIBLE);
                constraintLayout.setVisibility(View.INVISIBLE);
                btnUnlock();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {




            }
        });



    }

    private void btnUnlock() {

        btnRanking.setEnabled(true);
        btnGame.setEnabled(true);
        btnExit.setEnabled(true);

    }

    private void btnlock() {

        btnRanking.setEnabled(false);
        btnGame.setEnabled(false);
        btnExit.setEnabled(false);

    }


}
