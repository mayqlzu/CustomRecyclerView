package android.mayqlzu.com.customrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BasicRecyclerViewActivity extends Activity {
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
        private String[] dataset;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;

            public ViewHolder(TextView tv) {
                super(tv);
                this.tv = tv;
            }
        }

        public MyAdapter(String[] dataset) {
            this.dataset = dataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item_text, parent, false);

            ViewHolder vh = new ViewHolder(tv);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(dataset[position]);
        }

        @Override
        public int getItemCount() {
            return dataset.length;
        }
    }


}
