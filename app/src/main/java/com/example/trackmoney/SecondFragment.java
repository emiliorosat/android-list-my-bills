package com.example.trackmoney;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.example.trackmoney.Entidades.Database;
import com.example.trackmoney.Entidades.Gasto;
import com.example.trackmoney.databinding.FragmentSecondBinding;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rc = view.findViewById(R.id.recicler);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        Database conn = new Database(getContext());
        SQLiteDatabase db = conn.getReadableDatabase();

        ArrayList<Gasto> datos = new ArrayList<>();
        Gasto g;

        String query = "SELECT b.pay, b.date, pm.name as pay_method, tb.name as type_bill FROM bills b INNER JOIN pay_methods pm ON b.id_pay_method = pm.id INNER JOIN types_bill tb ON b.id_type_bill = tb.id";
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            do{
                g =new Gasto();
                g.setPay(c.getFloat(0));
                g.setDate(c.getString(1));
                g.setPay_method(c.getString(2));
                g.setType_bill(c.getString(3));
                datos.add(g);
                //data.add(new ValueDataEntry(c.getString(1), c.getFloat(0)));

            }while (c.moveToNext());
            AdaptadorCustomPay adapt = new AdaptadorCustomPay(datos);
            rc.setAdapter(adapt);

        }





        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}