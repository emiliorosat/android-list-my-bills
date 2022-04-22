package com.example.trackmoney;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.trackmoney.Entidades.Database;
import com.example.trackmoney.Entidades.Parametro;
import com.example.trackmoney.databinding.FragmentFirstBinding;
import com.example.trackmoney.paymethod.AdaptadorPM;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);



        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Database conn = new Database(getContext());
        SQLiteDatabase db = conn.getReadableDatabase();

        Spinner sp = view.findViewById(R.id.sp_slect);

        ArrayAdapter<CharSequence> selctAdapter =ArrayAdapter.createFromResource(getContext(), R.array.graph_select, android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(selctAdapter);

        Pie pie = AnyChart.pie();



        //"SELECT SUM( b.pay) as pay, pm.name as pay_method FROM bills b INNER JOIN pay_methods pm ON b.id_pay_method = pm.id INNER JOIN types_bill tb ON b.id_type_bill = tb.id GROUP BY pm.name"


        //pie.title("Gastos del mes");
        pie.legend()
                .position("center-top")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        AnyChartView anyChartView = (AnyChartView) view.findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<DataEntry> data = new ArrayList<>();


                if(i == 1){
                    String query = "SELECT SUM( b.pay) as pay, pm.name as pay_method FROM bills b INNER JOIN pay_methods pm ON b.id_pay_method = pm.id INNER JOIN types_bill tb ON b.id_type_bill = tb.id GROUP BY pm.name";
                    Cursor c = db.rawQuery(query, null);
                    if (c.moveToFirst()){
                        do{
                            data.add(new ValueDataEntry(c.getString(1), c.getFloat(0)));

                        }while (c.moveToNext());
                        pie.data(data);
                    }
                }else{
                    String query = "SELECT SUM( b.pay) as pay, tb.name FROM bills b INNER JOIN pay_methods pm ON b.id_pay_method = pm.id INNER JOIN types_bill tb ON b.id_type_bill = tb.id GROUP BY tb.name";
                    Cursor c = db.rawQuery(query, null);
                    if (c.moveToFirst()){
                        do{
                            data.add(new ValueDataEntry(c.getString(1), c.getFloat(0)));

                        }while (c.moveToNext());
                        pie.data(data);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}