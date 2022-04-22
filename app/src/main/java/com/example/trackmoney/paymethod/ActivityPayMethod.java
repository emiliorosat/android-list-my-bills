package com.example.trackmoney.paymethod;

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


import java.util.ArrayList;

public class ActivityPayMethod extends AppCompatActivity {

    RecyclerView rv_list;
    ArrayList<Parametro> datos;
    AdaptadorPM adaptador;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_method);

        Database conn = new Database(this);
        db = conn.getReadableDatabase();

        rv_list = findViewById(R.id.rv_pm);
        rv_list.setLayoutManager(new LinearLayoutManager(this));


        findViewById(R.id.btn_pm_new).setOnClickListener(n -> {
            Intent intent = new Intent(ActivityPayMethod.this, ActivityAddOrEditPayMethod.class);
            intent.putExtra("id", 0);
            startActivity(intent);
        });
        findViewById(R.id.btn_pm_back).setOnClickListener(b -> finish());

        UpdateList();
    }

    private void UpdateList(){
        String query = "SELECT id, name FROM pay_methods";
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
            AdaptadorPM adapter = new AdaptadorPM(datos);
            rv_list.setAdapter(adapter);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        UpdateList();
    }
}