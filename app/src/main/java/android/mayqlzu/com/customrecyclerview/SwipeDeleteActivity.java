package android.mayqlzu.com.customrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * reference: https://medium.com/@kitek/recyclerview-swipe-to-delete-easier-than-you-thought-cff67ff5e5f6
 */
public class SwipeDeleteActivity extends Activity {
    private RecyclerView rv;
    private MyAdapter adapter;

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
        adapter = new MyAdapter(list);
        rv.setAdapter(adapter);

        // create swipe callback
        SwipeToDeleteCallback callback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.removeAt(viewHolder.getAdapterPosition());
            }
        };

        // create ItemTouchHelper with callback
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

        // bind ItemTouchHelper and RecyclerView
        itemTouchHelper.attachToRecyclerView(rv);
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> dataset;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;

            public ViewHolder(TextView tv) {
                super(tv);
                this.tv = tv;
            }
        }

        public MyAdapter(List<String> dataset) {
            this.dataset = dataset;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Log.d("mayq", "onCreateViewHolder(), viewType=" + viewType);

            TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_text, parent, false);

            ViewHolder vh = new ViewHolder(tv);

            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //Log.d("mayq", "onBindViewHolder(), position=" + position);

            holder.tv.setText(dataset.get(position));
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }

        public void removeAt(int position) {
            dataset.remove(position);
            notifyDataSetChanged();
        }
    }


}
