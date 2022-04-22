package com.example.trackmoney.typebill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.trackmoney.Entidades.Database;
import com.example.trackmoney.R;
import com.example.trackmoney.paymethod.ActivityAddOrEditPayMethod;
import com.example.trackmoney.paymethod.ActivityViewOrDeletePayMethod;

public class ActivityViewOrDeleteTypeBill extends AppCompatActivity {

    SQLiteDatabase db;
    int id;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_or_delete_type_bill);

        Context context = getBaseContext();
        Bundle b = getIntent().getExtras();

        texto = findViewById(R.id.tv_tb_ae);

        Database conn = new Database(context);
        db = conn.getWritableDatabase();

        id = b.getInt("id");

        String[] q = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery("SELECT name FROM types_bill WHERE id = ?", q);

        if (c.moveToFirst()){
            texto.setText(c.getString(0));
        }

        findViewById(R.id.btn_tb_edit).setOnClickListener(ed -> {
            Intent intent = new Intent(ActivityViewOrDeleteTypeBill.this, ActivityAddOrEditTypeBill.class);
            intent.putExtra("id", id);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_tb_delete).setOnClickListener(e -> {
            db.execSQL("DELETE FROM types_bill WHERE id="+ id);
            finish();
        });

        findViewById(R.id.btn_tb_back).setOnClickListener(back -> finish());
    }
}