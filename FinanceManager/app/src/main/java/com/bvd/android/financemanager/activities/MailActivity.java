package com.bvd.android.financemanager.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.bvd.android.financemanager.R;

public class MailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        Button mailBtn = findViewById(R.id.sendEmailBtn);

        EditText emailContent = findViewById(R.id.emailContentText);
        CheckBox bugCheckBox = findViewById(R.id.errorMailCheckBox);

        final Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra(Intent.EXTRA_TEXT, emailContent.getText().toString());
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "test@gmail.com" });

        if (bugCheckBox.isEnabled()) {
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Bug Report");
        }

        sendIntent.setType("message/rfc822");
        mailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Intent.createChooser(sendIntent, "Send mail using..."));
            }
        });
    }
}
