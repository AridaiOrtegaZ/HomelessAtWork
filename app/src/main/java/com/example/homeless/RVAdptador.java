package com.example.homeless;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeless.modelo.DAOEmpleado;
import com.example.homeless.modelo.Empleado;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;

public class RVAdptador extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Empleado> list = new ArrayList<>();

    public RVAdptador(Context ctx){
        this.context = ctx;
    }

    public void setItems(ArrayList<Empleado> emp){
        list.addAll(emp);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elemento_lista,parent,false);
        return new EmpleadoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmpleadoVH vh = (EmpleadoVH) holder;
        Empleado emp = list.get(position);
        vh.nombreEmpleado.setText(emp.getNombre());
        vh.edadEmpleado.setText(emp.getEdad());
        vh.ubicacionEmpleado.setText(emp.getUbicacion());
        vh.urlEmpleado.setText(emp.getUrl());
        String url = emp.getUrl();
        if (!url.isEmpty()){
            Picasso.get().load(url).fit().into(vh.imagen);
        }


        vh.textOpciones.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context,vh.textOpciones);
            popupMenu.inflate(R.menu.opciones_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.menuEditar:
                        Intent intent = new Intent(context,registarEmpleado.class);
                        intent.putExtra("Editar", emp);
                        context.startActivity(intent);
                        break;

                    case R.id.menuEliminar:
                        DAOEmpleado daoEmpleado = new DAOEmpleado();
                        daoEmpleado.eliminarEmpleado(emp.getKey()).addOnSuccessListener(suc-> {
                            notifyItemRemoved(position);
                        });
                        break;

                }
                return false;
            });
            popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
