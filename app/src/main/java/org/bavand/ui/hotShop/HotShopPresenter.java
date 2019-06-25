package org.bavand.ui.hotShop;

import org.bavand.BavandApplication;
import org.bavand.R;
import org.bavand.models.ShopModel;
import org.bavand.network.ConnectionDetector;
import org.bavand.network.NetworkClient;
import org.bavand.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HotShopPresenter implements HotShopPresenterInterface {
    public HotShopViewInterface hsvi;
    private ConnectionDetector cn = new ConnectionDetector();

    public HotShopPresenter(HotShopViewInterface hsvi) {
        this.hsvi = hsvi;
    }


    @Override
    public void getHotShop() {
        getObservableHotShop().subscribeWith(getObserverHotShop());
    }

    public Observable<ShopModel> getObservableHotShop() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getHotShop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ShopModel> getObserverHotShop() {
        return new DisposableObserver<ShopModel>() {
            @Override
            public void onNext(ShopModel shopModel) {
                hsvi.displayHotShop(shopModel);
            }

            @Override
            public void onError(Throwable e) {
                if (!cn.isConnectingToInternet()) {
                    hsvi.displayError(BavandApplication.getInstance().getString(R.string.network_onavilable), true);
                } else {
                    hsvi.displayError(BavandApplication.getInstance().getString(R.string.request_faild), true);
                }
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
