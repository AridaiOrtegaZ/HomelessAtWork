package com.example.homeless;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContratoVH extends RecyclerView.ViewHolder {
    public TextView nombreContratador,nombreContratado,domicilioContratador,oficioContratado,opciones;

    public ContratoVH(@NonNull View itemView) {
        super(itemView);
        nombreContratador = itemView.findViewById(R.id.textNombreContatador);
        nombreContratado = itemView.findViewById(R.id.textNombreContratado);
        domicilioContratador = itemView.findViewById(R.id.textDomicilioContratador);
        oficioContratado = itemView.findViewById(R.id.textOficioContratado);
        opciones = itemView.findViewById(R.id.textOpcionesContrato);

    }
}
