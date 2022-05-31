package com.example.homeless;

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
import com.example.homeless.modelo.DAOEmpleado;
import com.example.homeless.modelo.Empleado;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class registarEmpleado extends AppCompatActivity {
    ImageButton botonRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_empleado);

        final EditText edit_nombre = (EditText)findViewById(R.id.edit_nombre);
        final EditText edit_edad = (EditText)findViewById(R.id.edit_edad);
        final EditText edit_ubicacion = (EditText)findViewById(R.id.edit_ubicacion);
        final EditText edit_url = (EditText)findViewById(R.id.edit_url);
        botonRegresar = findViewById(R.id.btnRegresarMenu);

       Button btnGuardar = (Button)findViewById(R.id.btnRegistrarEmpleado);
       LottieAnimationView animacion = (LottieAnimationView) findViewById(R.id.animacionGuardado);

        DAOEmpleado daoEmpleado = new DAOEmpleado();
        Empleado emp_edit = (Empleado)getIntent().getSerializableExtra("Editar");
        if (emp_edit != null){
            btnGuardar.setText("Actualizar");
            edit_nombre.setText(emp_edit.getNombre());
            edit_edad.setText(emp_edit.getEdad());
            edit_ubicacion.setText(emp_edit.getUbicacion());
            edit_url.setText(emp_edit.getUrl());
        }


        animacion.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent cambiarVentana = new Intent(registarEmpleado.this, listaEmpleado.class);
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

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emp_edit == null) {

                Empleado emp = new Empleado(edit_nombre.getText().toString(), edit_edad.getText().toString(), edit_ubicacion.getText().toString(), edit_url.getText().toString());
                daoEmpleado.agregarEmpleado(emp).addOnSuccessListener(suc -> {
                    animacion.setVisibility(View.VISIBLE);
                    animacion.playAnimation();
                });
                }else{
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("nombre",edit_nombre.getText().toString());
                    hashMap.put("edad",edit_edad.getText().toString());
                    hashMap.put("ubicacion",edit_ubicacion.getText().toString());
                    hashMap.put("url",edit_url.getText().toString());
                    daoEmpleado.actualizarEmpleado(emp_edit.getKey(),hashMap).addOnSuccessListener(suc-> {
                        animacion.setVisibility(View.VISIBLE);
                        animacion.playAnimation();
                        //finish();
                    });
                }
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registarEmpleado.this, listaEmpleado.class));
            }
        });
    }

}