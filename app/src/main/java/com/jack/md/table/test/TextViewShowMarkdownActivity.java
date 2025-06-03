package com.jack.md.table.test;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jack.md.table.test.databinding.ActivityTextviewShowMarkdownBinding;
import com.jack.md.table.test.utils.ExecutorUtils;
import com.jack.md.table.test.utils.FileUtils;

import io.noties.markwon.Markwon;
import io.noties.markwon.ext.tables.TablePlugin;
import io.noties.markwon.html.HtmlPlugin;
import io.noties.markwon.linkify.LinkifyPlugin;

public class TextViewShowMarkdownActivity extends AppCompatActivity {
    private ActivityTextviewShowMarkdownBinding uiBinding;
    private Markwon markwon;
    private String flowDataFromNetwork = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBinding = DataBindingUtil.setContentView(this, R.layout.activity_textview_show_markdown);

        markwon = Markwon.builder(this)
                .usePlugin(HtmlPlugin.create()) // 支持html
                //.usePlugin(ImagesPlugin.create())  // 支持图片
                .usePlugin(TablePlugin.create(this))  // 支持表格
                .usePlugin(LinkifyPlugin.create())  // 自动链接识别
                .build();

        loadData();
    }

    private void loadData() {
        ExecutorUtils.getExecutor().execute(()->{
            FileUtils.readAll("simulate.data.flow.txt", false, this::textCallBack);
        });
    }

    public void textCallBack(String text) {
        runOnUiThread(()->{
            flowDataFromNetwork += text;
            markwon.setMarkdown(uiBinding.dataTv, flowDataFromNetwork);
        });
    }

    public void reloadDataBtnClick(View view) {
        flowDataFromNetwork = "";
        loadData();
    }
}