package cn.fulldroid.lib.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

import cn.fulldroid.lib.utils.LocalStorage;

/**
 * Created by MDZZ on 2017/3/22.
 */

public class BaseActivity extends AppCompatActivity {

    public LocalStorage mLocalStorage;
    public Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mLocalStorage = new LocalStorage(this);
        x.view().inject(this);
    }


}
