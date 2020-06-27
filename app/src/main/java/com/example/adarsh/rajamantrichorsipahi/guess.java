package com.example.adarsh.rajamantrichorsipahi;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Random;


public class guess extends AppCompatActivity implements Animation.AnimationListener {

    String s;
    int chit, rate = 0, chance = 0, i = 0, x, g, l = 0;
    int[] urimages = new int[4];
    int[] score1array = new int[50];
    int[] score2array = new int[50];
    int[] score3array = new int[50];
    int[] score4array = new int[50];
    int k0, k1, k2, k3, p0, p1, p2, p3, n0, n1, n2, n3, sum1, sum2, sum3, sum4, score1, score2, score3, score4, imageno, j, click = 0;
    boolean isExit;
    int disabled = 0;
    String img0_path = "", img1_path = "", img2_path = "", img3_path = "";
    TextView tbb1, tbb2, tbb3, tbb4, scoreone, scoretwo, scorethree, scorefour, toa, name1, name2, name3, name4;
    Button ButtonRandom;
    Animation slideleft;
    ArrayList<Integer> randomList = new ArrayList<>();
    ImageView img0, img1, img2, img3;
    private boolean doubleBackToExitPressedOnce = false;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.next_chance:
                    if (disabled != 0) {
                        randomList.clear();
                        urimages = null;
                        urimages = new int[4];
                        img0.setImageResource(R.drawable.podopen);
                        img0_path = "/podopen.png";
                        img1.setImageResource(R.drawable.podopen);
                        img1_path = "/podopen.png";
                        img2.setImageResource(R.drawable.podopen);
                        img2_path = "/podopen.png";
                        img3.setImageResource(R.drawable.podopen);
                        img3_path = "/podopen.png";
                        score1 = score2 = score3 = score4 = 0;
                        i = 0;
                        rate = 0;
                        disabled = 0;
                        chit = 1;
                        l = 0;
                        scoreone.setText("");
                        scoretwo.setText("");
                        scorethree.setText("");
                        scorefour.setText("");
                        ButtonRandom.setEnabled(true);
                        p0 = p1 = p2 = p3 = 5;
                        n0 = n1 = n2 = n3 = 0;
                    }
                    return true;
                case R.id.winner:
                    if (isExit) {
                        Toast.makeText(getApplicationContext(), "Game is over .Please Exit !", Toast.LENGTH_SHORT).show();
                    }
                    if (!isExit) {
                        for (j = 0; j < score1array.length; j++) {
                            sum1 = sum1 + score1array[j];
                        }
                        String val1 = String.valueOf(sum1);
                        scoreone.setText(val1);
                        for (j = 0; j < score2array.length; j++) {
                            sum2 = sum2 + score2array[j];
                        }
                        String val2 = String.valueOf(sum2);
                        scoretwo.setText(val2);
                        for (j = 0; j < score3array.length; j++) {
                            sum3 = sum3 + score3array[j];
                        }
                        String val3 = String.valueOf(sum3);
                        scorethree.setText(val3);
                        for (j = 0; j < score4array.length; j++) {
                            sum4 = sum4 + score4array[j];
                        }
                        String val4 = String.valueOf(sum4);
                        scorefour.setText(val4);

                        if (chance == 0) {
                            toa.setText(R.string.Please_Complete_first_Chance);
                            toa.setVisibility(View.VISIBLE);
                            toa.startAnimation(slideleft);
                        }

                        if (disabled != 0) {
                            ButtonRandom.setEnabled(false);
                            BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
                            MenuItem menuItem1 = bottomNavigationView.getMenu().getItem(1);
                            menuItem1.setCheckable(false);
                            MenuItem menuItem2 = bottomNavigationView.getMenu().getItem(2);
                            menuItem2.setCheckable(false);
                            isExit = true;
                            //  next_chance.setEnabled(false);
                        }
                        if (sum1 > sum2 && sum1 > sum3 && sum1 > sum4)
                            Toast.makeText(getApplicationContext(), tbb1.getText() + " is Winner :) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum2 > sum1 && sum2 > sum3 && sum2 > sum4)
                            Toast.makeText(getApplicationContext(), tbb2.getText() + " is Winner :) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum3 > sum2 && sum3 > sum1 && sum3 > sum4)
                            Toast.makeText(getApplicationContext(), tbb3.getText() + " is Winner :) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum4 > sum2 && sum4 > sum3 && sum4 > sum1)
                            Toast.makeText(getApplicationContext(), tbb4.getText() + " is Winner :) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum1 == sum2 && sum2 == sum3 && sum3 == sum4) {
                            if (sum1 == sum2 && sum2 == sum3 && sum3 == sum4 && sum4 == 0)
                                Toast.makeText(getApplicationContext(), "None is winner in First Chance  , Please Start The Game", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(), tbb1.getText() + " , " + tbb2.getText() + " , " + tbb3.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        } else if (sum1 == sum2 && sum2 == sum3)
                            Toast.makeText(getApplicationContext(), tbb1.getText() + " , " + tbb2.getText() + " & " + tbb3.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum1 == sum2 && sum2 == sum4)
                            Toast.makeText(getApplicationContext(), tbb1.getText() + " , " + tbb2.getText() + " , " + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum2 == sum3 && sum3 == sum4)
                            Toast.makeText(getApplicationContext(), tbb2.getText() + " , " + tbb3.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum1 == sum3 && sum3 == sum4)
                            Toast.makeText(getApplicationContext(), tbb1.getText() + " , " + tbb3.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum1 == sum2)
                            Toast.makeText(getApplicationContext(), tbb1.getText() + " & " + tbb2.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum1 == sum3)
                            Toast.makeText(getApplicationContext(), tbb1.getText() + " & " + tbb3.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum1 == sum4)
                            Toast.makeText(getApplicationContext(), tbb1.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum2 == sum3)
                            Toast.makeText(getApplicationContext(), tbb2.getText() + " & " + tbb3.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum2 == sum4)
                            Toast.makeText(getApplicationContext(), tbb2.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                        else if (sum3 == sum4)
                            Toast.makeText(getApplicationContext(), tbb3.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                case R.id.info:
                    Intent intent = new Intent(guess.this, info.class);
                    startActivity(intent);
                    return true;
                case R.id.share:
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "https://play.google.com/store/apps/details?id=com.chit.adarsh.rajamantrichorsipahi";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out " + "Raja Mantri Chor Sipahi" + "Game ");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    return true;
                case R.id.moreApps:
                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                    intent1.setData(Uri.parse("market://search?q=pub:adarshgumashta"));
                    startActivity(intent1);
                    return true;
            }
            return false;
        }
    };

    public guess() {
        isExit = false;
        k0 = k1 = k2 = k3 = 0;
        p0 = p1 = p2 = p3 = 5;
        n0 = n1 = n2 = n3 = 0;
        sum1 = sum2 = sum3 = sum4 = 0;
        score1 = score2 = score3 = score4 = 0;
        chit = 1;

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess1);
        View decorView = getWindow().getDecorView();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);
        tbb1 = (TextView) findViewById(R.id.textView1);
        tbb2 = (TextView) findViewById(R.id.textView2);
        tbb3 = (TextView) findViewById(R.id.textView3);
        tbb4 = (TextView) findViewById(R.id.textView4);
        name1 = (TextView) findViewById(R.id.textView9);
        name2 = (TextView) findViewById(R.id.textView10);
        name3 = (TextView) findViewById(R.id.textView11);
        name4 = (TextView) findViewById(R.id.textView12);
        scoreone = (TextView) findViewById(R.id.textView5);
        scoretwo = (TextView) findViewById(R.id.textView6);
        scorethree = (TextView) findViewById(R.id.textView7);
        scorefour = (TextView) findViewById(R.id.textView8);
        ButtonRandom = (Button) findViewById(R.id.Br);
        img0 = (ImageView) findViewById(R.id.imageView1);
        img1 = (ImageView) findViewById(R.id.imageView2);
        img2 = (ImageView) findViewById(R.id.imageView3);
        img3 = (ImageView) findViewById(R.id.imageView4);
        toa = (TextView) findViewById(R.id.toastText);
        slideleft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_left);
        slideleft.setAnimationListener(this);

        img0.setImageResource(R.drawable.podopen);
        img0_path = "/podopen.png";
        img1.setImageResource(R.drawable.podopen);
        img1_path = "/podopen.png";
        img2.setImageResource(R.drawable.podopen);
        img2_path = "/podopen.png";
        img3.setImageResource(R.drawable.podopen);
        img3_path = "/podopen.png";

        Button btnrdm = (Button) ButtonRandom;
        String content = btnrdm.getText().toString();
        final String on = content;
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/xenippa1.ttf");
        tbb1.setTypeface(custom_font);
        tbb2.setTypeface(custom_font);
        tbb3.setTypeface(custom_font);
        tbb4.setTypeface(custom_font);
        name1.setTypeface(custom_font);
        name2.setTypeface(custom_font);
        name3.setTypeface(custom_font);
        name4.setTypeface(custom_font);
        btnrdm.setTypeface(custom_font);
        Intent inte = getIntent();
        String ed1 = inte.getStringExtra("edittext1");
        String ed2 = inte.getStringExtra("edittext2");
        String ed3 = inte.getStringExtra("edittext3");
        String ed4 = inte.getStringExtra("edittext4");
        tbb1.setText(ed1);
        tbb2.setText(ed2);
        tbb3.setText(ed3);
        tbb4.setText(ed4);


        ButtonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rate = 1;
                if (on.equals("Shuffle") || i == 4 && l == 0) {
                    if (chit == 1)
                        Guess(on);

                    else if (chance == 0) {
                        toa.setText(R.string.Please_Complete_first_Chance);
                        toa.setVisibility(View.VISIBLE);
                        toa.startAnimation(slideleft);
                    } else
                        toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                }
            }
        });

        img0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rate == 0) {
                    toa.setText(R.string.Please_Click_on_Shuffle);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                }

                imageno = 0;

                if (click == 1)
                    Click_Again_To_Close_Chit();

                else if (n0 == 1) {
                    switch (i) {
                        case 1:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining Three Chits", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining Two Chits", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining One Chit", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getApplicationContext(), "Please Click on Guess !", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else if (p0 == 2 && img0_path.equals("/podtlhalf.png")) {
                    Toast.makeText(getApplicationContext(), "Your Guess is Right", Toast.LENGTH_SHORT).show();
                    toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    chit = 0;
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");
                    name4.setText("");
//                    ButtonRandom.setEnabled(false);
                    img0.setImageResource(R.drawable.two);
                    img0_path = "/two.png";
                    l = 1;
                    disabled = 1;
                    chance = 1;
                    if (p1 == 3) {
                        if (p2 == 1) {
                            switch (k2) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }

                        if (p3 == 1) {
                            switch (k3) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img1.setImageResource(R.drawable.three);
                        img1_path = "/three.png";
                    }

                    if (p2 == 3) {
                        if (p1 == 1) {
                            switch (k1) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }

                        if (p3 == 1) {
                            switch (k3) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img2.setImageResource(R.drawable.three);
                        img2_path = "/three.png";
                    }


                    if (p3 == 3) {
                        if (p1 == 1) {
                            switch (k1) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;

                            }
                        }
                        if (p2 == 1) {
                            switch (k2) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img3.setImageResource(R.drawable.three);
                        img3_path = "/three.png";


                    }
                    String value1 = String.valueOf(score1);
                    String value2 = String.valueOf(score2);
                    String value3 = String.valueOf(score3);
                    String value4 = String.valueOf(score4);
                    scoreone.setText(value1);
                    scoretwo.setText(value2);
                    scorethree.setText(value3);
                    scorefour.setText(value4);
                    score1array[g] = score1;
                    score2array[g] = score2;
                    score3array[g] = score3;
                    score4array[g] = score4;
                    g = g + 1;
                } else if (p0 == 3 && img0_path.equals("/podtlhalf.png")) {
                    Toast.makeText(getApplicationContext(), "Your Guess is Wrong", Toast.LENGTH_SHORT).show();
                    toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    chit = 0;
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");
                    name4.setText("");
//
                    //  ButtonRandom.setEnabled(false);
                    img0.setImageResource(R.drawable.three);
                    img0_path = "/three.png";

                    l = 1;
                    disabled = 1;
                    chance = 1;
                    if (p1 == 2) {
                        switch (k1) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img1.setImageResource(R.drawable.two);
                        img1_path = "/two.png";


                    }
                    if (p2 == 2) {
                        switch (k2) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img2.setImageResource(R.drawable.two);
                        img2_path = "/two.png";


                    }
                    if (p3 == 2) {
                        switch (k3) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img3.setImageResource(R.drawable.two);
                        img3_path = "/two.png";
                    }
                    String value1 = String.valueOf(score1);
                    String value2 = String.valueOf(score2);
                    String value3 = String.valueOf(score3);
                    String value4 = String.valueOf(score4);
                    scoreone.setText(value1);
                    scoretwo.setText(value2);
                    scorethree.setText(value3);
                    scorefour.setText(value4);
                    score1array[g] = score1;
                    score2array[g] = score2;
                    score3array[g] = score3;
                    score4array[g] = score4;
                    g = g + 1;
                } else if (img0_path.equals("/podtlclosed.png")) {
                    click = 1;
                    x = GenerateRandom();
                    while (randomList.contains(x))
                        x = GenerateRandom();
                    randomList.add(new Integer(x));
                    Set_Images_of_Raja_Mantri_Chor_Sipahi(x, imageno);
                    urimages[i] = x;
                    i = i + 1;
                    k0 = i;
                    // To Make Chit Appear Before Msgbox
                    if (img0_path.equals("/zero.png")) {
                        p0 = 0;
                        switch (i) {
                            case 1:
                                score1 = score1 + 1000;
                                break;
                            case 2:
                                score2 = score2 + 1000;
                                break;
                            case 3:
                                score3 = score3 + 1000;
                                break;
                            case 4:
                                score4 = score4 + 1000;
                                break;
                        }
                    }
                    if (img0_path.equals("/one.png"))
                        p0 = 1;

                    if (img0_path.equals("/two.png"))
                        p0 = 2;

                    if (img0_path.equals("/three.png")) {
                        p0 = 3;
                        switch (i) {
                            case 1:
                                score1 = score1 + 250;
                                break;
                            case 2:
                                score2 = score2 + 250;
                                break;
                            case 3:
                                score3 = score3 + 250;
                                break;
                            case 4:
                                score4 = score4 + 250;
                                break;
                        }
                    }
                    if (img0_path.equals("/zero.png") || img0_path.equals("/one.png") || img0_path.equals("/two.png") || img0_path.equals("/three.png")) {
                        //  await Task.Delay(1500);

                        try {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Your process to do
                                    img0.setImageResource(R.drawable.podtlhalf);
                                    img0_path = "/podtlhalf.png";
                                    switch (i) {
                                        case 1:
                                            name1.setText(tbb1.getText());
                                            break;
                                        case 2:
                                            name1.setText(tbb2.getText());
                                            break;
                                        case 3:
                                            name1.setText(tbb3.getText());
                                            break;
                                        case 4:
                                            name1.setText(tbb4.getText());
                                            break;

                                    }
                                    whose_turn_it_is(i);
                                    n0 = 1;
                                    click = 0;
                                    chit = 1;
                                }
                            }, 1500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }


        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rate == 0) {
                    toa.setText(R.string.Please_Click_on_Shuffle);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                }

                imageno = 1;

                if (click == 1)
                    Click_Again_To_Close_Chit();

                else if (n1 == 1) {
                    switch (i) {
                        case 1:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining Three Chits", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining Two Chits", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining One Chit", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getApplicationContext(), "Please Click on Guess !", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else if (p1 == 2 && img1_path.equals("/podtrhalf.png")) {
                    Toast.makeText(getApplicationContext(), "Your Guess is Right", Toast.LENGTH_SHORT).show();
                    toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    chit = 0;
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");
                    name4.setText("");

//                    ButtonRandom.setEnabled(false);
                    img1.setImageResource(R.drawable.two);
                    img1_path = "/two.png";
                    l = 1;
                    disabled = 1;
                    chance = 1;
                    if (p0 == 3) {
                        if (p2 == 1) {
                            switch (k2) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }

                        if (p3 == 1) {
                            switch (k3) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img0.setImageResource(R.drawable.three);
                        img0_path = "/three.png";

                    }

                    if (p2 == 3) {
                        if (p0 == 1) {
                            switch (k0) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }

                        if (p3 == 1) {
                            switch (k3) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img2.setImageResource(R.drawable.three);
                        img2_path = "/three.png";
                    }


                    if (p3 == 3) {
                        if (p2 == 1) {
                            switch (k2) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;

                            }
                        }
                        if (p0 == 1) {
                            switch (k0) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img3.setImageResource(R.drawable.three);
                        img3_path = "/three.png";


                    }
                    String value1 = String.valueOf(score1);
                    String value2 = String.valueOf(score2);
                    String value3 = String.valueOf(score3);
                    String value4 = String.valueOf(score4);
                    scoreone.setText(value1);
                    scoretwo.setText(value2);
                    scorethree.setText(value3);
                    scorefour.setText(value4);
                    score1array[g] = score1;
                    score2array[g] = score2;
                    score3array[g] = score3;
                    score4array[g] = score4;
                    g = g + 1;
                } else if (p1 == 3 && img1_path.equals("/podtrhalf.png")) {
                    Toast.makeText(getApplicationContext(), "Your Guess is Wrong", Toast.LENGTH_SHORT).show();
                    toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    chit = 0;
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");
                    name4.setText("");
//
                    //                  ButtonRandom.setEnabled(false);
                    img1.setImageResource(R.drawable.three);
                    img1_path = "/three.png";

                    l = 1;
                    disabled = 1;
                    chance = 1;
                    if (p0 == 2) {
                        switch (k0) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img0.setImageResource(R.drawable.two);
                        img0_path = "/two.png";


                    }
                    if (p2 == 2) {
                        switch (k2) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img2.setImageResource(R.drawable.two);
                        img2_path = "/two.png";


                    }
                    if (p3 == 2) {
                        switch (k3) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img3.setImageResource(R.drawable.two);
                        img3_path = "/two.png";
                    }
                    String value1 = String.valueOf(score1);
                    String value2 = String.valueOf(score2);
                    String value3 = String.valueOf(score3);
                    String value4 = String.valueOf(score4);
                    scoreone.setText(value1);
                    scoretwo.setText(value2);
                    scorethree.setText(value3);
                    scorefour.setText(value4);
                    score1array[g] = score1;
                    score2array[g] = score2;
                    score3array[g] = score3;
                    score4array[g] = score4;
                    g = g + 1;
                } else if (img1_path.equals("/podtrclosed.png")) {
                    click = 1;
                    x = GenerateRandom();
                    while (randomList.contains(x))
                        x = GenerateRandom();
                    randomList.add(new Integer(x));
                    Set_Images_of_Raja_Mantri_Chor_Sipahi(x, imageno);
                    urimages[i] = x;
                    i = i + 1;
                    k1 = i;
                    // To Make Chit Appear Before Msgbox
                    if (img1_path.equals("/zero.png")) {
                        p1 = 0;
                        switch (i) {
                            case 1:
                                score1 = score1 + 1000;
                                break;
                            case 2:
                                score2 = score2 + 1000;
                                break;
                            case 3:
                                score3 = score3 + 1000;
                                break;
                            case 4:
                                score4 = score4 + 1000;
                                break;
                        }
                    }
                    if (img1_path.equals("/one.png"))
                        p1 = 1;

                    if (img1_path.equals("/two.png"))
                        p1 = 2;

                    if (img1_path.equals("/three.png")) {
                        p1 = 3;
                        switch (i) {
                            case 1:
                                score1 = score1 + 250;
                                break;
                            case 2:
                                score2 = score2 + 250;
                                break;
                            case 3:
                                score3 = score3 + 250;
                                break;
                            case 4:
                                score4 = score4 + 250;
                                break;
                        }
                    }
                    if (img1_path.equals("/zero.png") || img1_path.equals("/one.png") || img1_path.equals("/two.png") || img1_path.equals("/three.png")) {
                        //  await Task.Delay(1500);

                        try {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Your process to do
                                    img1.setImageResource(R.drawable.podtrhalf);
                                    img1_path = "/podtrhalf.png";
                                    switch (i) {
                                        case 1:
                                            name2.setText(tbb1.getText());
                                            break;
                                        case 2:
                                            name2.setText(tbb2.getText());
                                            break;
                                        case 3:
                                            name2.setText(tbb3.getText());
                                            break;
                                        case 4:
                                            name2.setText(tbb4.getText());
                                            break;

                                    }
                                    whose_turn_it_is(i);
                                    n1 = 1;
                                    click = 0;
                                    chit = 1;
                                }
                            }, 1500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }


        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rate == 0) {
                    toa.setText(R.string.Please_Click_on_Shuffle);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                }

                imageno = 2;

                if (click == 1)
                    Click_Again_To_Close_Chit();

                else if (n2 == 1) {
                    switch (i) {
                        case 1:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining Three Chits", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining Two Chits", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining One Chit", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getApplicationContext(), "Please Click on Guess !", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else if (p2 == 2 && img2_path.equals("/podblhalf.png")) {
                    Toast.makeText(getApplicationContext(), "Your Guess is Right", Toast.LENGTH_SHORT).show();
                    toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    chit = 0;
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");
                    name4.setText("");


                    img2.setImageResource(R.drawable.two);
                    img2_path = "/two.png";
                    l = 1;
                    disabled = 1;
                    chance = 1;
                    if (p0 == 3) {
                        if (p1 == 1) {
                            switch (k1) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }

                        if (p3 == 1) {
                            switch (k3) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img0.setImageResource(R.drawable.three);
                        img0_path = "/three.png";

                    }

                    if (p1 == 3) {
                        if (p0 == 1) {
                            switch (k0) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }

                        if (p3 == 1) {
                            switch (k3) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img1.setImageResource(R.drawable.three);
                        img1_path = "/three.png";
                    }


                    if (p3 == 3) {
                        if (p0 == 1) {
                            switch (k0) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;

                            }
                        }
                        if (p1 == 1) {
                            switch (k1) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img3.setImageResource(R.drawable.three);
                        img3_path = "/three.png";


                    }
                    String value1 = String.valueOf(score1);
                    String value2 = String.valueOf(score2);
                    String value3 = String.valueOf(score3);
                    String value4 = String.valueOf(score4);
                    scoreone.setText(value1);
                    scoretwo.setText(value2);
                    scorethree.setText(value3);
                    scorefour.setText(value4);
                    score1array[g] = score1;
                    score2array[g] = score2;
                    score3array[g] = score3;
                    score4array[g] = score4;
                    g = g + 1;
                } else if (p2 == 3 && img2_path.equals("/podblhalf.png")) {
                    Toast.makeText(getApplicationContext(), "Your Guess is Wrong", Toast.LENGTH_SHORT).show();
                    toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    chit = 0;
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");
                    name4.setText("");

                    img2.setImageResource(R.drawable.three);
                    img2_path = "/three.png";

                    l = 1;
                    disabled = 1;
                    chance = 1;
                    if (p0 == 2) {
                        switch (k0) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img0.setImageResource(R.drawable.two);
                        img0_path = "/two.png";


                    }
                    if (p1 == 2) {
                        switch (k1) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img1.setImageResource(R.drawable.two);
                        img1_path = "/two.png";


                    }
                    if (p3 == 2) {
                        switch (k3) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img3.setImageResource(R.drawable.two);
                        img3_path = "/two.png";
                    }
                    String value1 = String.valueOf(score1);
                    String value2 = String.valueOf(score2);
                    String value3 = String.valueOf(score3);
                    String value4 = String.valueOf(score4);
                    scoreone.setText(value1);
                    scoretwo.setText(value2);
                    scorethree.setText(value3);
                    scorefour.setText(value4);
                    score1array[g] = score1;
                    score2array[g] = score2;
                    score3array[g] = score3;
                    score4array[g] = score4;
                    g = g + 1;
                } else if (img2_path.equals("/podblclosed.png")) {
                    click = 1;
                    x = GenerateRandom();
                    while (randomList.contains(x))
                        x = GenerateRandom();
                    randomList.add(new Integer(x));
                    Set_Images_of_Raja_Mantri_Chor_Sipahi(x, imageno);
                    urimages[i] = x;
                    i = i + 1;
                    k2 = i;
                    // To Make Chit Appear Before Msgbox
                    if (img2_path.equals("/zero.png")) {
                        p2 = 0;
                        switch (i) {
                            case 1:
                                score1 = score1 + 1000;
                                break;
                            case 2:
                                score2 = score2 + 1000;
                                break;
                            case 3:
                                score3 = score3 + 1000;
                                break;
                            case 4:
                                score4 = score4 + 1000;
                                break;
                        }
                    }
                    if (img2_path.equals("/one.png"))
                        p2 = 1;

                    if (img2_path.equals("/two.png"))
                        p2 = 2;

                    if (img2_path.equals("/three.png")) {
                        p2 = 3;
                        switch (i) {
                            case 1:
                                score1 = score1 + 250;
                                break;
                            case 2:
                                score2 = score2 + 250;
                                break;
                            case 3:
                                score3 = score3 + 250;
                                break;
                            case 4:
                                score4 = score4 + 250;
                                break;
                        }
                    }
                    if (img2_path.equals("/zero.png") || img2_path.equals("/one.png") || img2_path.equals("/two.png") || img2_path.equals("/three.png")) {
                        //  await Task.Delay(1500);

                        try {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Your process to do
                                    img2.setImageResource(R.drawable.podblhalf);
                                    img2_path = "/podblhalf.png";
                                    switch (i) {
                                        case 1:
                                            name3.setText(tbb1.getText());
                                            break;
                                        case 2:
                                            name3.setText(tbb2.getText());
                                            break;
                                        case 3:
                                            name3.setText(tbb3.getText());
                                            break;
                                        case 4:
                                            name3.setText(tbb4.getText());
                                            break;

                                    }
                                    whose_turn_it_is(i);
                                    n2 = 1;
                                    click = 0;
                                    chit = 1;
                                }
                            }, 1500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }


        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rate == 0) {
                    toa.setText(R.string.Please_Click_on_Shuffle);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                }
                imageno = 3;

                if (click == 1)
                    Click_Again_To_Close_Chit();

                else if (n3 == 1) {
                    switch (i) {
                        case 1:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining Three Chits", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining Two Chits", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(getApplicationContext(), "Please Click on Remaining One Chit", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(getApplicationContext(), "Please Click on Guess !", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else if (p3 == 2 && img3_path.equals("/podbrhalf.png")) {
                    Toast.makeText(getApplicationContext(), "Your Guess is Right", Toast.LENGTH_SHORT).show();
                    toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    chit = 0;
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");
                    name4.setText("");

                    img3.setImageResource(R.drawable.two);
                    img3_path = "/two.png";
                    l = 1;
                    disabled = 1;
                    chance = 1;
                    if (p0 == 3) {
                        if (p2 == 1) {
                            switch (k2) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }

                        if (p1 == 1) {
                            switch (k1) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img0.setImageResource(R.drawable.three);
                        img0_path = "/three.png";

                    }

                    if (p1 == 3) {
                        if (p2 == 1) {
                            switch (k2) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }

                        if (p0 == 1) {
                            switch (k0) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img1.setImageResource(R.drawable.three);
                        img1_path = "/three.png";
                    }


                    if (p2 == 3) {
                        if (p0 == 1) {
                            switch (k0) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;

                            }
                        }
                        if (p1 == 1) {
                            switch (k1) {
                                case 1:
                                    score1 = score1 + 500;
                                    break;
                                case 2:
                                    score2 = score2 + 500;
                                    break;
                                case 3:
                                    score3 = score3 + 500;
                                    break;
                                case 4:
                                    score4 = score4 + 500;
                                    break;
                            }
                        }
                        img2.setImageResource(R.drawable.three);
                        img2_path = "/three.png";


                    }
                    String value1 = String.valueOf(score1);
                    String value2 = String.valueOf(score2);
                    String value3 = String.valueOf(score3);
                    String value4 = String.valueOf(score4);
                    scoreone.setText(value1);
                    scoretwo.setText(value2);
                    scorethree.setText(value3);
                    scorefour.setText(value4);
                    score1array[g] = score1;
                    score2array[g] = score2;
                    score3array[g] = score3;
                    score4array[g] = score4;
                    g = g + 1;
                } else if (p3 == 3 && img3_path.equals("/podbrhalf.png")) {
                    Toast.makeText(getApplicationContext(), "Your Guess is Wrong", Toast.LENGTH_SHORT).show();
                    toa.setText(R.string.Click_on_Next_Chance_or_Winner);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");
                    name4.setText("");

                    img3.setImageResource(R.drawable.three);
                    img3_path = "/three.png";
                    chit = 0;
                    l = 1;
                    disabled = 1;
                    chance = 1;
                    if (p0 == 2) {
                        switch (k0) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img0.setImageResource(R.drawable.two);
                        img0_path = "/two.png";


                    }
                    if (p1 == 2) {
                        switch (k1) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img1.setImageResource(R.drawable.two);
                        img1_path = "/two.png";


                    }
                    if (p2 == 2) {
                        switch (k2) {
                            case 1:
                                score1 = score1 + 500;
                                break;
                            case 2:
                                score2 = score2 + 500;
                                break;
                            case 3:
                                score3 = score3 + 500;
                                break;
                            case 4:
                                score4 = score4 + 500;
                                break;
                        }
                        img2.setImageResource(R.drawable.two);
                        img2_path = "/two.png";
                    }
                    String value1 = String.valueOf(score1);
                    String value2 = String.valueOf(score2);
                    String value3 = String.valueOf(score3);
                    String value4 = String.valueOf(score4);
                    scoreone.setText(value1);
                    scoretwo.setText(value2);
                    scorethree.setText(value3);
                    scorefour.setText(value4);
                    score1array[g] = score1;
                    score2array[g] = score2;
                    score3array[g] = score3;
                    score4array[g] = score4;
                    g = g + 1;
                } else if (img3_path.equals("/podbrclosed.png")) {
                    click = 1;
                    x = GenerateRandom();
                    while (randomList.contains(x))
                        x = GenerateRandom();
                    randomList.add(new Integer(x));
                    Set_Images_of_Raja_Mantri_Chor_Sipahi(x, imageno);
                    urimages[i] = x;
                    i = i + 1;
                    k3 = i;
                    // To Make Chit Appear Before Msgbox
                    if (img3_path.equals("/zero.png")) {
                        p3 = 0;
                        switch (i) {
                            case 1:
                                score1 = score1 + 1000;
                                break;
                            case 2:
                                score2 = score2 + 1000;
                                break;
                            case 3:
                                score3 = score3 + 1000;
                                break;
                            case 4:
                                score4 = score4 + 1000;
                                break;
                        }
                    }
                    if (img3_path.equals("/one.png"))
                        p3 = 1;

                    if (img3_path.equals("/two.png"))
                        p3 = 2;

                    if (img3_path.equals("/three.png")) {
                        p3 = 3;
                        switch (i) {
                            case 1:
                                score1 = score1 + 250;
                                break;
                            case 2:
                                score2 = score2 + 250;
                                break;
                            case 3:
                                score3 = score3 + 250;
                                break;
                            case 4:
                                score4 = score4 + 250;
                                break;
                        }
                    }
                    if (img3_path.equals("/zero.png") || img3_path.equals("/one.png") || img3_path.equals("/two.png") || img3_path.equals("/three.png")) {
                        //  await Task.Delay(1500);

                        try {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Your process to do
                                    img3.setImageResource(R.drawable.podbrhalf);
                                    img3_path = "/podbrhalf.png";
                                    switch (i) {
                                        case 1:
                                            name4.setText(tbb1.getText());
                                            break;
                                        case 2:
                                            name4.setText(tbb2.getText());
                                            break;
                                        case 3:
                                            name4.setText(tbb3.getText());
                                            break;
                                        case 4:
                                            name4.setText(tbb4.getText());
                                            break;

                                    }
                                    whose_turn_it_is(i);
                                    n3 = 1;
                                    click = 0;
                                    chit = 1;
                                }
                            }, 1500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }


        });


        /*Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(guess.this, info.class);
                startActivity(intent);
            }
        });*/
     /*   next_chance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disabled != 0) {
                    randomList.clear();
                    urimages = null;
                    urimages = new int[4];
                    img0.setImageResource(R.drawable.podopen);
                    img0_path = "/podopen.png";
                    img1.setImageResource(R.drawable.podopen);
                    img1_path = "/podopen.png";
                    img2.setImageResource(R.drawable.podopen);
                    img2_path = "/podopen.png";
                    img3.setImageResource(R.drawable.podopen);
                    img3_path = "/podopen.png";
                    score1 = score2 = score3 = score4 = 0;
                    i = 0;
                    rate = 0;
                    disabled = 0;
                    chit = 1;
                    l = 0;
                    scoreone.setText("");
                    scoretwo.setText("");
                    scorethree.setText("");
                    scorefour.setText("");
                    ButtonRandom.setEnabled(true);
                    p0 = p1 = p2 = p3 = 5;
                    n0 = n1 = n2 = n3 = 0;
                }
            }
        });

        PlayerWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (j = 0; j < score1array.length; j++) {
                    sum1 = sum1 + score1array[j];
                }
                String val1 = String.valueOf(sum1);
                scoreone.setText(val1);
                for (j = 0; j < score2array.length; j++) {
                    sum2 = sum2 + score2array[j];
                }
                String val2 = String.valueOf(sum2);
                scoretwo.setText(val2);
                for (j = 0; j < score3array.length; j++) {
                    sum3 = sum3 + score3array[j];
                }
                String val3 = String.valueOf(sum3);
                scorethree.setText(val3);
                for (j = 0; j < score4array.length; j++) {
                    sum4 = sum4 + score4array[j];
                }
                String val4 = String.valueOf(sum4);
                scorefour.setText(val4);

                if (chance == 0) {
                    toa.setText(R.string.Please_Complete_first_Chance);
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                }

                if (disabled != 0) {
                    ButtonRandom.setEnabled(false);
                    next_chance.setEnabled(false);
                }
                if (sum1 > sum2 && sum1 > sum3 && sum1 > sum4)
                    Toast.makeText(getApplicationContext(), tbb1.getText() + " is Winner :) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum2 > sum1 && sum2 > sum3 && sum2 > sum4)
                    Toast.makeText(getApplicationContext(), tbb2.getText() + " is Winner :) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum3 > sum2 && sum3 > sum1 && sum3 > sum4)
                    Toast.makeText(getApplicationContext(), tbb3.getText() + " is Winner :) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum4 > sum2 && sum4 > sum3 && sum4 > sum1)
                    Toast.makeText(getApplicationContext(), tbb4.getText() + " is Winner :) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum1 == sum2 && sum2 == sum3 && sum3 == sum4) {
                    if (sum1 == sum2 && sum2 == sum3 && sum3 == sum4 && sum4 == 0)
                        Toast.makeText(getApplicationContext(), "None is winner in First Chance  , Please Start The Game", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), tbb1.getText() + " , " + tbb2.getText() + " , " + tbb3.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                } else if (sum1 == sum2 && sum2 == sum3)
                    Toast.makeText(getApplicationContext(), tbb1.getText() + " , " + tbb2.getText() + " & " + tbb3.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum1 == sum2 && sum2 == sum4)
                    Toast.makeText(getApplicationContext(), tbb1.getText() + " , " + tbb2.getText() + " , " + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum2 == sum3 && sum3 == sum4)
                    Toast.makeText(getApplicationContext(), tbb2.getText() + " , " + tbb3.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum1 == sum3 && sum3 == sum4)
                    Toast.makeText(getApplicationContext(), tbb1.getText() + " , " + tbb3.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum1 == sum2)
                    Toast.makeText(getApplicationContext(), tbb1.getText() + " & " + tbb2.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum1 == sum3)
                    Toast.makeText(getApplicationContext(), tbb1.getText() + " & " + tbb3.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum1 == sum4)
                    Toast.makeText(getApplicationContext(), tbb1.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum2 == sum3)
                    Toast.makeText(getApplicationContext(), tbb2.getText() + " & " + tbb3.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum2 == sum4)
                    Toast.makeText(getApplicationContext(), tbb2.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
                else if (sum3 == sum4)
                    Toast.makeText(getApplicationContext(), tbb3.getText() + " & " + tbb4.getText() + " are Winners:) .Please Exit !", Toast.LENGTH_SHORT).show();
            }
        });
        distribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://play.google.com/store/apps/details?id=com.chit.adarsh.rajamantrichorsipahi";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out " + "Raja Mantri Chor Sipahi" + "Game ");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });*/
    }

    void Set_Images_of_Raja_Mantri_Chor_Sipahi(int x, int imgno) {


        switch (imgno) {
            case 0:
                switch (x) {
                    case 0:
                        img0.setImageResource(R.drawable.zero);
                        img0_path = "/zero.png";
                        break;
                    case 1:
                        img0.setImageResource(R.drawable.one);
                        img0_path = "/one.png";
                        break;
                    case 2:
                        img0.setImageResource(R.drawable.two);
                        img0_path = "/two.png";
                        break;
                    case 3:
                        img0.setImageResource(R.drawable.three);
                        img0_path = "/three.png";
                        break;

                }
                break;
            case 1:
                switch (x) {
                    case 0:
                        img1.setImageResource(R.drawable.zero);
                        img1_path = "/zero.png";
                        break;
                    case 1:
                        img1.setImageResource(R.drawable.one);
                        img1_path = "/one.png";
                        break;
                    case 2:
                        img1.setImageResource(R.drawable.two);
                        img1_path = "/two.png";
                        break;
                    case 3:
                        img1.setImageResource(R.drawable.three);
                        img1_path = "/three.png";
                        break;

                }
                break;
            case 2:
                switch (x) {
                    case 0:
                        img2.setImageResource(R.drawable.zero);
                        img2_path = "/zero.png";
                        break;
                    case 1:
                        img2.setImageResource(R.drawable.one);
                        img2_path = "/one.png";
                        break;
                    case 2:
                        img2.setImageResource(R.drawable.two);
                        img2_path = "/two.png";
                        break;
                    case 3:
                        img2.setImageResource(R.drawable.three);
                        img2_path = "/three.png";
                        break;

                }
                break;
            case 3:
                switch (x) {
                    case 0:
                        img3.setImageResource(R.drawable.zero);
                        img3_path = "/zero.png";
                        break;
                    case 1:
                        img3.setImageResource(R.drawable.one);
                        img3_path = "/one.png";
                        break;
                    case 2:
                        img3.setImageResource(R.drawable.two);
                        img3_path = "/two.png";
                        break;
                    case 3:
                        img3.setImageResource(R.drawable.three);
                        img3_path = "/three.png";
                        break;

                }
                break;

        }
    }


    void Click_Again_To_Close_Chit() {
        toa.setText(R.string.Click_Again_To_Close_Chit);
        toa.setVisibility(View.VISIBLE);
        toa.startAnimation(slideleft);
    }


    void Guess(String on) {

        if (p0 == 0 && img0_path.equals("/podtlhalf.png")) {
            img0.setImageResource(R.drawable.zero);
            img0_path = "/zero.png";
            name1.setText("");
        }
        if (p0 == 1 && img0_path.equals("/podtlhalf.png")) {
            img0.setImageResource(R.drawable.one);
            img0_path = "/one.png";
            name1.setText("");
        }
        if (p1 == 0 && img1_path.equals("/podtrhalf.png")) {
            img1.setImageResource(R.drawable.zero);
            img1_path = "/zero.png";
            name2.setText("");
        }
        if (p1 == 1 && img1_path.equals("/podtrhalf.png")) {
            img1.setImageResource(R.drawable.one);
            img1_path = "/one.png";
            name2.setText("");
        }
        if (p2 == 0 && img2_path.equals("/podblhalf.png")) {
            img2.setImageResource(R.drawable.zero);
            img2_path = "/zero.png";
            name3.setText("");
        }
        if (p2 == 1 && img2_path.equals("/podblhalf.png")) {
            img2.setImageResource(R.drawable.one);
            img2_path = "/one.png";
            name3.setText("");
        }
        if (p3 == 0 && img3_path.equals("/podbrhalf.png")) {
            img3.setImageResource(R.drawable.zero);
            img3_path = "/zero.png";
            name4.setText("");
        }
        if (p3 == 1 && img3_path.equals("/podbrhalf.png")) {
            img3.setImageResource(R.drawable.one);
            img3_path = "/one.png";
            name4.setText("");
        }
        n0 = n1 = n2 = n3 = 0;
        if (p0 == 1) {
            switch (k0) {

                case 1:
                    toa.setText(tbb1.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 2:
                    toa.setText(tbb2.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 3:
                    toa.setText(tbb3.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 4:
                    toa.setText(tbb4.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
            }

        }

        if (p1 == 1) {
            switch (k1) {
                case 1:
                    toa.setText(tbb1.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 2:
                    toa.setText(tbb2.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 3:
                    toa.setText(tbb3.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 4:
                    toa.setText(tbb4.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
            }

        }

        if (p2 == 1) {
            switch (k2) {
                case 1:
                    toa.setText(tbb1.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 2:
                    toa.setText(tbb2.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 3:
                    toa.setText(tbb3.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 4:
                    toa.setText(tbb4.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
            }
        }

        if (p3 == 1) {
            switch (k3) {
                case 1:
                    toa.setText(tbb1.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 2:
                    toa.setText(tbb2.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 3:
                    toa.setText(tbb3.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
                case 4:
                    toa.setText(tbb4.getText().toString().concat(getString(R.string._will_find_the_Chor)));
                    toa.setVisibility(View.VISIBLE);
                    toa.startAnimation(slideleft);
                    break;
            }

        }


        ButtonRandom.setText(R.string.Shuffle);
        if (img0_path.equals("/podopen.png") || img1_path.equals("/podopen.png") || img2_path.equals("/podopen.png") || img3_path.equals("/podopen.png")) {
            if (i == 0) {
                whose_turn_it_is(i);
            }
            img0.setImageResource(R.drawable.podtlclosed);
            img0_path = "/podtlclosed.png";
            img1.setImageResource(R.drawable.podtrclosed);
            img1_path = "/podtrclosed.png";
            img2.setImageResource(R.drawable.podblclosed);
            img2_path = "/podblclosed.png";
            img3.setImageResource(R.drawable.podbrclosed);
            img3_path = "/podbrclosed.png";
            ButtonRandom.setText(R.string.Guess);
            chit = 0;


        }
    }

    public int GenerateRandom() {
        Random random = new Random();
        int randomNumber = random.nextInt(4 - 0) + 0;
        return randomNumber;
    }

    void whose_turn_it_is(int i) {
        switch (i) {
            case 0:
                toa.setText(tbb1.getText().toString().concat(getString(R.string.Whose_Turn)));
                break;
            case 1:
                toa.setText(tbb2.getText().toString().concat(getString(R.string.Whose_Turn)));
                break;
            case 2:
                toa.setText(tbb3.getText().toString().concat(getString(R.string.Whose_Turn)));
                break;
            case 3:
                toa.setText(tbb4.getText().toString().concat(getString(R.string.Whose_Turn)));
                break;
            case 4:
                toa.setText(R.string.Click_on_Guess);
                break;
        }
        toa.setVisibility(View.VISIBLE);
        toa.startAnimation(slideleft);
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            moveTaskToBack(true);
            System.exit(0);

        }

        this.doubleBackToExitPressedOnce = true;
        toa.setText(R.string.Press_Again_To_Exit);
        toa.setVisibility(View.VISIBLE);
        toa.startAnimation(slideleft);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}//class brace