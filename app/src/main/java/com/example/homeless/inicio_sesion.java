package com.example.homeless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

import java.io.Console;

public class inicio_sesion extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText correo,password;
    TextView registrarse;
    Button botonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        correo = findViewById(R.id.txtCorreo);
        password = findViewById(R.id.txtPassword);
        botonIngresar = findViewById(R.id.btnIngresar);
        registrarse = findViewById(R.id.textRegistrarse);

        auth = FirebaseAuth.getInstance();

        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(inicio_sesion.this, registrarse.class));
            }
        });

    }

    private void login() {
        String email = correo.getText().toString();
        String pass = password.getText().toString();

        if (email.equals("") || pass.equals("")){
            Toast.makeText(inicio_sesion.this, "Debe de ingresar usuario y/o contraseña",Toast.LENGTH_SHORT).show();
        }else if (email.equals("admin@mail.com") & pass.equals("admin123")){
                startActivity(new Intent(inicio_sesion.this, menuPrincipal_admin.class));
        }else {
            auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(new Intent(inicio_sesion.this, menuUsuario.class));
                    }else{
                        Toast.makeText(inicio_sesion.this,"Error al iniciar sesión",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

}