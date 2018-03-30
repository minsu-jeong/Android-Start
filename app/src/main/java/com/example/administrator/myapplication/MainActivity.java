package com.example.administrator.myapplication;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    SQLiteDatabase sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteDB = init_database();
        init_tables();

        text = (TextView) findViewById(R.id.txt_reg_login_id);
        text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_reg_login_id:
                Intent intent = new Intent(getApplicationContext(),register_login.class);
                startActivity(intent);
                break;
        }
    }

    private SQLiteDatabase init_database(){
        SQLiteDatabase db = null;
        File file = new File(getFilesDir(), "Myapplication.db");
        System.out.println("PATH : " + file.toString());
        try{
            db = SQLiteDatabase.openOrCreateDatabase(file,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(db == null) {
            System.out.println("DB creation failed. " + file.getAbsolutePath());
        }

        return db;
    }

    private void init_tables(){
        if(sqliteDB != null) {
            String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS RESOURCE_T(" +
                    "NO " + "INTEGER NOT NULL," +
                    "LOGIN_ID " + "TEXT," +
                    "LOGIN_NM " + "TEXT," +
                    "EMAIL_ID " + "TEXT" + ")";
            System.out.println(sqlCreateTbl);
            sqliteDB.execSQL(sqlCreateTbl);

        }
    }

}
