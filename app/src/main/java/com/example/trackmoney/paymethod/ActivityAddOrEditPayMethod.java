package com.example.trackmoney.paymethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trackmoney.Entidades.Database;
import com.example.trackmoney.R;

public class ActivityAddOrEditPayMethod extends AppCompatActivity {

    SQLiteDatabase db;
    EditText texto;
    Button btnNewOrUpdate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_pay_method);

        Context context = getBaseContext();
        Bundle b = getIntent().getExtras();

        id = b.getInt("id");

        Database conn = new Database(context);
        db = conn.getWritableDatabase();
        texto = findViewById(R.id.et_pm_ae);
        btnNewOrUpdate = findViewById(R.id.btn_pm_new);

        if( id > 0){
            btnNewOrUpdate.setText("Actualizar");
            String[] q = new String[]{String.valueOf(id)};
            Cursor c = db.rawQuery("SELECT name FROM pay_methods WHERE id = ?", q);

            if (c.moveToFirst()){
                texto.setText(c.getString(0));
            }
        }else{
            btnNewOrUpdate.setText("Agregar");
        }



        findViewById(R.id.btn_pm_back).setOnClickListener(back -> finish());

        int finalId = id;
        btnNewOrUpdate.setOnClickListener(v -> {
            if( finalId > 0){
                db.execSQL( "UPDATE pay_methods SET name = '"+String.valueOf(texto.getText())+"' WHERE id="+ finalId);
                Toast.makeText(context, "Actualizacion Correcta.", Toast.LENGTH_LONG).show();
            }else{
                db.execSQL("INSERT INTO pay_methods(name) VALUES ('"+ String.valueOf(texto.getText()) + "')");
                Toast.makeText(context, "Creacion Correcta.", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}