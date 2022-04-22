package com.example.trackmoney.Entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    static String DATABASENAME = "trakmoney";
    static Integer DATABASEVERSION = 1;
    static  SQLiteDatabase.CursorFactory factory = null;
    String drop_query;

    public Database(@Nullable Context context) {
        super(context, DATABASENAME, factory, DATABASEVERSION);
        drop_query = "DROP IF EXISTS "+ DATABASENAME;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        CreateDB(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(drop_query);
        CreateDB(sqLiteDatabase);
    }

    private void CreateDB(SQLiteDatabase db){
        String payMethodTable = "CREATE TABLE IF NOT EXISTS pay_methods (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        String typeBillTable = "CREATE TABLE IF NOT EXISTS types_bill (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        String billsTable = "CREATE TABLE IF NOT EXISTS bills (id INTEGER PRIMARY KEY AUTOINCREMENT, date DATE, pay REAL, id_pay_method INT, id_type_bill INT, month INT)";

        db.execSQL(payMethodTable);
        db.execSQL(typeBillTable);
        db.execSQL(billsTable);
    }
}
