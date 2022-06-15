package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView showHello;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().setTitle("欢迎");

        //显示“欢迎你！” + 用户名
//        Intent intent = getIntent();
//        String account = intent.getStringExtra("account");
        //Log.d("tag","Welcome account:" + account);

        showHello = this.findViewById(R.id.welcomeWord);
        showHello.setText("欢迎你！");

    }
}