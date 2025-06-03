package com.jack.md.table.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void textViewShowMarkdownClick(View view) {
        startActivity(new Intent(this, TextViewShowMarkdownActivity.class));
    }

    public void tableLayoutShowMarkdownClick(View view) {
        startActivity(new Intent(this, TableLayoutShowMarkdownActivity.class));
    }
}