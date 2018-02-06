package com.akshaykale.snapview;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.RequiresApi;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    public final int ANIMATION_DURATION = 500;

    //Views
    ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    CoordinatorLayout rootView;

    //orange to red
    String[] colourBlentA = {"#F1BA48","#EFB14B","#ECA94D","#EAA050", "#E89753","#E58F56","#E38658","#E07E5B","#DE755E","#DC6C61","#D96463","#D75B66"};// [];
    //orange to blue
    String[] colourBlentB = {"#F1BA48", "#E3B755","#D4B462","#C6B170","#B8AE7D","#A9AB8A","#9BA797","#8CA4A4","#7EA1B1","#709EBF","#619BCC","#5398D9"};// [];


    //black to violet
    //String[] colourBlentA = {"#000000", "#0E0310","#1C0720","#2A0A30","#380E40","#461150","#55155F","#63186F","#711C7F","#7F1F8F","#8D239F","#9B26AF"};// [];
    //black to orange
    //String[] colourBlentB = {"#000000", "#170803","#2E1006","#461809","#5D200C","#74280F","#8B2F13","#A23716","#B93F19","#D1471C","#E84F1F","#FF5722"};// [];


    //yellow to violet
    //String[] colourBlentA = {"#FFFF00", "#F6EB10","#EDD820","#E4C430","#DBB040","#D29C50","#C8895F","#BF756F","#B6617F","#AD4D8F","#A43A9F","#9B26AF"};// [];
    //yellow to orange
    //String[] colourBlentB = {"#FFFF00", "#FFF003","#FFE006","#FFD109","#FFC20C","#FFB30F","#FFA313","#FF9416","#FF8519","#FF761C","#FF661F","#FF5722"};// [];
    //String[] colourBlentA = {"#", "#","#","#","#","#","#","#","#","#","#","#"};// [];

    int CURRENT_POSITION = 1;
    private Window window;

    TextView toolbarTitle;

    ImageView iv_left_icon, iv_right_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        window = getWindow();
        rootView = findViewById(R.id.root_view);
        viewPager = findViewById(R.id.view_pager_main);
        toolbarTitle = findViewById(R.id.tv_toolbar_title);

        iv_left_icon = findViewById(R.id.ib_titalbar_left_icon);
        iv_right_icon = findViewById(R.id.ib_titalbar_right_icon);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1,false);
        changeStatusBarColour(Color.parseColor("#F9A825"));

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
                    rootView.setBackgroundColor(Color.parseColor(colourBlentA[9])); //Change the title bar colour
                    toolbarTitle.setText("Title Red");  //change the actionbar widget
                    changeStatusBarColour(Color.parseColor("#D50000")); //change the status bar colour

                    YoYo.with(Techniques.FadeInRight)
                            .duration(ANIMATION_DURATION)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {iv_right_icon.setImageResource(R.drawable.ico_add);}
                                @Override
                                public void onAnimationEnd(Animator animator) {}
                                @Override
                                public void onAnimationCancel(Animator animator) {}
                                @Override
                                public void onAnimationRepeat(Animator animator) {}
                            }).playOn(iv_right_icon);
                    YoYo.with(Techniques.FadeOutLeft)
                            .duration(ANIMATION_DURATION)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {}
                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    //iv_left_icon.setImageResource(R.drawable.ico_gallery);
                                }
                                @Override
                                public void onAnimationCancel(Animator animator) {}
                                @Override
                                public void onAnimationRepeat(Animator animator) {}
                            }).playOn(iv_left_icon);
                }else if (position ==1){
                    rootView.setBackgroundColor(Color.parseColor(colourBlentA[0]));
                    toolbarTitle.setText("Title Orange");
                    changeStatusBarColour(Color.parseColor("#F9A825"));

                    YoYo.with(Techniques.FadeInRight)
                            .duration(ANIMATION_DURATION)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {iv_right_icon.setImageResource(R.drawable.ico_gallery);}
                                @Override
                                public void onAnimationEnd(Animator animator) {}
                                @Override
                                public void onAnimationCancel(Animator animator) {}
                                @Override
                                public void onAnimationRepeat(Animator animator) {}
                            }).playOn(iv_right_icon);
                    YoYo.with(Techniques.FadeInLeft)
                            .duration(ANIMATION_DURATION)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {iv_left_icon.setImageResource(R.drawable.ico_menu_list);}
                                @Override
                                public void onAnimationEnd(Animator animator) {}
                                @Override
                                public void onAnimationCancel(Animator animator) {}
                                @Override
                                public void onAnimationRepeat(Animator animator) {}
                            }).playOn(iv_left_icon);
                }else if (position == 2){
                    rootView.setBackgroundColor(Color.parseColor(colourBlentB[9]));
                    toolbarTitle.setText("Title Blue");
                    changeStatusBarColour(Color.parseColor("#00838F"));

                    YoYo.with(Techniques.FadeOutRight)
                            .duration(ANIMATION_DURATION)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {}
                                @Override
                                public void onAnimationEnd(Animator animator) {}
                                @Override
                                public void onAnimationCancel(Animator animator) {}
                                @Override
                                public void onAnimationRepeat(Animator animator) {}
                            }).playOn(iv_right_icon);
                    YoYo.with(Techniques.FadeInLeft)
                            .duration(ANIMATION_DURATION)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {iv_left_icon.setImageResource(R.drawable.ico_add);}
                                @Override
                                public void onAnimationEnd(Animator animator) {}
                                @Override
                                public void onAnimationCancel(Animator animator) {}
                                @Override
                                public void onAnimationRepeat(Animator animator) {}
                            }).playOn(iv_left_icon);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("pager onPageStateChan","state: "+state);
            }
        });

        ViewPager.PageTransformer pageTransformer = new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                view.setTranslationX(-(view.getWidth() * -position)/2);

                if(position <= -1.0F || position >= 1.0F) {
                    view.setAlpha(0.0F);
                } else if( position == 0.0F ) {
                    view.setAlpha(1.0F);
                } else {
                    // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                    view.setAlpha(1.0F - Math.abs(position));
                }
            }
        };
        viewPager.setPageTransformer(false, pageTransformer);
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

    public void SlideUp(View view){
        float height = view.getHeight();

        TranslateAnimation animate = new TranslateAnimation(0,0,0,0);

        animate.setDuration(500);
        animate.setFillAfter(true);

        view.animate().translationY((float)(0-0.62*height)).start();
        view.startAnimation(animate);
        view.setVisibility(View.INVISIBLE);
    }

    public void FadeInAnimation(final ImageView view, final int res){

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(5000);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setImageResource(res);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fadeIn.startNow();
    }

    public void FadeOutAnimation(final View view){
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(5000);
        fadeOut.setDuration(5000);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fadeOut.start();
    }
}
