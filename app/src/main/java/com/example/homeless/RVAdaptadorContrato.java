package com.example.homeless;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeless.modelo.Contrato;
import com.example.homeless.modelo.DAOContrato;
import com.example.homeless.modelo.DAOEmpleado;
import com.example.homeless.modelo.Empleado;


import java.util.ArrayList;

public class RVAdaptadorContrato extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    ArrayList<Contrato> list = new ArrayList<>();

    public RVAdaptadorContrato(Context ctx){
        this.context = ctx;
    }
    public void setItems(ArrayList<Contrato> con){
        list.addAll(con);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elemento_contrato,parent,false);
        return new ContratoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContratoVH vh = (ContratoVH) holder;
        Contrato con = list.get(position);
        vh.nombreContratador.setText(con.getNombreDelContratador());
        vh.nombreContratado.setText(con.getNombreContratado());
        vh.domicilioContratador.setText(con.getDomicilio());
        vh.oficioContratado.setText(con.getOficio());
        vh.opciones.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context,vh.opciones);
            popupMenu.inflate(R.menu.opciones_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.menuEditar:
                        Intent intent = new Intent(context,RegistrarContrato.class);
                        intent.putExtra("Editar", con);
                        context.startActivity(intent);
                        break;

                    case R.id.menuEliminar:
                        DAOContrato daoContrato = new DAOContrato();
                        daoContrato.eliminarContrato(con.getKey()).addOnSuccessListener(suc-> {
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
