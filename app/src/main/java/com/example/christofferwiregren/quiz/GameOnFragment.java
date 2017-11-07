package com.example.christofferwiregren.quiz;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameOnFragment extends Fragment {
    private Button btn1, btn2, btn3, btn4;
    private TextView txtRubrik, txtQuestion, points;
    private User adminUser;
    private List<Card> wordList = new ArrayList<>();
    private Card[] wordsarray;
    private ConstraintLayout constraintLayout;
    private ProgressBar progressBar;
    private  int currentNumber;
    private boolean wasitRight = false;
    private int score = 0;
    private int nextRound = 0 ;
    private TextView rounds;
    private int rightAnswer;


    public GameOnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_on, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        String id = bundle.getString("ID");
        String us = bundle.getString("NAME");
        this.adminUser = new User(id,us);

        btn1 = (Button)view.findViewById(R.id.statement1);
        btn2 = (Button)view.findViewById(R.id.statement2);
        btn3 = (Button)view.findViewById(R.id.statement3);
        btn4 = (Button)view.findViewById(R.id.statement4);
        txtQuestion = (TextView)view.findViewById(R.id.txtQuestion);
        txtRubrik = (TextView)view.findViewById(R.id.txtrubrik);
        points = (TextView)view.findViewById(R.id.points);
        rounds = (TextView)view.findViewById(R.id.rounds);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar3);
        constraintLayout = (ConstraintLayout)view.findViewById(R.id.constraint3);
        constraintLayout.setVisibility(View.VISIBLE);

        lockBtn();
        getCards();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasitRight = checkAnswer(1);

                if(wasitRight == true){

                    btn1.setTextColor(Color.GREEN);


                    score += 1;

                }else{

                    btn1.setTextColor(Color.RED);

                }
                setTimer();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasitRight =   checkAnswer(2);
                if(wasitRight == true){

                    btn2.setTextColor(Color.GREEN);


                    score += 1;


                }else{

                    btn2.setTextColor(Color.RED);

                }
                setTimer();



            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wasitRight = checkAnswer(3);
                if(wasitRight == true){

                    btn3.setTextColor(Color.GREEN);
                    score += 1;


                }else {


                    btn3.setTextColor(Color.RED);

                }

                setTimer();

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               wasitRight = checkAnswer(4);

                if(wasitRight == true){

                    btn1.setTextColor(Color.GREEN);


                    score += 1;


                }else {


                    btn4.setTextColor(Color.RED);
                }

                setTimer();


            }
        });
    }



    public void lockBtn(){

        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);


    }
    public void unlockBtn(){

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);


    }

    private void getCards() {

        wordList.clear();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("CARD");


        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    String text = (String) ds.child("text").getValue();
                    int correcAnswer = Integer.parseInt(ds.child("correctAnswer").getValue().toString());
                    String rubrik = (String) ds.child("rubrik").getValue();
                    String statement1 = (String) ds.child("statement1").getValue();
                    String statement2 = (String) ds.child("statement2").getValue();
                    String statement3 = (String) ds.child("statement3").getValue();
                    String statement4 = (String) ds.child("statement4").getValue();


                    Card words = new Card(rubrik,text,statement1,statement2,statement3,statement4,correcAnswer);
                    wordList.add(words);

                }

                wordsarray = wordList.toArray(new Card[wordList.size()]);

                constraintLayout.setVisibility(View.INVISIBLE);

                setInfo();
                unlockBtn();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void setInfo(){

        nextRound += 1;


        int a = randomnumber();

        Card cardCurrent = wordsarray[a];

rounds.setText(nextRound+"/10");
        btn1.setText(cardCurrent.getStatement1().toString());
        btn2.setText(cardCurrent.getStatement2().toString());
        btn3.setText(cardCurrent.getStatement3().toString());
        btn4.setText(cardCurrent.getStatement4().toString());
        txtRubrik.setText(cardCurrent.getRubrik().toString());
        txtQuestion.setText(cardCurrent.getText().toString());
        points.setText(""+score);

    }

    public boolean checkAnswer(int a){

        int rightAnswer = wordsarray[currentNumber].getCorrectAnswer();

        if(rightAnswer == a){

            return true;
        }else{

            showRightAnswerBtn(rightAnswer);



            return false;
        }

    }
    public int randomnumber(){

        Random random = new Random();

        currentNumber = random.nextInt(wordsarray.length)+0;

        return currentNumber;

    }

    public void setTimer(){

        lockBtn();

        CountDownTimer timer = new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                try {

                    if(nextRound>=2){

                        points.setText(""+score);


                        try {





                           sendScoreFirebase();

                           RankingFragment rankingFragment = new RankingFragment();

                           getFragmentManager().beginTransaction().replace(R.id.root, rankingFragment).commit();
                       }catch (Exception e){
                           Log.e("FEL", ""+e.toString());
                       }

                    }else {

                        setInfo();
                        btnState();
                        unlockBtn();

                    }


                } catch (Exception e) {
                }
            }


        }.start();


    }

    public void btnState(){
        btn1.setTextColor(Color.BLACK);
        btn2.setTextColor(Color.BLACK);
        btn3.setTextColor(Color.BLACK);
        btn4.setTextColor(Color.BLACK);

    }

    public void setScoreFirebase(){



    }

    public void sendScoreFirebase() {


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("GamePoint").push();


        Map<String, Object> hopperUpdates = new HashMap<String, Object>();
        hopperUpdates.put("name",""+ adminUser.getNickname().toString());
        hopperUpdates.put("id",""+ adminUser.getId().toString());
        hopperUpdates.put("score",score);



        databaseReference.updateChildren(hopperUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                }

            }
        });


    }


    public void showRightAnswerBtn(int a){

        switch (a){

            case 1:{

                btn1.setTextColor(Color.GREEN);


                break;
            }

            case 2:{
                btn2.setTextColor(Color.GREEN);


                break;
            }

            case 3:{
                btn3.setTextColor(Color.GREEN);


                break;
            }

            case 4:{

                btn4.setTextColor(Color.GREEN);



                break;
            }

        }

    }




}
