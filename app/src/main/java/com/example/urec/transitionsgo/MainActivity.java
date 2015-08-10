package com.example.urec.transitionsgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.onebit.enterprayz.transitiongolib.ActivityTransitionLauncher;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {
    String refer = "https://github.com/square/picasso/raw/master/website/static/sample.png";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Picasso.with(this).load(refer).into((ImageView) findViewById(R.id.imageView));
        Picasso.with(this).load(refer).into((ImageView) findViewById(R.id.imageView2));

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) v;
                final Intent intent = new Intent(MainActivity.this, SubActivity.class);
                ActivityTransitionLauncher.with(MainActivity.this)
                        .transit("test1", imageView)
                        .transit("test2", findViewById(R.id.imageView2))
                        .transit("test3", findViewById(R.id.tv_text_test1))
                        .launch(intent);
            }
        });
    }
}
