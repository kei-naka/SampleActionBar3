package com.example.keigo.sampleactionbar3;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {

    public SearchView searchView;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * アイコン表示
         * ShowHomeEnabledとUseLogoEnabledの両方をtrueにする必要がある。
         */
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        /*
         * 背景色変更
         * xmlだとなぜかうまくいかなかった。
         * @see also http://tomoyamkung.net/2014/02/27/android-change-actionbar-backgroundcolor/
         */
        int color = R.color.action_bar_background;
        Drawable drawable = getApplicationContext().getResources().getDrawable(color);
        getSupportActionBar().setBackgroundDrawable(drawable);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        // 検索バーの設定
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = new SearchView(MainActivity.this);
        // 未入力時に検索窓に表示する文字列
        searchView.setQueryHint("search news");
        // true: 検索バー自体にsubmitボタンを表示する
        //searchView.setSubmitButtonEnabled(true);
        // true: 表示時はアイコンのみ表示、false: 表示時からアイコン化はせず、検索バーが表示される
        searchView.setIconified(false);
        // true: 検索バーに☓ボタンが表示され、☓ボタン押下でアイコン化できる、false: ☓ボタンがなく、検索バーが表示されっぱなし
        searchView.setIconifiedByDefault(false);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        item.setActionView(searchView);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }
}
