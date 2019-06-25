package org.bavand.ui.hotShop;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.bavand.R;
import org.bavand.adaptors.HotShopAdaptor;
import org.bavand.models.ShopModel;
import org.bavand.ui.MainActivity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotShopFragment extends Fragment implements HotShopViewInterface {

    public View view;
    @BindView(R.id.viewPagerHotShop)
    ViewPager viewPagerHotShop;
    @BindView(R.id.imageButtonFullScreen)
    ImageButton imageButtonFullScreen;
    private HotShopPresenter hotShopPresenter;
    private PagerAdapter pagerAdapter;
    private boolean isFullScreen = true;

    public HotShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hot_shop, container, false);
        ButterKnife.bind(this, view);
        setupView();
        setupMVP();
        getHotShop();
        return view;
    }

    private void setupView() {
        imageButtonFullScreen.setOnClickListener((View v) -> {
            if (isFullScreen) {
                isFullScreen = false;
                MainActivity.navigationView.setVisibility(View.VISIBLE);
            } else {
                isFullScreen = true;
                MainActivity.navigationView.setVisibility(View.GONE);
            }
        });
    }

    private void setupMVP() {
        hotShopPresenter = new HotShopPresenter(this);
    }

    public void getHotShop() {
        hotShopPresenter.getHotShop();
    }

    @Override
    public void displayHotShop(ShopModel shopModel) {
        if (shopModel != null) {

            pagerAdapter = new HotShopAdaptor(shopModel.getShopResults(), getContext());
            viewPagerHotShop.setAdapter(pagerAdapter);
            viewPagerHotShop.setClipToPadding(false);
            viewPagerHotShop.setPageMargin(10);

        } else {
            Log.d(TAG, "Ads response null");
        }
    }

    @Override
    public void displayError(String s, boolean isShowing) {

    }
}
