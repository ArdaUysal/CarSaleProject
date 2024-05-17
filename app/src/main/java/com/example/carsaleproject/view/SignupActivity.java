package com.example.carsaleproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carsaleproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText username,usermail,userpassword;
    Button btn_login,btn_signup;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        username = findViewById(R.id.txt_username);
        usermail = findViewById(R.id.txt_usermail);
        userpassword = findViewById(R.id.txt_userpassword);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        auth = FirebaseAuth.getInstance();

    }

    public void signupClicked(View view){
        String name = username.getText().toString();
        String mail = usermail.getText().toString();
        String password = userpassword.getText().toString();
        if(TextUtils.isEmpty(name)){
            username.setError("Kullanıcı Adı Kısmı Boş Olamaz!");
            username.requestFocus();
        } else if(TextUtils.isEmpty(mail)){
            usermail.setError("E-Posta Kısmı Boş Olamaz!");
            usermail.requestFocus();
        } else if(TextUtils.isEmpty(password)){
            userpassword.setError("Parola Kısmı Boş Olamaz!");
            userpassword.requestFocus();
        } else {
            auth.createUserWithEmailAndPassword(mail,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupActivity.this, "Kayıt Başarısız!", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public void loginClicked(View view){
        Intent intent = new Intent(SignupActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}