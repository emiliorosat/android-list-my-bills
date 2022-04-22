package com.example.trackmoney.typebill;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackmoney.Entidades.Parametro;
import com.example.trackmoney.R;
import com.example.trackmoney.paymethod.ActivityAddOrEditPayMethod;
import com.example.trackmoney.paymethod.AdaptadorPM;

import java.util.ArrayList;

public class AdaptadorTB extends RecyclerView.Adapter<AdaptadorTB.ViewHolder>{
    ArrayList<Parametro> datos;

    public  AdaptadorTB(ArrayList<Parametro> _datos){
        datos = _datos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_text, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarDato(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView texto;
        int dato;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            texto = itemView.findViewById(R.id.tv_l_simple_text);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ActivityViewOrDeleteTypeBill.class);

                intent.putExtra("id", dato);
                context.startActivity(intent);
            });
        }

        public void asignarDato(Parametro _dato) {
            dato = _dato.getId();
            texto.setText(_dato.getNombre());

        }
    }
}
