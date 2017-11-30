package net.flow9.backgroundgifani;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivImg = findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.drawable.giphy)
                .into(ivImg);

        final FrameLayout container = findViewById(R.id.container);

        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View view = findViewById(R.id.keyboard);
                int marginBottom;
                Rect outGlobalRect = new Rect();
                Rect outWindowRect = new Rect();
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                container.getGlobalVisibleRect(outGlobalRect);
                container.getWindowVisibleDisplayFrame(outWindowRect);
                marginBottom = outGlobalRect.bottom - outWindowRect.bottom;
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
                layoutParams.setMargins(0, 0, 0, marginBottom);
                view.setLayoutParams(layoutParams);
            }
        });
    }
}
