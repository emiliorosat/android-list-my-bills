package com.example.trackmoney.typebill;

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

public class ActivityAddOrEditTypeBill extends AppCompatActivity {

    SQLiteDatabase db;
    EditText texto;
    Button btnNewOrUpdate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_type_bill);

        Context context = getBaseContext();
        Bundle b = getIntent().getExtras();

        id = b.getInt("id");

        Database conn = new Database(context);
        db = conn.getWritableDatabase();
        texto = findViewById(R.id.et_tb_ae);
        btnNewOrUpdate = findViewById(R.id.btn_tb_new);

        if( id > 0){
            btnNewOrUpdate.setText("Actualizar");
            String[] q = new String[]{String.valueOf(id)};
            Cursor c = db.rawQuery("SELECT name FROM types_bill WHERE id = ?", q);

            if (c.moveToFirst()){
                texto.setText(c.getString(0));
            }
        }else{
            btnNewOrUpdate.setText("Agregar");
        }

        findViewById(R.id.btn_tb_back).setOnClickListener(back -> finish());

        int finalId = id;
        btnNewOrUpdate.setOnClickListener(v -> {
            if( finalId > 0){
                db.execSQL( "UPDATE types_bill SET name = '"+String.valueOf(texto.getText())+"' WHERE id="+ finalId);
                Toast.makeText(context, "Actualizacion Correcta.", Toast.LENGTH_LONG).show();
            }else{
                db.execSQL("INSERT INTO types_bill(name) VALUES ('"+ String.valueOf(texto.getText()) + "')");
                Toast.makeText(context, "Creacion Correcta.", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}