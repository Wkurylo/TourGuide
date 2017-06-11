package com.example.wojtekkurylo.tourguide;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button start = (Button) findViewById(R.id.button_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Clickable effect
                int[] attrs = new int[]{R.attr.selectableItemBackground};
                TypedArray typedArray = MainActivity.this.obtainStyledAttributes(attrs);
                int backgroundResource = typedArray.getResourceId(0, 0);
                start.setBackgroundResource(backgroundResource);

                // Fire up next activity
                Intent SwipeIntent = new Intent(MainActivity.this, SwipeActivity.class);
                startActivity(SwipeIntent);
            }
        });
    }
}
