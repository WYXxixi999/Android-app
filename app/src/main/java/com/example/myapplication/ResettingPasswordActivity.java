package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class ResettingPasswordActivity extends AppCompatActivity {

    private Button btnResettingPass;
    private EditText etAccount,etPass,etPassConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetting_password);

        getSupportActionBar().setTitle("密码重置");

        etAccount = findViewById(R.id.et_account);
        etPass = findViewById(R.id.et_password);
        etPassConfirm = findViewById(R.id.et_password_confirm);
        btnResettingPass = findViewById(R.id.btn_reset_password);

        btnResettingPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etAccount.getText().toString();
                String pass = etPass.getText().toString();
                String passConfirm = etPassConfirm.getText().toString();

                String content;
                //使用SharedPreferences
                SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);

                //如果存储文件中没有对应的用户名，那么content值为""
                content = spRecord.getString(email,"");

                //用户自己输入的账号为空
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(ResettingPasswordActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                //用户输入的账号在数据库中不存在
                if(TextUtils.isEmpty(content)){
                    Toast.makeText(ResettingPasswordActivity.this,"用户名不存在！",Toast.LENGTH_LONG).show();
                    return;
                }else{//开始重设密码
                    //输入密码为空
                    if(TextUtils.isEmpty(pass)){
                        Toast.makeText(ResettingPasswordActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                        return;
                    }
                    //两次密码不一致
                    if(!TextUtils.equals(pass,passConfirm)){
                        Toast.makeText(ResettingPasswordActivity.this, "两次输入密码不一致", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                //将邮箱和新密码存到SharedPreferences,覆盖掉原来的值
                spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);
                SharedPreferences.Editor edit = spRecord.edit();
                //edit.putString(email,pass);
                edit.remove(email);
                //edit.commit();
                edit.putString(email,pass);
                edit.commit();
                Toast.makeText(ResettingPasswordActivity.this, "密码重置成功！", Toast.LENGTH_LONG).show();

                //重置成功，跳转到登录页面
                Intent intent = new Intent(ResettingPasswordActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}