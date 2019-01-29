package com.bw.movie.avtivity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

public class EditionGXActivity extends BaseActivity {

    private WebView webview;
    private ProgressBar progressbar;


    protected void initView() {
        setContentView(R.layout.activity_edition_gx);


    }

    @Override
    protected void initData() {
        webview = (WebView) findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        webview.loadUrl("http://www.baidu.com");//加载url
    }

    @Override
    protected void present() {

    }
}
