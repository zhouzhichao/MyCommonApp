package com.example.chou.mycommonapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.chou.mycommonapp.MainActivity;
import com.example.chou.mycommonapp.R;
import com.example.chou.mycommonapp.databinding.ActivityTransitionBinding;

public class TransitionActivity extends AppCompatActivity {

    private ActivityTransitionBinding mBinding;
    private boolean isIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_transition);
        onClickListener();
    }

    private void onClickListener() {
        mBinding.tvJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMainActicity();
            }
        });
    }

    private void toMainActicity() {
        if (isIn) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }
}
