package cn.codingblock.optimize.animator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import cn.codingblock.libutils.view.ViewUtils;
import cn.codingblock.optimize.R;

public class AnimatorTestActivity extends AppCompatActivity {

    private static final String TAG = AnimatorTestActivity.class.getSimpleName();

    private Button btn_test;
    private ObjectAnimator animator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_test);
        btn_test = ViewUtils.find(this, R.id.btn_test);

        animator = ObjectAnimator.ofFloat(btn_test, "rotation", 0, 360).setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        animator.cancel();
    }
}
