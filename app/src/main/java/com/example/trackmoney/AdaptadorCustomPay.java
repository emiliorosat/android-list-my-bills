package com.example.trackmoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackmoney.Entidades.Gasto;
import com.example.trackmoney.Entidades.Parametro;

import java.util.ArrayList;


public class AdaptadorCustomPay extends RecyclerView.Adapter<AdaptadorCustomPay.ViewHolder> {
    ArrayList<Gasto> datos;
    public AdaptadorCustomPay(ArrayList<Gasto> _dato){
        datos = _dato;
    }

    @NonNull
    @Override
    public AdaptadorCustomPay.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_bill_item, null, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCustomPay.ViewHolder holder, int position) {
        holder.asignarDato(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, method, type, pay;
        int dato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.pbi_date);
            method = itemView.findViewById(R.id.pbi_method);
            type = itemView.findViewById(R.id.pbi_type);
            pay = itemView.findViewById(R.id.pbi_pay);
        }

        public void asignarDato(Gasto _dato) {
            dato = _dato.getId();

            date.setText(_dato.getDate());
            method.setText(_dato.getPay_method());
            type.setText(_dato.getType_bill());
            pay.setText(_dato.getPay().toString());

        }
    }
}
