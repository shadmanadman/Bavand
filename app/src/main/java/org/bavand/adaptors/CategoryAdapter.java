package org.bavand.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.bavand.R;
import org.bavand.models.CategoryModel;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryModel> categoryModels;
    Context context;

    public CategoryAdapter(List<CategoryModel> categoryModel, Context context) {
        this.categoryModels = categoryModel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(categoryModels.get(position).getCategoryImage()).dontAnimate().into(holder.imageViewCategory);
        holder.textViewCategoryTitle.setText(categoryModels.get(position).getCategoryName());
    }


    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardViewCategory)
        CardView cardViewCategory;
        @BindView(R.id.imageViewCategory)
        ImageView imageViewCategory;
        @BindView(R.id.textViewCategoryTitle)
        TextView textViewCategoryTitle;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
