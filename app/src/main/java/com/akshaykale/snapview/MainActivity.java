package com.akshaykale.snapview;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    //Views
    ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    CoordinatorLayout rootView;

    //black to violet
    String[] colourBlentA = {"#000000", "#0E0310","#1C0720","#2A0A30","#380E40","#461150","#55155F","#63186F","#711C7F","#7F1F8F","#8D239F","#9B26AF"};// [];
    //black to orange
    String[] colourBlentB = {"#000000", "#170803","#2E1006","#461809","#5D200C","#74280F","#8B2F13","#A23716","#B93F19","#D1471C","#E84F1F","#FF5722"};// [];


    //yellow to violet
    //String[] colourBlentA = {"#FFFF00", "#F6EB10","#EDD820","#E4C430","#DBB040","#D29C50","#C8895F","#BF756F","#B6617F","#AD4D8F","#A43A9F","#9B26AF"};// [];
    //yellow to orange
    //String[] colourBlentB = {"#FFFF00", "#FFF003","#FFE006","#FFD109","#FFC20C","#FFB30F","#FFA313","#FF9416","#FF8519","#FF761C","#FF661F","#FF5722"};// [];
    //String[] colourBlentA = {"#", "#","#","#","#","#","#","#","#","#","#","#"};// [];

    int CURRENT_POSITION = 1;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        window = getWindow();
        rootView = findViewById(R.id.root_view);
        viewPager = findViewById(R.id.view_pager_main);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1,false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("pager onPageScrolled","Current:"+CURRENT_POSITION+"   position: "+position+"   offset: "+positionOffset+"    pixels: "+positionOffsetPixels);
                int index = index =(int) (positionOffset*10);
                switch (CURRENT_POSITION){
                    case 0:
                        rootView.setBackgroundColor(Color.parseColor(colourBlentA[10-index]));
                        break;
                    case 1:
                        if (position == 0){ //A<-B C
                            rootView.setBackgroundColor(Color.parseColor(colourBlentA[10-index]));
                        }else if (position ==1) { //A B->C
                            rootView.setBackgroundColor(Color.parseColor(colourBlentB[index]));
                        }
                        break;
                    case 2:
                        if (position == 2){
                            rootView.setBackgroundColor(Color.parseColor(colourBlentB[9]));
                        }else
                        rootView.setBackgroundColor(Color.parseColor(colourBlentB[index]));
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                CURRENT_POSITION = position;
                Log.d("pager onPageSelected","position: "+position);
                if (position == 0){
                    rootView.setBackgroundColor(Color.parseColor(colourBlentA[9]));
                    changeStatusBarColour(Color.parseColor("#691A99"));
                }else if (position ==1){
                    rootView.setBackgroundColor(Color.parseColor(colourBlentA[0]));
                    changeStatusBarColour(Color.parseColor("#000000"));
                }else if (position == 2){
                    rootView.setBackgroundColor(Color.parseColor(colourBlentB[9]));
                    changeStatusBarColour(Color.parseColor("#BF360C"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("pager onPageStateChan","state: "+state);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeStatusBarColour(int colour){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colour);
        }
    }
}
