package org.bavand.ui.category;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.bavand.R;
import org.bavand.adaptors.CategoryAdapter;
import org.bavand.adaptors.HotCategoryAdapter;
import org.bavand.custom.GridAutoFitLayoutManager;
import org.bavand.models.CategoryModel;
import org.bavand.ui.MainActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryViewInterface {

    public View view;
    @BindView(R.id.categoryList)
    RecyclerView recyclerViewCategory;
    @BindView(R.id.hotCategoryList)
    RecyclerView recyclerViewHotCategoryList;
    private RecyclerView.Adapter adapter;
    private CategoryPresenter categoryPresenter;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        setupMVP();
        setupViews();
        getHotCategorys();
        getCategoryList();
        return view;
    }

    private void setupViews() {
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCategory.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    MainActivity.navigationView.animate().translationY(MainActivity.navigationView.getHeight());
                } else if (dy < 0) {
                    MainActivity.navigationView.animate().translationY(0);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setupMVP() {
        categoryPresenter = new CategoryPresenter(this);
    }

    private void getCategoryList() {
        categoryPresenter.getCategory();
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void displayCategory(CategoryModel category) {
        if (category != null) {
            adapter = new CategoryAdapter(category.getResults(), getContext());
            recyclerViewCategory.setAdapter(adapter);
            recyclerViewCategory.setLayoutManager(new GridAutoFitLayoutManager(getContext(), 180));

        } else {
            Log.d(TAG, "Ads response null");
        }

    }

    @Override
    public void displayError(String s) {

    }

    private void getHotCategorys() {
        recyclerViewHotCategoryList.setAdapter(new HotCategoryAdapter(getActivity().getResources().getStringArray(R.array.topCategory), getContext()));
        recyclerViewHotCategoryList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

    }
}
