package com.jack.md.table.test.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.jack.md.table.test.Constants;
import com.jack.md.table.test.R;
import com.jack.md.table.test.databinding.VoiceItemBinding;
import com.jack.md.table.test.listener.CommonListener;
import com.jack.md.table.test.model.VoiceItem;

import java.util.Objects;

public class VoiceListAdapter extends BaseBindingAdapter<VoiceItem, VoiceItemBinding> {
    private final String TAG = VoiceListAdapter.class.getSimpleName();
    CommonListener listener;
    public VoiceListAdapter(Context context, CommonListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.voice_item;
    }

    @Override
    protected void onBindItem(VoiceItemBinding binding, VoiceItem data, int position) {
        binding.setData(data);
        binding.setHandler(listener);
        binding.setPos(position);
        binding.textTv.setText(TextUtils.isEmpty(data.text) ? data.hint : data.text);
        binding.robotIv.setVisibility(Objects.equals(data.type, Constants.ROBOT) ? View.VISIBLE : View.GONE);
        binding.peopleIv.setVisibility(Objects.equals(data.type, Constants.PEOPLE) ? View.VISIBLE : View.GONE);

        binding.executePendingBindings();
    }
}
