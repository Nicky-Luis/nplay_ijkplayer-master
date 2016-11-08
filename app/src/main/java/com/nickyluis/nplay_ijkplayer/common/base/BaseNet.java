package com.nickyluis.nplay_ijkplayer.common.base;


import android.app.Activity;
import android.os.Environment;
import android.view.View;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicky on 2016/5/14.
 */
public class BaseNet {
    private static final String TAG = "BaseNet";
    //超时值
    private static final int Time_Out_Value = 20000;

    /**
     * post请求
     */
    public void postRequest(Activity tag, String url, Gson gson, StringCallback callBack) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(gson.toString())
                .tag(tag)
                .build()
                .execute(callBack);
    }

    /**
     * get 请求
     */
    public void getRequest(Activity tag, String url, Map<String, String> params,
                           StringCallback callBack) {
        OkHttpUtils
                .get()
                .url(url)
                .tag(tag)
                .params(params)
                .build()
                .execute(callBack);
    }

    /**
     * 上传文件
     */
    public void postFile(Activity tag, String url, StringCallback callBack, String dir,
                         String name) {
        File file = new File(dir, name);
        if (!file.exists()) {
            return;
        }
        OkHttpUtils
                .postFile()
                .url(url)
                .tag(tag)
                .file(file)
                .build()
                .execute(callBack);
    }

    /**
     * 带参数的文件上传
     */
    public void uploadFile(Activity tag, String url, Map<String, String> params, String dir,
                           String name,
                           StringCallback callBack) {
        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            return;
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");

        OkHttpUtils.post()
                .addFile("mFile", name, file)
                .url(url)
                .params(params)
                .headers(headers)
                .build()
                .execute(callBack);
    }

    /**
     * 上传多个文件
     */
    public void multiFileUpload(String url,
                                StringCallback callBack) {
        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        File file2 = new File(Environment.getExternalStorageDirectory(), "test1#.txt");
        if (!file.exists()) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        OkHttpUtils.post()//
                .addFile("mFile", "messenger_01.png", file)//
                .addFile("mFile", "test1.txt", file2)//
                .url(url)
                .params(params)//
                .build()//
                .execute(callBack);
    }


    /**
     * 下载文件
     */
    public void downloadFile(Activity tag, String url, FileCallBack callBack) {
        OkHttpUtils
                .get()
                .url(url)
                .tag(tag)
                .build()
                .execute(callBack);
    }

    /**
     * 下载图片
     */
    public void getImage(Activity tag, String url, BitmapCallback callBack) {
        OkHttpUtils.get()
                .tag(tag)
                .url(url)
                .tag(this)
                .build()
                .connTimeOut(Time_Out_Value)
                .readTimeOut(Time_Out_Value)
                .writeTimeOut(Time_Out_Value)
                .execute(callBack);
    }

    /**
     * 清除缓存
     */
    public void clearSession(View view) {
        OkHttpUtils.getInstance().getCookieStore().removeAll();
    }
}
