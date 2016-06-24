package com.qq.tangtang.dawapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zhaoaiqiu on 2016/6/23.
 */
public class MyFragment extends Fragment{

    private WebView webView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_layout, null);
        webView = (WebView) view.findViewById(R.id.webView);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        initWebView(url);



        return view;
    }

    private void initWebView(String url){

        //对webView的设置稍后在做
        final WebSettings settings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
               settings.setBlockNetworkImage(true);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                settings.setBlockNetworkImage(false);
            }
        });
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);

        settings.setDisplayZoomControls(false);
        webView.loadUrl(url);

        webView.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    if(keyCode==KeyEvent.KEYCODE_BACK){
                        if(webView.canGoBack()){
                            webView.goBack();
                        }else {
                            getActivity().finish();
                        }
                    }
                }
                return true;
            }
        });

    }

}
