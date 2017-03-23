package cn.fulldroid.lib.example;

import android.app.Application;

import org.xutils.x;


/**
 * Created by MDZZ on 2017/3/22.
 */

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
    }
}
