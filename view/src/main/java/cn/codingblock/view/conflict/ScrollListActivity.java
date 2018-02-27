package cn.codingblock.view.conflict;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.codingblock.view.R;
import cn.codingblock.view.conflict.adapter.ScrollListAdapter;

public class ScrollListActivity extends AppCompatActivity {

    private List<String> mList;
    private ScrollListAdapter mAdapter;
    private ListViewFroScrollView lv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);

        lv_list = (ListViewFroScrollView) findViewById(R.id.lv_list);

        mList = new ArrayList<>();
        int n = 0;
        while (n++ < 20) {
            mList.add("第" + n + "条");
        }

        mAdapter = new ScrollListAdapter(getApplicationContext(), mList);
        lv_list.setAdapter(mAdapter);
    }
}
