package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class listeView extends AppCompatActivity {
    HBHandler db= new HBHandler(this);
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_view);
        lv = this.findViewById(R.id.listView);

        Cursor cursor =  db.showStudents();
        myAdapter adapter= new myAdapter(this, cursor);
        lv.setAdapter(adapter);


    }
}