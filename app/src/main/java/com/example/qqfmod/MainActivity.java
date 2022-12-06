package com.example.qqfmod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qqfmod.databinding.ActivityMainBinding;

import org.fmod.FMOD;

public class MainActivity extends AppCompatActivity {
    private static  final  int MODE_NORMAL=0;
    private static final int MODE_LUOLI = 1; //
    private static final int MODE_DASHU = 2; //
    private static final int MODE_JINGSONG = 3; //
    private static final int MODE_GAOGUAI = 4; //
    private static final int MODE_KONGLING = 5; //
    static {
        System.loadLibrary("native-lib");
    }
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path =  "file:///android_asset/11.mp3";
        FMOD.init(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FMOD.close();
    }
    // 六个 点击事件
    public void onFix(@org.jetbrains.annotations.NotNull View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                if (path == null){
                    Log.d("YJH", "onFix: btn_normal");
                }
                voiceChangeNative(MODE_NORMAL, path); // 真实开发中，必须子线程  JNI线程（很多坑）
                break;
            case R.id.btn_luoli:
                Log.d("YJH", "onFix: btn_luoli");
                voiceChangeNative(MODE_LUOLI, path);
                break;
            case R.id.btn_dashu:
                Log.d("YJH", "onFix: btn_dashu");
                voiceChangeNative(MODE_DASHU, path);
                break;
            case R.id.btn_jingsong:
                voiceChangeNative(MODE_JINGSONG, path);
                break;
            case R.id.btn_gaoguai:
                voiceChangeNative(MODE_GAOGUAI, path);
                break;
            case R.id.btn_kongling:
                voiceChangeNative(MODE_KONGLING, path);
                break;
        }
    }
    private native void voiceChangeNative(int nodeNormal,String path);
    private void playerEnd(String msg) {
        Toast.makeText(this, "" +msg, Toast.LENGTH_SHORT).show();
    }
}