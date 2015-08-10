package com.example.urec.transitionsgo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;


import com.onebit.enterprayz.transitiongolib.ActivityTransition;
import com.onebit.enterprayz.transitiongolib.ExitActivityTransition;

import java.util.ArrayList;


public class SubActivity extends AppCompatActivity {

    private ArrayList<ExitActivityTransition> exitTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        exitTransition = ActivityTransition.with(getIntent())
                .transit("test1", findViewById(R.id.image))
                .transit("test2", findViewById(R.id.image2))
                .transit("test3", findViewById(R.id.tv_test_1))
                .interpolator(new DecelerateInterpolator())
                .duration(500)
                .start(savedInstanceState);
    }


    @Override
    public void onBackPressed() {
        for (ExitActivityTransition exitActivityTransition : exitTransition) {
            exitActivityTransition.exit(this);
        }
    }
}
