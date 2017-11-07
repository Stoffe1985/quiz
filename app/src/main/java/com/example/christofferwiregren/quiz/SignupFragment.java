package com.example.christofferwiregren.quiz;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private EditText inputEmail, inputPassword, inputNickname;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private User user;


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) view.findViewById(R.id.sign_in_button);
        btnSignUp = (Button) view.findViewById(R.id.sign_up_button);
        inputEmail = (EditText) view.findViewById(R.id.email);
        inputPassword = (EditText) view.findViewById(R.id.password);

        btnResetPassword = (Button) view.findViewById(R.id.btn_reset_password);
        inputNickname = (EditText) view.findViewById(R.id.nickname);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgottPasswordFragment resetPasswordFragment = new ForgottPasswordFragment();
                getFragmentManager().beginTransaction().replace(R.id.root, resetPasswordFragment).addToBackStack(null).commit();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                getFragmentManager().beginTransaction().replace(R.id.root, loginFragment).addToBackStack(null).commit();
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    setFirebaseUser();


            }
        });
    }


    private void setFirebaseUser() {

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        final String nickname = inputNickname.getText().toString().trim();

        user = new User("", nickname);



        DatabaseReference mMessageReference = FirebaseDatabase.getInstance().getReference().child("User");


        mMessageReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (!checkIfUsernameExists(nickname.toString(), dataSnapshot)) {
                    creatDatabaseUser();
                } else {


                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });




    }


    public void creatDatabaseUser() {


        auth.createUserWithEmailAndPassword(inputEmail.getText().toString(), inputPassword.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            showUserId();
                            MainFragment mainActivityFragment = new MainFragment();
                            getFragmentManager().beginTransaction().replace(R.id.root, mainActivityFragment).commit();
                        }
                    }
                });

    }

    public void showUserId() {

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("User");

        user.setId(currentFirebaseUser.getUid());

        HashMap<String, String> datamap = new HashMap<String, String>();
        String userID = currentFirebaseUser.getUid();

        datamap.put("nickname", user.getNickname());
        datamap.put("id", user.getId());


        database.child(userID).setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                } else {


                }
            }
        });

    }

    public boolean checkIfUsernameExists(String username, DataSnapshot datasnapshot) {

        User user = new User();

        for (DataSnapshot ds : datasnapshot.getChildren()) {

            user.setNickname(ds.getValue(User.class).getNickname());


        }
        return false;
    }


}