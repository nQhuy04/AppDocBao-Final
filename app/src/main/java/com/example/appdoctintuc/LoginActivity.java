package com.example.appdoctintuc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText edUserNameC, edPasswordC;
    ImageButton imbBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        btnLogin =findViewById(R.id.btnlogin);
        btnRegister = findViewById(R.id.btnRegister);
        edUserNameC =findViewById(R.id.edUserName);
        edPasswordC =findViewById(R.id.edPassword);
        imbBack = findViewById(R.id.imbBack);
        //
        btnLogin.setOnClickListener(NhanVaoLogin());
        btnRegister.setOnClickListener(NhanVaoRegister());
        imbBack.setOnClickListener(NhanVaoNutBack());
    }

    @NonNull
    private  View.OnClickListener NhanVaoNutBack() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                // Kết thúc (finish) Activity hiện tại nếu bạn muốn
                finish();
            }
        };
    }

    @NonNull
    private View.OnClickListener NhanVaoRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };
    }

    @NonNull
    private View.OnClickListener NhanVaoLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUserNameC.getText().toString().trim();
                String password = edPasswordC.getText().toString().trim();
                if(checkUsername(username) && checkPassword(password))
                {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        };
    }

    boolean checkUsername(String username){
        if(username.isEmpty())
        {
            edUserNameC.setError("Vui lòng nhập");
            return false;
        }
        return true;
    }

    boolean checkPassword(String password){
        if(password.isEmpty())
        {
            edPasswordC.setError("Vui lòng nhập");
            return false;
        }
        return true;
    }
}