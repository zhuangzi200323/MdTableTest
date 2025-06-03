package com.jack.md.table.test;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jack.md.table.test.adapter.VoiceListAdapter;
import com.jack.md.table.test.databinding.ActivityTablelayoutShowMarkdownBinding;
import com.jack.md.table.test.listener.CommonListener;
import com.jack.md.table.test.model.VoiceItem;
import com.jack.md.table.test.utils.ExecutorUtils;
import com.jack.md.table.test.utils.FileUtils;

public class TableLayoutShowMarkdownActivity extends AppCompatActivity implements CommonListener {
    private ActivityTablelayoutShowMarkdownBinding uiBinding;
    private VoiceListAdapter voiceListAdapter;
    private int voiceId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiBinding = DataBindingUtil.setContentView(this, R.layout.activity_tablelayout_show_markdown);

        uiBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        voiceListAdapter = new VoiceListAdapter(this, this);
        uiBinding.recyclerView.setAdapter(voiceListAdapter);

        loadData();
    }

    private void loadData() {
        ExecutorUtils.getExecutor().execute(()->{
            FileUtils.readAll("simulate.data.flow.txt", false, this::textCallBack);
//            if (voiceId < 1) { //模拟插入数据
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception err){}
//                voiceId ++;
//                loadData();
//            }
        });
    }

    public void textCallBack(String text) {
        runOnUiThread(()->{
            boolean res = false;
            for (int i = voiceListAdapter.getItems().size()-1; i >= 0; i--) {
                if (voiceListAdapter.getItems().get(i).id == voiceId) {
                    voiceListAdapter.getItems().get(i).text += text;
                    res = true;
                    break;
                }
            }
            if (!res) {
                VoiceItem item = new VoiceItem();
                item.id = voiceId;
                item.type = Constants.ROBOT;
                item.text = text;
                voiceListAdapter.getItems().add(item);
            }
            voiceListAdapter.notifyDataSetChanged();
        });
    }

    public void reloadDataBtnClick(View view) {
        voiceListAdapter.getItems().clear();
        voiceId = 0;
        loadData();
    }
}