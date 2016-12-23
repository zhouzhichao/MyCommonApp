package com.example.chou.mycommonapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.example.chou.mycommonapp.view.PercentCircleView;

public class MainActivity extends AppCompatActivity {

    private PercentCircleView percent_circle;
    private View c1;
    private View c2;
    private TextView tv1;
    private TextView tv2;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        percent_circle = ((PercentCircleView) findViewById(R.id.percent_circle));
        percent_circle.setData(90);
        int allCircle=Color.argb(255,105, 210, 249);
        percent_circle.setPaints(allCircle,Color.rgb(0xff, 0x82, 0x82));
        percent_circle.setVisibility(View.VISIBLE);
        c1 = ((View) findViewById(R.id.c1));
        c2 = ((View) findViewById(R.id.c2));
        tv1 = ((TextView) c1.findViewById(R.id.tv_list_item));
        tv2 = ((TextView) c2.findViewById(R.id.tv_list_item));
        tv = ((TextView) findViewById(R.id.tv));
        tv1.setCompoundDrawables(getResources().getDrawable(R.mipmap.icon_wallet),null,getResources().getDrawable(R.mipmap.icon_wallet),null);
        tv2.setCompoundDrawables(getResources().getDrawable(R.mipmap.icon_wallet),null,getResources().getDrawable(R.mipmap.icon_wallet),null);
        String text = String.format("￥%1$s  门市价:￥%2$s", 18.6, 22);
        int z = text.lastIndexOf("门");
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new AbsoluteSizeSpan(14), 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#afafaf")), z, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //颜色
        style.setSpan(new AbsoluteSizeSpan(22), z, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //字号

        tv.setText(style);
    }
}
