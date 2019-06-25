package org.bavand.ui.home;

import org.bavand.BavandApplication;
import org.bavand.R;
import org.bavand.models.AdsModel;
import org.bavand.models.ShopModel;
import org.bavand.network.ConnectionDetector;
import org.bavand.network.NetworkClient;
import org.bavand.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomePresenterInterface {
    HomeViewInterface hvi;
    private ConnectionDetector cn = new ConnectionDetector();

    public HomePresenter(HomeViewInterface hvi) {
        this.hvi = hvi;
    }

    @Override
    public void getAds() {
        getObservable().subscribeWith(getObserverAds());
    }

    @Override
    public void getNewShop() {
        getObservableNewShop().subscribeWith(getObserverShop());
    }

    @Override
    public void getSuggestionShop() {
        getObservableSuggestionShop().subscribeWith(getObserverShop());
    }


    public Observable<AdsModel> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getAds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ShopModel> getObservableNewShop() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getNewShop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ShopModel> getObservableSuggestionShop() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getSuggestionShop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public DisposableObserver<ShopModel> getObserverShop() {
        return new DisposableObserver<ShopModel>() {
            @Override
            public void onNext(ShopModel shopModel) {
                hvi.displayError("", false);
                hvi.displayNewShop(shopModel);
                hvi.displaySuggestionShop(shopModel);
                hvi.isAllDataLoad(true);
            }

            @Override
            public void onError(Throwable e) {
                if (!cn.isConnectingToInternet()) {
                    hvi.displayError(BavandApplication.getInstance().getString(R.string.network_onavilable), true);
                } else {
                    hvi.displayError(BavandApplication.getInstance().getString(R.string.request_faild), true);
                }
                hvi.isAllDataLoad(false);
            }

            @Override
            public void onComplete() {
                hvi.displayError("", false);

            }
        };

    }


    public DisposableObserver<AdsModel> getObserverAds() {
        return new DisposableObserver<AdsModel>() {
            @Override
            public void onNext(AdsModel adsModel) {
                hvi.displayError("", false);
                hvi.displayAds(adsModel);
                hvi.isAllDataLoad(true);
            }

            @Override
            public void onError(Throwable e) {
                if (!cn.isConnectingToInternet()) {
                    hvi.displayError(BavandApplication.getInstance().getString(R.string.network_onavilable), true);
                } else {
                    hvi.displayError(BavandApplication.getInstance().getString(R.string.request_faild), true);
                }
                hvi.isAllDataLoad(false);
            }

            @Override
            public void onComplete() {
                hvi.displayError("", false);

            }
        };
    }

}
