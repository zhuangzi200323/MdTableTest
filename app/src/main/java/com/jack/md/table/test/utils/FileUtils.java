package com.jack.md.table.test.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.jack.md.table.test.model.MsgBean;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    private static String TAG = FileUtils.class.getSimpleName();

    public static InputStream getInputStream(String path, boolean isSdPath) {
        InputStream in;
        try {
            if (isSdPath) {
                in = new FileInputStream(path);
            } else {
                in = com.blankj.utilcode.util.Utils.getApp().getAssets().open(path);
            }
        } catch (Exception err){
            in = null;
        }
        return in;
    }

    public interface LineCallback {
        void testCallBack(String text);
    }
    public static StringBuilder readAll(String path, boolean isSdPath, LineCallback callback) {
        StringBuilder all = new StringBuilder(1024);
        try {
            InputStream in = getInputStream(path, isSdPath);
            if (in != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while((line = bufferedReader.readLine()) != null){
                    MsgBean msgBean = JSON.parseObject(line, MsgBean.class);
                    if (callback != null && msgBean != null && msgBean.getChoices().size() > 0) {
                        callback.testCallBack(msgBean.getChoices().get(0).getDelta().getContent());
                        try { Thread.sleep(30); } catch (Exception err) {}
                    }
                }
                bufferedReader.close();
                inputStreamReader.close();
                in.close();
            }
        } catch (Exception err){
            Log.e(TAG, "path = " + path + ", readAll err", err);
            return null;
        }
        return all;
    }
}
