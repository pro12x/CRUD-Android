package com.example.crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper myDb;
    EditText editName, editPhone, editAge;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        editName = findViewById(R.id.name);
        editPhone = findViewById(R.id.phone);
        editAge = findViewById(R.id.age);
        btnAdd = findViewById(R.id.btn_add);

        Add();
    }

    public void Add()
    {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String phone = editPhone.getText().toString();
                String age = editAge.getText().toString();

                if(name.equals(String.valueOf("")))
                {
                    editName.setError("Please type your name");
                    //Toast.makeText(getApplicationContext(), "Please type your name", Toast.LENGTH_LONG).show();
                }
                if(phone.equals(String.valueOf("")))
                {
                    editPhone.setError("Please type your phone number");
                    //Toast.makeText(getApplicationContext(), "Please type your phone number", Toast.LENGTH_LONG).show();
                }
                if(age.equals(String.valueOf("")))
                {
                    editAge.setError("Please type your age");
                    //Toast.makeText(getApplicationContext(), "Please type your age", Toast.LENGTH_LONG).show();
                }

                boolean isInserted = myDb.add(name, phone, age);
                if(isInserted == true)
                {
                    Toast.makeText(getApplicationContext(), "You added", Toast.LENGTH_LONG).show();
                    editName.getText().clear();
                    editPhone.getText().clear();
                    editAge.getText().clear();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}