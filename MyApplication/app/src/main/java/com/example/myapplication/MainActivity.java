package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.graphics.Insets.add;

public class MainActivity extends AppCompatActivity {

    Button addBtn, deleteBtn, updateBtn, showBtn, searchBtn;
    EditText value1, value2, value3;
    String ID, name, familyName;
    HBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = this.findViewById(R.id.addBtn);
        deleteBtn= this.findViewById(R.id.deleteBtn);
        updateBtn = this.findViewById(R.id.editBtn);
        showBtn = this.findViewById(R.id.showBtn);
        searchBtn = this.findViewById(R.id.searchBtn);


        value1 = this.findViewById(R.id.id);
        value2= this.findViewById(R.id.name);
        value3= this.findViewById(R.id.familyName);

        ID = value1.getText().toString();
        name = value2.getText().toString();
        familyName = value3.getText().toString();

        db= new HBHandler(this);


        addBtn.setOnClickListener(v->addStudent());
        deleteBtn.setOnClickListener(v->deleteStudent());
        updateBtn.setOnClickListener(v->updateStudent());
        showBtn.setOnClickListener(v -> showAll());
        searchBtn.setOnClickListener(v-> searchStudent());


    }

    private void searchStudent() {
    }

    private void showAll() {
        Intent intent = new Intent(this, listeView.class);
        startActivity(intent);
    }

    private void updateStudent() {
        Student student = new Student(ID,name, familyName);
        int result = db.update(student);
        if(result == 1) {
            Toast.makeText(this,"student is updated successfully", Toast.LENGTH_LONG).show();
        }else if(result == -1){
            Toast.makeText(this,"student doesn't exist", Toast.LENGTH_LONG).show();
        }
    }


    private void addStudent() {
        Student student = new Student(ID,name, familyName);
        int result = db.add(student);
        if(result == 1)
        {
            Toast.makeText(this,"student is added successfully", Toast.LENGTH_LONG).show();
        }else if(ID.isEmpty() || name.isEmpty() || familyName.isEmpty()){

            Toast.makeText(this,"all the fields are required", Toast.LENGTH_LONG).show();

        }
    }
    private void deleteStudent() {
        Student student = new Student(ID,name, familyName);
        int result = db.delete(student);
        if(result == 1) {
            Toast.makeText(this,"student is deleted successfully", Toast.LENGTH_LONG).show();
        }else if(result == -1){
            Toast.makeText(this,"student doesn't exist", Toast.LENGTH_LONG).show();
        }

    }








}