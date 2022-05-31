package com.example.homeless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class registrarse extends AppCompatActivity {

    EditText correo,password;
    Button btnGuardar;
    ImageButton btnRegresar;
    LottieAnimationView animacion;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        correo = findViewById(R.id.txtCorreo);
        password = findViewById(R.id.txtPassword);
        btnGuardar = findViewById(R.id.btnRegistrarUsuario);
        btnRegresar = findViewById(R.id.btnInicioSesion);
        animacion = (LottieAnimationView) findViewById(R.id.animacionGuardado);

        auth = FirebaseAuth.getInstance();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correo.getText().toString();
                String pass = password.getText().toString();
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(registrarse.this,"Registrado con éxito",Toast.LENGTH_LONG).show();
                            animacion.setVisibility(View.VISIBLE);
                            animacion.playAnimation();
                        }else {
                            Toast.makeText(registrarse.this,"Error " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registrarse.this,inicio_sesion.class));
            }
        });

        animacion.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent cambiarVentana = new Intent(registrarse.this, inicio_sesion.class);
                startActivity(cambiarVentana);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }


}