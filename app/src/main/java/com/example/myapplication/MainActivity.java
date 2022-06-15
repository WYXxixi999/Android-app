package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";

    private Button btnLogin;
    private EditText etAccount,etPassword;
    private TextView tvRegister,tvForget;

    private String userName = "wyx";
    private String pass = "123";

    //private Mysql mysql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("登录");

        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        tvRegister = findViewById(R.id.tv_register);
        tvForget = findViewById(R.id.tv_forgetPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断账号和密码是否符合要求
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                Log.d(TAG,"onClick:-----------------" + account);
                Log.d(TAG,"password:-----------------" + password);

                //从存储文件中获得用户名对应的密码（根据用户名检索）
                //如果content为空，说明用户不存在；否则，如果content内容和password不同，则密码错误登录失败
                String content;
                //使用SharedPreferences
                SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);

                //如果存储文件中没有对应的用户名，那么content值为""
                content = spRecord.getString(account,"");
                //Log.d(TAG,"content:" + content);

                //检查是否存在该用户名
                if(TextUtils.isEmpty(content)){
                    //如果为空
                    Toast.makeText(MainActivity.this,"用户名不存在！",Toast.LENGTH_LONG).show();
                }else{
                    //密码和存储文件中的相等，登录成功，跳转到欢迎页面
                    if(TextUtils.equals(content,password)) {
                        Toast.makeText(MainActivity.this, "恭喜你，登录成功！", Toast.LENGTH_LONG).show();
                        Intent intentWelcome = new Intent(MainActivity.this,WelcomeActivity.class);
                        //传递用户名参数
                        //intentWelcome.putExtra("account",account);
                        //页面跳转
                        startActivity(intentWelcome);

                    }
                    else //密码错误
                        Toast.makeText(MainActivity.this,"密码错误！",Toast.LENGTH_LONG).show();
                }
            }
        });

        //点击“还没有账号？”跳转到注册页面
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
        //点击“忘记密码”跳转到密码重置页面
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResettingPass = new Intent(MainActivity.this,ResettingPasswordActivity.class);
                startActivity(intentResettingPass);
            }
        });
    }


}