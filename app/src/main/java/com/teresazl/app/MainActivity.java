package com.teresazl.app;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.teresazl.library.CircleProgressBarWithNumber;
import com.teresazl.library.HorizontalProgressBarWithNumber;
import com.teresazl.view.R;

public class MainActivity extends AppCompatActivity {

    private static final int MSG_UPDATE = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int progress = mHProgressBar.getProgress();
            mHProgressBar.setProgress(++progress);
            mCProgressBar.setProgress(++progress);

            if (progress >= 100) {
                mHandler.removeMessages(MSG_UPDATE);
            }

            mHandler.sendEmptyMessageDelayed(MSG_UPDATE, 100);
        }
    };


    private HorizontalProgressBarWithNumber mHProgressBar;
    private CircleProgressBarWithNumber mCProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHProgressBar = (HorizontalProgressBarWithNumber) findViewById(R.id.h_progressbar);
        mCProgressBar = (CircleProgressBarWithNumber) findViewById(R.id.c_progressbar);

        mHandler.sendEmptyMessage(MSG_UPDATE);
    }


}
