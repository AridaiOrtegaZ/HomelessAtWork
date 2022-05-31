package com.example.homeless;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeless.modelo.DAOEmpleado;
import com.example.homeless.modelo.Empleado;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVAdaptadorUsuario extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Empleado> list = new ArrayList<>();

    public RVAdaptadorUsuario(Context ctx){
        this.context = ctx;
    }
    public void setItems(ArrayList<Empleado> emp){
        list.addAll(emp);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elemento_lista2,parent,false);
        return new EmpleadoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmpleadoVH vh = (EmpleadoVH) holder;
        Empleado emp = list.get(position);
        vh.nombreEmpleado.setText(emp.getNombre());
        vh.edadEmpleado.setText(emp.getEdad());
        vh.ubicacionEmpleado.setText(emp.getUbicacion());
        vh.textOpciones.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context,vh.textOpciones);
            popupMenu.inflate(R.menu.opciones_usuario);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.menuPerfil:
                            Intent intent = new Intent(context,perfilEmpleado.class);
                            intent.putExtra("Mostrar", emp);
                            context.startActivity(intent);
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
