package com.jack.md.table.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jack.md.table.test.databinding.ActivityTablelayoutShowMarkdownBinding;

public class TableLayoutShowMarkdownActivity extends AppCompatActivity {
    private ActivityTablelayoutShowMarkdownBinding uiBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBinding = DataBindingUtil.setContentView(this, R.layout.activity_tablelayout_show_markdown);
    }

}