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

        // 최상위 레이아웃
        final FrameLayout container = findViewById(R.id.container);

        // 최상위 레이아웃에 변경사항 체크
        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 입력필드들이 있는 레이아웃 가져오기
                View view = findViewById(R.id.keyboard);
                int keyboard_height;
                Rect outGlobalRect = new Rect();
                Rect outWindowRect = new Rect();
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                // 실제 레이아웃 사이즈 가져오기
                container.getGlobalVisibleRect(outGlobalRect);
                // 보여지는 (키보드가 가린부분 제외) 레이아웃 사이즈 가져오기
                container.getWindowVisibleDisplayFrame(outWindowRect);
                // 실제 레이아웃에서 보여지는 부분(키보드 크기)만 빼면 키보드의 높이가 나온다
                keyboard_height = outGlobalRect.bottom - outWindowRect.bottom;
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
                // 키보드 높이만큼 아래쪽에 마진값을 줘서 레이아웃을 밀어 올린다.
                layoutParams.setMargins(0, 0, 0, keyboard_height);
                view.setLayoutParams(layoutParams);
            }
        });
    }
}
