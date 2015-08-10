package com.example.urec.transitionsgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;

import com.onebit.enterprayz.transitiongolib.activity.ActivityTransition;
import com.onebit.enterprayz.transitiongolib.activity.ExitActivityTransition;

import java.util.ArrayList;


public class SubListActivity extends AppCompatActivity {

    private ArrayList<ExitActivityTransition> exitTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_list);
        exitTransition = ActivityTransition.with(getIntent())
                .transit("large_image", findViewById(R.id.iv_large_image))
                .transit("small_image", findViewById(R.id.iv_small_image))
                .transit("title", findViewById(R.id.tv_title))
                .transit("sub_title", findViewById(R.id.tv_sub_title))
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
