package org.bavand.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.bavand.R;
import org.bavand.custom.TagView;
import org.bavand.models.ShopModel;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdaptor extends RecyclerView.Adapter<SearchAdaptor.ViewHolder> {
    List<ShopModel> shopModels;
    Context context;

    public SearchAdaptor(List<ShopModel> shopModel, Context context) {
        this.shopModels = shopModel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_shop_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(shopModels.get(position).getImageURL1()).dontAnimate().into(holder.imageViewShop);
        holder.textViewShopTitle.setText(shopModels.get(position).getTitle());
        holder.textViewShopCity.setText(shopModels.get(position).getCityID());
        holder.tagViewCategory.setMono_title(shopModels.get(position).getCategoryId());
    }


    @Override
    public int getItemCount() {
        return shopModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardViewSearchShopList)
        CardView cardViewSearchShopList;
        @BindView(R.id.imageViewShop)
        ImageView imageViewShop;
        @BindView(R.id.textViewShopTitle)
        TextView textViewShopTitle;
        @BindView(R.id.textViewShopCity)
        TextView textViewShopCity;
        @BindView(R.id.tagViewCategory)
        TagView tagViewCategory;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
