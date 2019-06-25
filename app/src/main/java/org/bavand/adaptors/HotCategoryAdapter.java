package org.bavand.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.bavand.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HotCategoryAdapter extends RecyclerView.Adapter<HotCategoryAdapter.ViewHolder> {
    Context context;
    private String[] hotCategory;

    public HotCategoryAdapter(String[] hotCategory, Context context) {
        this.hotCategory = hotCategory;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hot_category_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewHotCategoryTitle.setText(hotCategory[position]);
    }


    @Override
    public int getItemCount() {
        return hotCategory.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardViewHotCategory)
        CardView cardViewCategory;
        @BindView(R.id.textViewHotCategoryTitle)
        TextView textViewHotCategoryTitle;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
