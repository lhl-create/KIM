package com.example.practice;
import android.content.Intent;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText userName, passWord;
    private CheckBox box;
    private Button login;
    File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName =findViewById(R.id.et_username);
        passWord =findViewById(R.id.et_password);
        box = findViewById(R.id.cb_remerber);
        login= findViewById(R.id.bt_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        try {
            load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 点击登陆，写入账户密码的方法
    @SuppressLint("ShowToast")
    public void login(View v) throws IOException {
        String name = userName.getText().toString();
        String pwd = passWord.getText().toString();
        FileOutputStream fos = null;
        // 判断是否有勾选记住密码
        if (box.isChecked()) {
            try {

                file = new File(getFilesDir(), "info.txt");
                fos = new FileOutputStream(file);
                // 将name和pwd转化为字节数组写入。##是为了方便待会分割
                fos.write((name + "##" + pwd).getBytes());
                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();

            } finally {
                if (fos != null) {
                    fos.close();
                }
            }

        } else {
            // 如果用户没有勾选记住密码，就判断file是否存在，存在就删除
            if (file.exists()) {
                file.delete();
                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 加载账户密码的方法
    public void load() throws IOException {
        FileInputStream fiStream = null;
        BufferedReader br = null;
        file = new File(getFilesDir(), "info.txt");
        if (file.exists()) {
            try {
                fiStream = new FileInputStream(file);
                /* 将字节流转化为字符流，转化是因为我们知道info.txt
                 * 只有一行数据，为了使用readLine()方法，所以我们这里
                 * 转化为字符流，其实用字节流也是可以做的。但比较麻烦
                 */
                br = new BufferedReader(new InputStreamReader(fiStream));
                //读取info.txt
                String str = br.readLine();
                //分割info.txt里面的内容。这就是为什么写入的时候要加入##的原因
                String arr[] = str.split("##");
                userName.setText(arr[0]);
                passWord.setText(arr[1]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    br.close();
                }
            }
        } else {

        }
    }
}