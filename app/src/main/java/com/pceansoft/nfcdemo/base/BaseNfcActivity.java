package com.pceansoft.nfcdemo.base;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by LSH on 2016/12/13.
 *1.子类需要在onCreate方法中做Activity初始化。
 * 2.子类需要在onNewIntent方法中进行NFC标签相关操作。
 * 当launchMode设置为singleTop时，第一次运行调用onCreate方法，
 * 第二次运行将不会创建新的Activity实例，将调用onNewIntent方法
 * 所以我们获取intent传递过来的Tag数据操作放在onNewIntent方法中执行
 * 如果在栈中已经有该Activity的实例，就重用该实例(会调用实例的onNewIntent())
 * 只要NFC标签靠近就执行
 */

public class BaseNfcActivity extends AppCompatActivity{
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;

    /**
     * 启动activity,界面可以
     */
    @Override
    protected void onStart() {
        super.onStart();
        mNfcAdapter=NfcAdapter.getDefaultAdapter(this);
        mPendingIntent=PendingIntent.getActivity(this,0,new Intent(this,getClass()),0);
    }

    /**
     * 获得焦点，按钮可以点击
     */
    @Override
    protected void onResume() {
        super.onResume();
        //设置处理优于所有其他的NFC的处理
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    /**
     * 暂停activity 界面获取焦点，按钮可以点击
     */
    @Override
    protected void onPause() {
        super.onPause();
        //恢复默认状态
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }
}
