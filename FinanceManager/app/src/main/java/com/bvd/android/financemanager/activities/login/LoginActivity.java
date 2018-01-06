package com.bvd.android.financemanager.activities.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bvd.android.financemanager.MainActivity;
import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getName();
    @BindView(R.id.loginEmailText)
    TextView emailTxt;
    @BindView(R.id.loginPasswordText)
    TextView passwordTxt;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    private FirebaseAuth auth;
    private DatabaseReference rootReference;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();

        //todo check the role
        if (currentUser != null) {
            Log.v(TAG, "Signed in as=" + currentUser.getEmail());
            redirectToMainView(currentUser);
        }
    }

    private void redirectToMainView(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);

            Intent intentInitial = getIntent();
            if (intentInitial != null) {
                User user = (User) intentInitial.getSerializableExtra("userData");
                intent.putExtra("userData", user);
            }

            startActivity(intent);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lgin);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();


    }

    @OnClick(R.id.loginBtn)
    protected void signIn() {
        String email = String.valueOf(emailTxt.getText());
        String password = String.valueOf(passwordTxt.getText());
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            redirectToMainView(user);

                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            redirectToMainView(null);
                        }
                    }
                });


    }

    @OnClick(R.id.loginRegisterBtn)
    protected void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}
