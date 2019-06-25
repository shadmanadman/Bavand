package org.bavand.adaptors;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import org.bavand.R;
import org.bavand.models.ShopModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopAdaptor extends RecyclerView.Adapter<ShopAdaptor.ViewHolder> {
    List<ShopModel> shopModels;
    Context context;

    public ShopAdaptor(List<ShopModel> shopModel, Context context) {
        this.shopModels = shopModel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (shopModels.get(position).getImageURL1() != null)
            drawBackground(shopModels.get(position), holder);
        Glide.with(context).load(shopModels.get(position).getImageURL1()).dontAnimate().into(holder.imageViewShop);
        holder.textViewShopTitle.setText(shopModels.get(position).getTitle());
        holder.textViewShopCity.setText(shopModels.get(position).getCityID());
        //holder.textViewShopCategory.setText(shopModels.get(position).getCategoryId());

    }


    @Override
    public int getItemCount() {
        return shopModels.size();
    }

    private void drawBackground(ShopModel shopModel, ViewHolder holder) {
        Glide.with(context)
                .asBitmap()
                .load(shopModel.getImageURL1())
                .apply(new RequestOptions()
                        .override(100, 100)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.color.transparent))
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        getPalette(resource, holder);
                        return false;
                    }
                })
                .submit();
    }

    private void getPalette(Bitmap bitmap, ViewHolder holder) {
        Palette.from(bitmap)
                .generate(palette -> paintEmAll(palette, holder));
    }

    private void paintEmAll(Palette palette, ViewHolder holder) {
        Palette.Swatch swatch = palette.getDominantSwatch();
        if (swatch != null) {
            holder.relativeLayoutShopData.setBackgroundColor(swatch.getRgb());
            holder.textViewShopTitle.setTextColor(swatch.getTitleTextColor());
            holder.textViewShopCity.setTextColor(swatch.getBodyTextColor());
            holder.imageViewshopMenu3Dots.setColorFilter(swatch.getBodyTextColor());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardViewShop)
        CardView cardViewShop;
        @BindView(R.id.imageViewShop)
        ImageView imageViewShop;
        @BindView(R.id.textViewShopTitle)
        TextView textViewShopTitle;
        @BindView(R.id.textViewShopCity)
        TextView textViewShopCity;
        @BindView(R.id.shopData)
        RelativeLayout relativeLayoutShopData;
        @BindView(R.id.shop_menu3dot)
        ImageView imageViewshopMenu3Dots;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
