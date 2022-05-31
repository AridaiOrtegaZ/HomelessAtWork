package com.example.homeless;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmpleadoVH extends RecyclerView.ViewHolder {
    public TextView nombreEmpleado,edadEmpleado,ubicacionEmpleado,urlEmpleado,textOpciones;
    ImageView imagen;


    public EmpleadoVH(@NonNull View itemView) {
        super(itemView);

        nombreEmpleado = itemView.findViewById(R.id.nombreEmpleado);
        edadEmpleado = itemView.findViewById(R.id.edadEmpleado);
        ubicacionEmpleado = itemView.findViewById(R.id.ubicacionEmpleado);
        imagen = itemView.findViewById(R.id.fotoEmpleado);
        urlEmpleado = itemView.findViewById(R.id.urlEmpleado);
        textOpciones = itemView.findViewById(R.id.textOpciones);
    }
}
