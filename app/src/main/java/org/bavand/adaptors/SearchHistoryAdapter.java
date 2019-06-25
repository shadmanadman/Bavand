package org.bavand.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.bavand.Bavand;
import org.bavand.R;
import org.bavand.helper.Prefs;
import org.bavand.ui.search.SearchFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {

    private ArrayList<String> mQueryList;
    private SearchFragment fragment;


    public SearchHistoryAdapter(SearchFragment fragment, ArrayList<String> mQueryList) {
        this.fragment = fragment;
        this.mQueryList = mQueryList;
    }

    public void add(int position, String mHistory) {
        mQueryList.add(mHistory);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mQueryList.remove(position);
        updatePrefList();
        notifyItemRemoved(position);
    }

    public void clear() {
        mQueryList.clear();
        clearPrefList();
        notifyDataSetChanged();
    }

    public void reload() {
        mQueryList = Prefs.getListString(fragment.getContext(), Bavand.HISTORY_LIST);
        notifyDataSetChanged();
    }

    private void updatePrefList() {
        Prefs.putListString(fragment.getContext(), Bavand.HISTORY_LIST, mQueryList);
    }

    private void clearPrefList() {
        Prefs.putListString(fragment.getContext(), Bavand.HISTORY_LIST, new ArrayList<>());
    }

    @NonNull
    @Override
    public SearchHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryAdapter.ViewHolder holder, final int position) {
        String mDatedQuery[] = mQueryList.get(position).split(":");
        holder.query.setText(mDatedQuery[0]);
        holder.date.setText(getDiffString((int) getDiff(mDatedQuery[1])));
        holder.viewForeground.setOnClickListener(v -> {
            final String query = holder.query.getText().toString();

        });
    }

    @Override
    public int getItemCount() {
        return mQueryList.size();
    }

    private long getDiff(String queryDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date curDate = simpleDateFormat.parse(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()));
            return TimeUnit.DAYS.convert(curDate.getTime() - simpleDateFormat.parse(queryDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private String getDiffString(int diff) {
        if (diff == 0)
            return "امروز";
        if (diff == 1)
            return "دیروز";
        else if (diff > 1)
            return diff + " روز قبل ";
        return "";
    }

    private String getTitleString(String query) {
        return query.startsWith(Bavand.PUB_PREFIX)
                ? fragment.getString(R.string.app_name, query.substring(Bavand.PUB_PREFIX.length()))
                : fragment.getString(R.string.fragment_title_search, query)
                ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout viewForeground;
        RelativeLayout viewBackground;
        TextView query;
        TextView date;

        ViewHolder(View view) {
            super(view);
            query = view.findViewById(R.id.query);
            date = view.findViewById(R.id.queryTime);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }
}