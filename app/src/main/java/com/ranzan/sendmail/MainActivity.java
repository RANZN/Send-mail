package com.ranzan.sendmail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText cc;
    private EditText context;
    private EditText subject;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        cc = findViewById(R.id.cc);
        context = findViewById(R.id.text);
        subject = findViewById(R.id.subject);
        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_CC, new String[]{cc.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_TEXT, context.getText().toString());

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activitiesList = packageManager.queryIntentActivities(emailIntent, 0);
                if (activitiesList.size() >= 1) {
                    startActivity(emailIntent);
                } else {
                    Toast.makeText(MainActivity.this, "No App found", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}