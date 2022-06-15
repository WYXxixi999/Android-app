package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import java.util.regex.*;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "tag";
    private Button btnRegister;
    private EditText etAccount,etPass,etPassConfirm;
    private CheckBox cbAgree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("注册");

        etAccount = findViewById(R.id.et_account);
        etPass = findViewById(R.id.et_password);
        etPassConfirm = findViewById(R.id.et_password_confirm);
        cbAgree = findViewById(R.id.cb_agree);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);//把RegisterActivity作为事件收听者

    }

    @Override
    public void onClick(View v) {
        String email = etAccount.getText().toString();
        String pass = etPass.getText().toString();
        String passConfirm = etPassConfirm.getText().toString();

        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        boolean isEmailMatch = Pattern.matches(emailMatcher,email);

        if(TextUtils.isEmpty(email)){
            Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if(!isEmailMatch){
            Toast.makeText(RegisterActivity.this, "邮箱格式不正确", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if(!TextUtils.equals(pass,passConfirm)){
            Toast.makeText(RegisterActivity.this, "两次输入密码不一致", Toast.LENGTH_LONG).show();
            return;
        }
        if(!cbAgree.isChecked()){
            Toast.makeText(RegisterActivity.this, "请同意协议", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();

        //将邮箱和密码存到SharedPreferences
        SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit = spRecord.edit();
        edit.putString(email,pass);
        edit.commit();

//        String content = spRecord.getString("contetnt","");//如果存储文件中没有对应的用户名，那么content值为""
        //Log.d(TAG,"content:"+content);

        //注册成功，跳转到登录页面
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
    }
}