package org.bavand.ui.search;

import org.bavand.BavandApplication;
import org.bavand.R;
import org.bavand.models.ShopModel;
import org.bavand.network.ConnectionDetector;
import org.bavand.network.NetworkClient;
import org.bavand.network.NetworkInterface;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchPresenterInterface {
    public SearchViewInterface svi;
    public ConnectionDetector cn = new ConnectionDetector();
    public String textSearch;

    public SearchPresenter(SearchViewInterface svi) {
        this.svi = svi;
    }

    @Override
    public void getSearch(String searchQuery, String page) {
        try {
            textSearch = URLEncoder.encode(searchQuery, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getObservableSearchResult(textSearch.replaceAll(" ", "%20"), page).subscribeWith(getObserverSearchResult());
    }

    private Observable<ShopModel> getObservableSearchResult(String searchQuery, String page) {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getSearch(searchQuery, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<ShopModel> getObserverSearchResult() {
        return new DisposableObserver<ShopModel>() {
            @Override
            public void onNext(ShopModel shopModel) {
                svi.displaySearchResult(shopModel);
            }

            @Override
            public void onError(Throwable e) {
                if (!cn.isConnectingToInternet()) {
                    svi.displayError(BavandApplication.getInstance().getString(R.string.network_onavilable), true);
                } else {
                    svi.displayError(BavandApplication.getInstance().getString(R.string.request_faild), true);
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
