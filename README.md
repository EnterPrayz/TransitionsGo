# TransitionGo 
If you need transits muttiple views beetven activities on pre lolipop devices you can use TransitionGo.
![list](docs/list.gif)![activity](docs/activity.gif)

# Get start
Download [TransitionGo master](https://github.com/EnterPrayz/TransitionsGo/archive/master.zip) and add transitiongolib to your progect as module.
Add in first activity 
```java
 findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, SubActivity.class);
                ActivityTransitionLauncher.with(MainActivity.this)
                        .transit("test1", v)
                        .transit("test2", findViewById(R.id.imageView2))
                        .transit("test3", findViewById(R.id.tv_text_test1))
                        .launch(intent);
            }
        });
```
Receive intent in second activity.
```java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        exitTransition = ActivityTransition.with(getIntent())
                .transit("test1", findViewById(R.id.image))
                .transit("test2", findViewById(R.id.image2))
                .transit("test3", findViewById(R.id.tv_test_1))
                .start(savedInstanceState);
    }
```
You can add interpolator or/and animation duration.
```java
ActivityTransition.with(getIntent())
                ...
                .interpolator(new DecelerateInterpolator())
                .duration(500)
                .start(savedInstanceState);
```
If you want the exit animation, you can do like this in second activity.
```java
  @Override
    public void onBackPressed() {
        for (ExitActivityTransition exitActivityTransition : exitTransition) {
            exitActivityTransition.exit(this);
        }
    }
 ```
# Thanks
[takahirom](https://github.com/takahirom) for sample app.

## License
This project is released under the Apache License, Version 2.0.

* [The Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
