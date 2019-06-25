package org.bavand.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.bavand.R;
import org.bavand.models.AdsModel;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.ViewHolder> {
    List<AdsModel> adsModels;
    Context context;

    public AdsAdapter(List<AdsModel> adsModel, Context context) {
        this.adsModels = adsModel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ads_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(adsModels.get(position).getImageURL()).dontAnimate().into(holder.imageViewAds);
        holder.textViewAdsText.setText(adsModels.get(position).getShopComment());
        holder.textViewAdsTitle.setText(adsModels.get(position).getShopTitle());
    }


    @Override
    public int getItemCount() {
        return adsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardViewAds)
        CardView cardViewAds;
        @BindView(R.id.imageViewAdsImage)
        ImageView imageViewAds;
        @BindView(R.id.textViewAdsTitle)
        TextView textViewAdsTitle;
        @BindView(R.id.textViewAdsText)
        TextView textViewAdsText;
        @BindView(R.id.fabAds)
        FloatingActionButton floatingActionButtonAds;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
