package org.bavand.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.bavand.R;
import org.bavand.adaptors.ShopAdaptor;
import org.bavand.helper.Transitions;
import org.bavand.models.ShopModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity implements SearchViewInterface {

    @BindView(R.id.more_shop_list)
    RecyclerView recyclerView;
    @BindView(R.id.unicorn)
    RelativeLayout unicorn;
    @BindView(R.id.progress)
    RelativeLayout progress;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recheck_query)
    Button retry_query;
    @BindView(R.id.searchTitle)
    TextView textViewSearchTitle;
    private SearchPresenter searchPresenter;
    private RecyclerView.Adapter adapter;
    private String searchQuery, searchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Transitions.setupEnterExitWindowAnimations(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setupView();
        getSearchResult(searchQuery, "1");
        setupMVP();

    }

    private void setupView() {
        Intent intent = new Intent();
        searchQuery = intent.getStringExtra("SearchQuery");
        searchTitle = intent.getStringExtra("SearchTitle");
    }

    private void setupMVP() {
        searchPresenter = new SearchPresenter(this);
    }

    private void getSearchResult(String quary, String page) {
        searchPresenter.getSearch(quary, page);
    }

    @Override
    public void displaySearchResult(ShopModel shopModel) {
        if (shopModel != null) {
            adapter = new ShopAdaptor(shopModel.getShopResults(), this);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void displaySearchError(boolean isFinded) {

    }

    @Override
    public void displayError(String s, boolean isShowing) {

    }
}
