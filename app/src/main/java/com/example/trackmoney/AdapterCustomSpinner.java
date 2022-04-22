package com.example.trackmoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trackmoney.Entidades.Parametro;

import java.util.ArrayList;

public class AdapterCustomSpinner extends BaseAdapter {

    ArrayList<Parametro> datos;
    Context context;

    public AdapterCustomSpinner(Context _context, ArrayList<Parametro> _datos){
        datos = _datos;
        context = _context;
    }
    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return datos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return datos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View elementView = LayoutInflater.from(context).inflate(R.layout.simple_text, viewGroup,false);

        TextView texto = elementView.findViewById(R.id.tv_l_simple_text);
        texto.setText(datos.get(i).getNombre());
        return elementView;
    }
}
