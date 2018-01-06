package com.bvd.android.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bvd.android.financemanager.activities.AllExpensesActivity;
import com.bvd.android.financemanager.activities.MailActivity;
import com.bvd.android.financemanager.activities.StatisticsActivity;
import com.bvd.android.financemanager.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.statisticsBtn)
    public Button statisticsBtn;
    private static String TAG = MainActivity.class.getName().toString();
    private FirebaseAuth auth;
    private User myUser;
    private boolean isPremium;
    @BindView(R.id.mainLogout)
    public Button logOutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();

        final Button showExpensesBtn = findViewById(R.id.showAllExpensesBtn);
        final Button mailBtn = findViewById(R.id.emailActivityBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                finish();
            }
        });

        showExpensesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllExpenses(view);
            }
        });
        mailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMailActivity();
            }
        });
        Intent intent = getIntent();
        User user = (User) intent.getExtras().getSerializable("userData");
        Log.v(TAG, "User Data=" + user);

        readUserDetailsFromDB();

        //this was set in the above method

        Log.v(TAG, "Premium Account=" + isPremium);


    }

    private void readUserDetailsFromDB() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //expensesReference = firebaseDatabase.getReference("https://financemanager-63f17.firebaseio.com/").child("expenses");
        final FirebaseUser currentUser = auth.getCurrentUser();

        DatabaseReference userDataRef = firebaseDatabase.getReference("users").child(currentUser.getUid()).child("premium");

        userDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                isPremium = dataSnapshot.getValue(Boolean.class);
                if(!isPremium){
                    statisticsBtn.setEnabled(false);

                }

                Log.d(TAG, "Value is: " + dataSnapshot.getValue().toString());
                Log.d(TAG, "User id: " + currentUser.getUid() + "|" + currentUser.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void showAllExpenses(View view) {
        Intent intent = new Intent(this, AllExpensesActivity.class);
        startActivity(intent);
    }

    public void showMailActivity() {
        Intent intent = new Intent(this, MailActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.statisticsBtn)
    public void showStatistics() {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }



}
