package com.example.urec.transitionsgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onebit.enterprayz.transitiongolib.activity.ActivityTransitionLauncher;
import com.onebit.enterprayz.transitiongolib.view.ExitViewTransitAnimation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by urec on 10.08.15.
 */
public class ListActivity extends AppCompatActivity {
    public static int ACTIVITY_CLOSED_REQ = 101;
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
    private ListAdapter adapter;
    private ListView listView;
    private ArrayList<ExitViewTransitAnimation> exitViewTransitAnimations;

    @Override
    public void onBackPressed() {
        if (exitViewTransitAnimations != null) {
            for (ExitViewTransitAnimation exitView : exitViewTransitAnimations) {
                exitView.exit();
            }
            exitViewTransitAnimations = null;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.list);
        adapter = new ListAdapter(getTestList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startViewsColapseAnimation(position, view);
            }
        });
    }

    private void startViewsColapseAnimation(int position, final View choicedView) {
        int firstVisibleRow = listView.getFirstVisiblePosition();
        int lastVisibleRow = listView.getLastVisiblePosition();
        int index = 1;
        for (int i = firstVisibleRow; i <= lastVisibleRow; i++) {
            if (i != position) {
                View fromView = getViewByPosition(i, listView);
                View toView = position > i ? findViewById(R.id.view_above) : findViewById(R.id.view_below);
                int toY = position < i ? toView.getBottom() : -fromView.getHeight() * index;
                Animation animation = new TranslateAnimation(0, 0, 0, toY);
                animation.setDuration(350);
                fromView.startAnimation(animation);
                index++;
            }
        }
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNewActivity(choicedView);
            }
        }, 300);


    }

    private void startNewActivity(View view) {
        final Intent intent = new Intent(ListActivity.this, SubListActivity.class);
        ActivityTransitionLauncher.with(ListActivity.this)
                .transit("large_image", view.findViewById(R.id.iv_large_image))
                .transit("small_image", view.findViewById(R.id.iv_small_image))
                .transit("title", view.findViewById(R.id.tv_title))
                .transit("sub_title", view.findViewById(R.id.tv_sub_title))
                .launch(intent, ACTIVITY_CLOSED_REQ);
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CLOSED_REQ) {

        }
    }
}
