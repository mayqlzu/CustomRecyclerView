package android.mayqlzu.com.customrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MultipleViewTypeActivity extends Activity {
    private RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_recyclerview);
        rv = findViewById(R.id.rv);

        // adapter changes cannot affect the size of the RecyclerView. improve performance.
        rv.setHasFixedSize(true);

        // set LayoutManager
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        // set Adapter
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("item" + i);
        }
        String[] arr = list.toArray(new String[0]);
        rv.setAdapter(new MyAdapter(arr));
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private static final int TYPE_TEXT = 1;
        private static final int TYPE_BTN = 2;

        private String[] dataset;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;
            public Button btn;

            public ViewHolder(View v) {
                super(v);
                this.tv = v.findViewById(R.id.tv);
                this.btn = v.findViewById(R.id.btn);
            }
        }

        public MyAdapter(String[] dataset) {
            this.dataset = dataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Log.d("mayq", "onCreateViewHolder(), viewType=" + viewType);

            int layout = viewType == TYPE_TEXT ? R.layout.layout_viewtype_text
                    : R.layout.layout_viewtype_btn;

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(layout, parent, false);

            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //Log.d("mayq", "onBindViewHolder(), position=" + position);

            if (getItemViewType(position) == TYPE_TEXT) {
                holder.tv.setText(dataset[position]);
            } else {
                holder.btn.setText(dataset[position]);
            }
        }

        @Override
        public int getItemCount() {
            return dataset.length;
        }

        @Override
        public int getItemViewType(int position) {
            //return super.getItemViewType(position);
            return position % 2 == 0 ? TYPE_TEXT : TYPE_BTN;
        }
    }


}
