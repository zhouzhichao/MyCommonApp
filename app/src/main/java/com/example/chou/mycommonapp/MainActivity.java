package com.example.chou.mycommonapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chou.mycommonapp.databinding.ActivityMainBinding;
import com.example.chou.mycommonapp.view.PercentCircleView;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;

public class MainActivity extends AppCompatActivity {

    private PercentCircleView percent_circle;
    private View c1;
    private View c2;
    private TextView tv1;
    private TextView tv2;
    private TextView tv;
    private ActivityMainBinding mainBinding;
    private FloatingActionButton fab;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Toolbar toolbar;
    private FrameLayout llTitleMenu;
    private ViewPager vpContent;
    private ImageView ivTitleGank;
    private ImageView ivTitleOne;
    private ImageView ivTitleDou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initId();
    }
    private void initId() {
        drawerLayout = mainBinding.drawerLayout;
        navView = mainBinding.navView;
        toolbar = mainBinding.include.toolbar;
        llTitleMenu = mainBinding.include.llTitleMenu;
        vpContent = mainBinding.include.vpContent;
        fab = mainBinding.include.fab;
        fab.setVisibility(View.GONE);

        ivTitleGank = mainBinding.include.ivTitleGank;
        ivTitleOne = mainBinding.include.ivTitleOne;
        ivTitleDou = mainBinding.include.ivTitleDou;
    }

    /*private void firstMethod() {
        setContentView(R.layout.activity_main);
        ShareSDK.initSDK(this);
        percent_circle = ((PercentCircleView) findViewById(R.id.percent_circle));
        percent_circle.setData(90);
        int allCircle= Color.argb(255,105, 210, 249);
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
    }*/

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
       // oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
       // oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        SinaWeibo sinaWeibo=new SinaWeibo(this);
        sinaWeibo.SSOSetting(false);
// 启动分享GUI
        oks.show(this);
    }

    public void shareClick(View view) {
        showShare();
    }
}
