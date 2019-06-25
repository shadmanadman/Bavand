package org.bavand.ui.search;


import android.app.ActivityOptions;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.bavand.Bavand;
import org.bavand.R;
import org.bavand.adaptors.SearchHistoryAdapter;
import org.bavand.helper.HistoryItemTouchHelper;
import org.bavand.helper.Prefs;
import org.bavand.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, HistoryItemTouchHelper.RecyclerItemTouchHelperListener {
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.searchHistory)
    RecyclerView recyclerViewSearchHistory;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.clearAll)
    Button clearAll;
    private View view;
    private SearchHistoryAdapter searchHistoryAdapter;
    private ArrayList<String> mQueryList;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        addQueryTextListener(searchView);
        setupHistory();
        setupView();
        return view;
    }

    private void setupView() {
        recyclerViewSearchHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearchHistory.setOnScrollListener(new RecyclerView.OnScrollListener() {
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

        clearAll.setOnClickListener(v -> clearAll());
    }


    private void addQueryTextListener(SearchView searchView) {
        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        if (null != searchManager) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setQuery("", false);
                searchView.setIconified(true);
                searchView.clearFocus();
                setQuery(query);
                return true;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = searchView.getSuggestionsAdapter().getCursor();
                cursor.moveToPosition(position);
                String suggestion = cursor.getString(2);
                searchView.setQuery(suggestion, true);
                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    private void setQuery(String query) {
        String mDatedQuerry = query.concat(":").concat(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()));
        setQueryToPref(mDatedQuerry);
        getQueriedShops(query);

    }

    private void setQueryToPref(String query) {
        mQueryList = Prefs.getListString(getContext(), Bavand.HISTORY_LIST);
        mQueryList.add(0, query);
        Prefs.putListString(getContext(), Bavand.HISTORY_LIST, mQueryList);
        if (searchHistoryAdapter != null)
            searchHistoryAdapter.reload();
        else
            setupSearchHistory(mQueryList);
    }

    private void setupSearchHistory(ArrayList<String> mHistoryList) {
        searchHistoryAdapter = new SearchHistoryAdapter(this, mHistoryList);
        recyclerViewSearchHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSearchHistory.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecorator = new DividerItemDecoration(recyclerViewSearchHistory.getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.list_divider));
        recyclerViewSearchHistory.addItemDecoration(itemDecorator);
        recyclerViewSearchHistory.setAdapter(searchHistoryAdapter);
        new ItemTouchHelper(
                new HistoryItemTouchHelper(0, ItemTouchHelper.RIGHT, this))
                .attachToRecyclerView(recyclerViewSearchHistory);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);

        if (viewHolder instanceof SearchHistoryAdapter.ViewHolder) {
            searchHistoryAdapter.remove(position);
            if (searchHistoryAdapter.getItemCount() < 1)
                clearAll();
        }
    }

    private void clearAll() {
        if (searchHistoryAdapter != null)
            searchHistoryAdapter.clear();
        toggleViews(true);
    }

    private void toggleViews(Boolean shouldHide) {
        if (shouldHide) {
            recyclerViewSearchHistory.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerViewSearchHistory.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private void setupHistory() {
        mQueryList = Prefs.getListString(getContext(), Bavand.HISTORY_LIST);
        if (!mQueryList.isEmpty()) {
            setupSearchHistory(mQueryList);
            toggleViews(false);
        } else {
            toggleViews(true);
        }
    }

    private void getQueriedShops(String query) {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("SearchQuery", query);
        intent.putExtra("SearchTitle", getTitleString(query));
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
        startActivity(intent, bundle);
    }

    private String getTitleString(String query) {
        return query.startsWith(Bavand.PUB_PREFIX)
                ? getString(R.string.app_name, query.substring(Bavand.PUB_PREFIX.length()))
                : getString(R.string.search_for, query)
                ;
    }
}
