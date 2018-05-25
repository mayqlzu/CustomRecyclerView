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
import android.widget.Toast;

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

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item, parent, false);

            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //Log.d("mayq", "onBindViewHolder(), position=" + position);

            holder.tv.setText(dataset[position]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(),
                            "clicked on item " + dataset[position],
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });

            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(),
                            "clicked on tv " + dataset[position],
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });

            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(holder.itemView.getContext(),
                            "clicked on btn " + dataset[position],
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return dataset.length;
        }
    }


}
