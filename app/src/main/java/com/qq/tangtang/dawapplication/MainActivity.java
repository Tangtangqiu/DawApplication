package com.qq.tangtang.dawapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity{

    private NavigationView navigationLeft;
    private FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;

    private String[] urls = new String[]{
      "http://www.baidu.com",
      "http://www.sohu.com",
      "http://www.wangyi.com",

    };
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private MyFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.content_fragment);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        initToolBar();
        initNavigation();
        initFrameLayout();


    }
    //对ToolBar的相关操作
    private void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.RED);
        toolbar.setSubtitle("测试");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //给抽屉创建一个监听器，这个监听器可以把抽屉和ToolBar联系起来
        ActionBarDrawerToggle listener = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        //2.同步toolbar上面的navitgation图标和drawer的状态
        listener.syncState();

        drawerLayout.addDrawerListener(listener);

    }

    //对Navigation控件的初始化
    private void initNavigation(){
        navigationLeft = (NavigationView) findViewById(R.id.navigation_left);
        //给Navigation控件设置点击监听事件
        navigationLeft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                //我只是对三个网址名字进行了点击处理
                switch(item.getItemId()){
                    case R.id.baidu:
                        //当点击的时候创建Fragment
                        fragment = new MyFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("url",urls[0]);
                        fragment.setArguments(bundle);
                        FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                        transaction1.replace(R.id.content_fragment, fragment);
                        transaction1.commit();

                        break;
                    case R.id.souhu:
                        MyFragment fragment1 = new MyFragment();
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("url",urls[1]);
                        fragment1.setArguments(bundle1);
                        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                        transaction2.replace(R.id.content_fragment,fragment1);
                        transaction2.commit();
                        break;
                    case R.id.wangyi:
                        MyFragment fragment2 = new MyFragment();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("url",urls[2]);
                        fragment2.setArguments(bundle2);
                        FragmentTransaction transaction3 = fragmentManager.beginTransaction();
                        transaction3.replace(R.id.content_fragment,fragment2);
                        transaction3.commit();
                        break;
                }
                //点击完成后关闭侧换菜单
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    //初始化控件
    private void initFrameLayout(){
        fragmentManager = getSupportFragmentManager();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onBackPressed(){
        super.onBackPressed();
    }*/

     /*@Override
    public void onBackPressed(){

        Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();
        fragment.setListener(new MyFragment.setListenner(){
            @Override
            public void getWebView(WebView webViw){
                Log.e("getWebView","getWebView");
                if(webViw.canGoBack()){
                    Log.e("getWebView","getWebView1");
                    webViw.goBack();
                }else {
                    Log.e("getWebView","getWebView2");
                    finish();
                }

            }
        });

    }*/
}
