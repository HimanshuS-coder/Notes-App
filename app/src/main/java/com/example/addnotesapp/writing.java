package com.example.addnotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class writing extends AppCompatActivity {
    EditText editText;
    int getitemnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        editText=findViewById(R.id.editTextTextPersonName);

        Intent intent=getIntent();
        getitemnote = intent.getIntExtra("getitemnote",-1);
        if (getitemnote!=-1)
        {
            editText.setText(MainActivity.notes.get(getitemnote));
        }else
        {
            MainActivity.notes.add("");
            getitemnote=MainActivity.notes.size() -1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(getitemnote,String.valueOf(s));
                MainActivity.note.notifyDataSetChanged();

                //Shared preferences

                SharedPreferences sharedPreferences=getSharedPreferences("com.example.addnotesapp", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}