package com.bw.movie.avtivity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.bw.movie.R;

public class EditionGXActivity extends AppCompatActivity {

    private WebView webview;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition_gx);
        initView();
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        webview.loadUrl("http://www.baidu.com");//加载url

    }
}
