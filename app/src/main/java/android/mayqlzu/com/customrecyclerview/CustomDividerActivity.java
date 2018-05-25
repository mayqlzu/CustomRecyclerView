package android.mayqlzu.com.customrecyclerview;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * reference: https://www.bignerdranch.com/blog/a-view-divided-adding-dividers-to-your-recyclerview-with-itemdecoration/
 */
public class CustomDividerActivity extends Activity {
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

        // set divider
        Drawable drawable = new ColorDrawable(
                getResources().getColor(R.color.colorAccent));
        rv.addItemDecoration(new DividerItemDecoration(drawable));

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
            //Log.d("mayq", "onCreateViewHolder(), viewType=" + viewType);

            TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_text, parent, false);

            ViewHolder vh = new ViewHolder(tv);

            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            //Log.d("mayq", "onBindViewHolder(), position=" + position);

            holder.tv.setText(dataset[position]);
        }

        @Override
        public int getItemCount() {
            return dataset.length;
        }
    }

    private static class DividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable drawable;
        private int HEIGHT = 10;

        public DividerItemDecoration(Drawable drawable) {
            this.drawable = drawable;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            // no divider above first item
            if (parent.getChildAdapterPosition(view) == 0) {
                return;
            }

            // for other items
            //outRect.top = drawable.getIntrinsicHeight(); // for images which has intrinsic height
            outRect.top = HEIGHT; // for color which has no intrinsic height
            //outRect.bottom = ...  //not needed?
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            //super.onDraw(c, parent, state); empty

            int dividerLeft = parent.getPaddingLeft();
            int dividerRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) { // why -1?
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)
                        child.getLayoutParams();

                int dividerTop = child.getBottom() + lp.bottomMargin;
                //int dividerBottom = dividerTop + drawable.getIntrinsicHeight();
                int dividerBottom = dividerTop + HEIGHT;

                drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                drawable.draw(c);
            }
        }
    }


}
