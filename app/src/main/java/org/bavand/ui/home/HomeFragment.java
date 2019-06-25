package org.bavand.ui.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.bavand.R;
import org.bavand.adaptors.AdsAdapter;
import org.bavand.adaptors.HotCategoryAdapter;
import org.bavand.adaptors.ShopAdaptor;
import org.bavand.models.AdsModel;
import org.bavand.models.ShopModel;
import org.bavand.ui.LinePagerIndicatorDecoration;
import org.bavand.ui.MainActivity;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeViewInterface {
    public View view;
    @BindView(R.id.adsList)
    RecyclerView recyclerViewAds;
    @BindView(R.id.newShopList)
    RecyclerView recyclerViewNewShopList;
    @BindView(R.id.suggestionShopList)
    RecyclerView recyclerViewSuggestionShopList;
    @BindView(R.id.hotCategoryList)
    RecyclerView recyclerViewHotCategoryList;
    @BindView(R.id.linearLayoutHomeView)
    LinearLayout linearLayoutHomeView;
    @BindView(R.id.progress)
    ContentLoadingProgressBar progress;
    @BindView(R.id.scrollViewHome)
    NestedScrollView nestedScrollViewHome;
    private HomePresenter homePresenter;
    private RecyclerView.Adapter adapter;
    private SnapHelper snapHelper;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        setupMVP();
        setupViews();
        getAdsList();
        getNewSopList();
        getSuggestionShopList();
        displayHotCategory();
        return view;
    }

    private void setupMVP() {
        homePresenter = new HomePresenter(this);
    }

    private void setupViews() {
        recyclerViewAds.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNewShopList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSuggestionShopList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHotCategoryList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewAds);

    }

    private void getSuggestionShopList() {
        homePresenter.getSuggestionShop();
    }

    private void getNewSopList() {
        homePresenter.getNewShop();
    }

    private void getAdsList() {
        homePresenter.getAds();
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void displayAds(AdsModel ads) {
        if (ads != null) {

            adapter = new AdsAdapter(ads.getAdsResults(), getContext());
            recyclerViewAds.setAdapter(adapter);
            recyclerViewAds.addItemDecoration(new LinePagerIndicatorDecoration());

        } else {
            Log.d(TAG, "Ads response null");
        }
    }

    @Override
    public void displayNewShop(ShopModel newShop) {
        if (newShop != null) {

            adapter = new ShopAdaptor(newShop.getShopResults(), getContext());
            recyclerViewNewShopList.setAdapter(adapter);

        } else {
            Log.d(TAG, "Ads response null");
        }
    }

    @Override
    public void displaySuggestionShop(ShopModel suggestionShop) {
        if (suggestionShop != null) {

            adapter = new ShopAdaptor(suggestionShop.getShopResults(), getContext());
            recyclerViewSuggestionShopList.setAdapter(adapter);

        } else {
            Log.d(TAG, "Ads response null");
        }
    }


    private void displayHotCategory() {
        adapter = new HotCategoryAdapter(getActivity().getResources().getStringArray(R.array.topCategory), getContext());
        recyclerViewHotCategoryList.setAdapter(adapter);
    }

    @Override
    public void displayError(String s, boolean isShowing) {
        if (isShowing) {
            MainActivity.showErrorLayout(getContext(), s);
        } else {
            MainActivity.frameLayoutErrorContainer.removeAllViews();
        }
    }

    @Override
    public void isAllDataLoad(boolean isAllDataLoad) {
        if (isAllDataLoad) {
            linearLayoutHomeView.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
        } else {
            linearLayoutHomeView.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
        }
    }

}
