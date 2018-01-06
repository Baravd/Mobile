package com.bvd.android.financemanager.activities.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bvd.android.financemanager.MainActivity;
import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.dao.AppDatabase;
import com.bvd.android.financemanager.model.Expense;
import com.bvd.android.financemanager.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getName();
    @BindView(R.id.password1Text)
    TextView password1Txt;
    @BindView(R.id.password2Text)
    TextView password2Txt;

    @BindView(R.id.registerEmailText)
    TextView emailTxt;
    @BindView(R.id.registerPremiumCheckBox)
    CheckBox premiumCheckBox;
    private FirebaseAuth auth;
    private DatabaseReference expensesReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();


    }

    @OnClick(R.id.registerCreateAccBtn)
    void createAccount() {
        final String email = emailTxt.getText().toString();
        String password1 = password1Txt.getText().toString();
        String password2 = password2Txt.getText().toString();
        if (password1.length() < 6) {
            Toast.makeText(this, "Password length >=6",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password2.equals(password1)) {
            Toast.makeText(this, "REtype yur password",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
            auth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                expensesReference = firebaseDatabase.getReference("users");
                                FirebaseUser currentUser = auth.getCurrentUser();
                                String uid = currentUser.getUid();
                                String email1 = currentUser.getEmail();
                                boolean enabled = premiumCheckBox.isChecked();
                                User myUser = new User( enabled, uid, email1);
                                expensesReference.child(uid).setValue(myUser);

                                redirectToMainView(user, myUser);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Failed user exists or check internet connection",
                                        Toast.LENGTH_SHORT).show();
                                redirectToMainView(null, null);
                            }

                        }
                    });
        }

    }

    private void redirectToMainView(FirebaseUser currentUser, User myUser) {
        Log.v(TAG, "currentUser=" + currentUser + "User Data" + myUser);
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userData", myUser);
            startActivity(intent);

        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("userData", myUser);
            startActivity(intent);

        }


    }
}
