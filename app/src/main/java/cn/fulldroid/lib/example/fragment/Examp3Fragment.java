package cn.fulldroid.lib.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.fulldroid.lib.button.FullButton;
import cn.fulldroid.lib.example.R;
import cn.fulldroid.lib.pickphoto.PickPhotoView;

/**
 * Created by MDZZ on 2017/3/22.
 */

public class Examp3Fragment extends Fragment {


    FullButton openPhoto;
    FullButton text;

    FullButton background;

    FullButton radius;

    FullButton stroke;

    FullButton dash;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_examp3, null);
        initView(v);
        return v;
    }

    private void initView(View v) {

        openPhoto = (FullButton) v.findViewById(R.id.openPhoto);
        openPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PickPhotoView.Builder(getActivity())
                        .setPickPhotoSize(9)
                        .setShowCamera(true)
                        .setSpanCount(4)
                        .start();
            }
        });

        //设置不同状态下文字变色
        text = (FullButton) v.findViewById(R.id.text_test);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setEnabled(false);
            }
        });

        //最常用的设置不同背景
        background = (FullButton) v.findViewById(R.id.background_test);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.setEnabled(false);
            }
        });

        //设置四个角不同的圆角
        radius = (FullButton) v.findViewById(R.id.different_radius_test);
        radius.setRadius(new float[]{0, 0, 20, 20, 40, 40, 60, 60});


        //设置不同状态下边框颜色，宽度
        stroke = (FullButton) v.findViewById(R.id.stroke_test);
        stroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stroke.setEnabled(false);
            }
        });

        //设置间断
        dash = (FullButton) v.findViewById(R.id.dash_test);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dash.setEnabled(false);
            }
        });
    }
}
