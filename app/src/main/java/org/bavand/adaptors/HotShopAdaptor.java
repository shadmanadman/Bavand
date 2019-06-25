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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.PagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HotShopAdaptor extends PagerAdapter {
    List<ShopModel> shopModels;
    Context context;

    @BindView(R.id.cardViewShop)
    CardView cardViewShop;
    @BindView(R.id.imageViewShop)
    ImageView imageViewHotShop;
    @BindView(R.id.textViewShopTitle)
    TextView textViewHotShopTitle;
    @BindView(R.id.textViewShopCity)
    TextView textViewHotShopCity;
    @BindView(R.id.textViewShopComment)
    TextView textViewHotShopComment;
    @BindView(R.id.shopData)
    RelativeLayout relativeLayoutShopData;
    @BindView(R.id.shop_menu3dot)
    ImageView imageViewshopMenu3Dots;

    public HotShopAdaptor(List<ShopModel> shopModel, Context context) {
        this.shopModels = shopModel;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext())
                .inflate(R.layout.hot_shop_list, container, false);

        ButterKnife.bind(this, v);

        if (shopModels.get(position) != null)
            drawBackground(shopModels.get(position));
        Glide.with(context).load(shopModels.get(position).getImageURL1()).dontAnimate().into(imageViewHotShop);
        textViewHotShopTitle.setText(shopModels.get(position).getTitle());
        textViewHotShopCity.setText(shopModels.get(position).getCityID());
        textViewHotShopComment.setText(shopModels.get(position).getComment());
        container.addView(v);

        return v;
    }


    @Override
    public int getCount() {
        return shopModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    private void drawBackground(ShopModel shopModel) {
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
                        getPalette(resource);
                        return false;
                    }
                })
                .submit();
    }

    private void getPalette(Bitmap bitmap) {
        Palette.from(bitmap)
                .generate(palette -> paintEmAll(palette));
    }

    private void paintEmAll(Palette palette) {
        Palette.Swatch swatch = palette.getDominantSwatch();
        if (swatch != null) {
            relativeLayoutShopData.setBackgroundColor(swatch.getRgb());
            textViewHotShopTitle.setTextColor(swatch.getTitleTextColor());
            textViewHotShopComment.setTextColor(swatch.getBodyTextColor());
            textViewHotShopCity.setTextColor(swatch.getBodyTextColor());
            imageViewshopMenu3Dots.setColorFilter(swatch.getBodyTextColor());
        }
    }
}
