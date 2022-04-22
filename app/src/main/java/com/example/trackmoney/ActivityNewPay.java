package com.example.trackmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trackmoney.Entidades.Database;
import com.example.trackmoney.Entidades.Parametro;
import com.example.trackmoney.paymethod.AdaptadorPM;
import com.example.trackmoney.typebill.AdaptadorTB;

import java.util.ArrayList;

public class ActivityNewPay extends AppCompatActivity {
    Spinner sp_pay_method, sp_type_bill;
    SQLiteDatabase db;
    ArrayList<Parametro> pm_list, tb_list;
    Parametro payMethodSelected = new Parametro(), typeBillSelected = new Parametro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pay);
        Database conn = new Database(this);
        db = conn.getWritableDatabase();

        getPayMethodData();
        getTypeBillData();

        sp_pay_method = findViewById(R.id.sp_pay_pm);
        sp_type_bill = findViewById(R.id.sp_pay_tb);

        AdapterCustomSpinner pmSpAdapter = new AdapterCustomSpinner(this, pm_list);
        sp_pay_method.setAdapter(pmSpAdapter);


        AdapterCustomSpinner tbSpAdapter = new AdapterCustomSpinner(this, tb_list);
        sp_type_bill.setAdapter(tbSpAdapter);

        findViewById(R.id.btn_add_pay).setOnClickListener(v->AddPay());

        findViewById(R.id.btn_pay_back).setOnClickListener(back -> finish());

        sp_pay_method.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                payMethodSelected = (Parametro) sp_pay_method.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getBaseContext(), "No se ha seleccionado NADA!", Toast.LENGTH_SHORT).show();
            }
        });

        sp_type_bill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeBillSelected = (Parametro) sp_type_bill.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getBaseContext(), "No se ha seleccionado NADA!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddPay(){
        try {


        EditText payedBill = findViewById(R.id.et_new_pay);
        EditText payedDate = findViewById(R.id.et_new_pay_date);
        Float payed = Float.parseFloat(payedBill.getText().toString());
        String _date = payedDate.getText().toString();
        if( payed <= 0 ){
            Toast.makeText(this, "debe agregar un monto", Toast.LENGTH_SHORT).show();
            return;
        }

        if(_date.length() != 10){
            Toast.makeText(this, "debe agregar una fecha correcta", Toast.LENGTH_SHORT).show();
            return;
        }

        if(payMethodSelected.getId() == 0){
            Toast.makeText(this, "debe seleccionar un metodo de pago", Toast.LENGTH_SHORT).show();
            return;
        }
        if(typeBillSelected.getId() == 0){
            Toast.makeText(this, "debe seleccionar un tipo de gasto", Toast.LENGTH_SHORT).show();
            return;
        }

        db.execSQL("INSERT INTO bills(date, pay, id_pay_method, id_type_bill, month) VALUES ('"+_date+ "',"+payed+","+payMethodSelected.getId()+","+typeBillSelected.getId()+", 1)");
        Toast.makeText(this, "Creacion Correcta.", Toast.LENGTH_SHORT).show();
        finish();
        }catch (Exception ex){
            Toast.makeText(this, "Debe completar todos los datos", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void getPayMethodData(){
        String query = "SELECT id, name FROM pay_methods";
        Cursor c = db.rawQuery(query, null);
        pm_list = new ArrayList<>();
        Parametro p = new Parametro();
        p.setNombre("Pagado con");
        p.setId(0);
        pm_list.add(p);
        if (c.moveToFirst()){
            do{
                p = new Parametro();
                p.setNombre(c.getString(1));
                p.setId(c.getInt(0));
                pm_list.add(p);
            }while (c.moveToNext());

        }
    }

    private void getTypeBillData(){
        String query = "SELECT id, name FROM types_bill";
        Cursor c = db.rawQuery(query, null);
        tb_list = new ArrayList<>();
        Parametro p = new Parametro();
        p.setNombre("Gastado en");
        p.setId(0);
        tb_list.add(p);
        if (c.moveToFirst()){
            do{
                p = new Parametro();
                p.setNombre(c.getString(1));
                p.setId(c.getInt(0));
                tb_list.add(p);
            }while (c.moveToNext());

        }
    }
}