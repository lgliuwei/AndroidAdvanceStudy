package cn.codingblock.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.codingblock.libutils.view.ViewUtils;
import cn.codingblock.view.conflict.ScrollListActivity;
import cn.codingblock.view.event_dispatch.EventDispatchActivity;
import cn.codingblock.view.activity.MyViewActivity;
import cn.codingblock.view.activity.PaintStudyActivity;
import cn.codingblock.view.activity.StudyActivity;
import cn.codingblock.view.game.DebrisShapeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    Button btn_myview;
    Button btn_dispatch;
    Button btn_canvas_study;
    Button btn_paint_study;
    Button btn_debris_shape;
    Button btn_start_scroll_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            btn_myview = ViewUtils.find(this, R.id.btn_myview);
            btn_dispatch = ViewUtils.find(this, R.id.btn_dispatch);
            btn_canvas_study = ViewUtils.find(this, R.id.btn_canvas_study);
            btn_paint_study = ViewUtils.find(this, R.id.btn_paint_study);
            btn_debris_shape = ViewUtils.find(this, R.id.btn_debris_shape);
            btn_start_scroll_list = ViewUtils.find(this, R.id.btn_start_scroll_list);
            btn_myview.setOnClickListener(this);
            btn_dispatch.setOnClickListener(this);
            btn_canvas_study.setOnClickListener(this);
            btn_paint_study.setOnClickListener(this);
            btn_debris_shape.setOnClickListener(this);
            btn_start_scroll_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_myview:
                startActivity(new Intent(this, MyViewActivity.class));
                break;
            case R.id.btn_dispatch:
                startActivity(new Intent(this, EventDispatchActivity.class));
                break;
            case R.id.btn_canvas_study:
                startActivity(new Intent(this, StudyActivity.class));
                break;
            case R.id.btn_paint_study:
                startActivity(new Intent(this, PaintStudyActivity.class));
                break;
            case R.id.btn_debris_shape:
                startActivity(new Intent(this, DebrisShapeActivity.class));
                break;
            case R.id.btn_start_scroll_list:
                startActivity(new Intent(this, ScrollListActivity.class));
                break;
        }
    }
}
