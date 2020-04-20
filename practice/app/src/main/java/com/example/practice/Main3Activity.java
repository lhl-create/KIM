package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private JsonResultListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button sendRequest = findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);
        initView();

    }

    private void initView() {
        RecyclerView mResultList= findViewById(R.id.resource_list);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new JsonResultListAdapter();
        mResultList.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithOkHttp();
        }

    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://www.wanandroid.com/friend/json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    App app = gson.fromJson(responseData, App.class);
                    UpdataList(app);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void UpdataList(App app) {
        mAdapter.setData(app);
    }
}

