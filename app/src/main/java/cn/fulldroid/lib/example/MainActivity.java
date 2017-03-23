package cn.fulldroid.lib.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.fulldroid.lib.example.tab.BottomTabActivity;
import cn.fulldroid.lib.example.tab.TopTabActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBottomTab(View view){
        Intent intent = new Intent(this, BottomTabActivity.class);
        startActivity(intent);
    }

    public void openTopTab(View view){
        Intent intent = new Intent(this, TopTabActivity.class);
        startActivity(intent);
    }

}
