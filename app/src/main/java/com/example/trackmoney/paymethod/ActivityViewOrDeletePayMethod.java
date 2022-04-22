package com.example.trackmoney.paymethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trackmoney.Entidades.Database;
import com.example.trackmoney.R;

public class ActivityViewOrDeletePayMethod extends AppCompatActivity {

    SQLiteDatabase db;
    int id;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_or_delete_pay_method);

        Context context = getBaseContext();
        Bundle b = getIntent().getExtras();

        texto = findViewById(R.id.tv_pm_ae);

        Database conn = new Database(context);
        db = conn.getWritableDatabase();

        id = b.getInt("id");

        String[] q = new String[]{String.valueOf(id)};
        Cursor c = db.rawQuery("SELECT name FROM pay_methods WHERE id = ?", q);

        if (c.moveToFirst()){
            texto.setText(c.getString(0));
        }

        findViewById(R.id.btn_pm_edit).setOnClickListener(ed -> {
            Intent intent = new Intent(ActivityViewOrDeletePayMethod.this, ActivityAddOrEditPayMethod.class);
            intent.putExtra("id", id);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_pm_delete).setOnClickListener(e -> {
            db.execSQL("DELETE FROM pay_methods WHERE id="+ id);
            finish();
        });

        findViewById(R.id.btn_pm_back).setOnClickListener(back -> finish());
    }
}