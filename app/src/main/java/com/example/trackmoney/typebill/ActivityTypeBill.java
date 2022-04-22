package com.example.trackmoney.typebill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.trackmoney.Entidades.Database;
import com.example.trackmoney.Entidades.Parametro;
import com.example.trackmoney.R;
import com.example.trackmoney.paymethod.ActivityAddOrEditPayMethod;
import com.example.trackmoney.paymethod.ActivityPayMethod;
import com.example.trackmoney.paymethod.AdaptadorPM;

import java.util.ArrayList;

public class ActivityTypeBill extends AppCompatActivity {

    RecyclerView rv_list;
    ArrayList<Parametro> datos;
    AdaptadorTB adaptador;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_bill);

        Database conn = new Database(this);
        db = conn.getReadableDatabase();

        rv_list = findViewById(R.id.rv_tb);
        rv_list.setLayoutManager(new LinearLayoutManager(this));


        findViewById(R.id.btn_tb_new).setOnClickListener(n -> {
            Intent intent = new Intent(ActivityTypeBill.this, ActivityAddOrEditTypeBill.class);
            intent.putExtra("id", 0);
            startActivity(intent);
        });

        findViewById(R.id.btn_tb_back).setOnClickListener(b -> finish());
    }

    private void UpdateList(){
        String query = "SELECT id, name FROM types_bill";
        Cursor c = db.rawQuery(query, null);
        datos = new ArrayList<>();
        Parametro p;
        if (c.moveToFirst()){
            do{
                p = new Parametro();
                p.setNombre(c.getString(1));
                p.setId(c.getInt(0));
                datos.add(p);
            }while (c.moveToNext());
            AdaptadorTB adapter = new AdaptadorTB(datos);
            rv_list.setAdapter(adapter);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        UpdateList();
    }
}