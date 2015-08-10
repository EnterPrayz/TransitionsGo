package com.example.urec.transitionsgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onebit.enterprayz.transitiongolib.activity.ActivityTransitionLauncher;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by urec on 10.08.15.
 */
public class ListActivity extends AppCompatActivity {


    String[] refers = new String[]{
            "http://mobilefon.org/_ph/28/1/179551610.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/179551610.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/297142442.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/231319873.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/156817439.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/474671733.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/109265158.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/463573011.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/80363080.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/730640591.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/169441899.jpg?1439231629",
            "http://mobilefon.org/_ph/28/1/462575782.jpg?1439231629"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ListAdapter(getTestList()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(ListActivity.this, SubListActivity.class);
                ActivityTransitionLauncher.with(ListActivity.this)
                        .transit("large_image", view.findViewById(R.id.iv_large_image))
                        .transit("small_image", view.findViewById(R.id.iv_small_image))
                        .transit("title", view.findViewById(R.id.tv_title))
                        .transit("sub_title", view.findViewById(R.id.tv_sub_title))
                        .launch(intent);
            }
        });
    }


    private ArrayList<ListItem> getTestList() {
        ArrayList<ListItem> test = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ListItem item = new ListItem(
                    getRandomImage(),
                    getRandomImage(),
                    "Title Hello word Android" + i,
                    "Sub title Hello word " + i
            );
            test.add(item);
        }

        return test;
    }

    private String getRandomImage() {
        Random rand = new Random();
        return refers[rand.nextInt(refers.length)];
    }

}
