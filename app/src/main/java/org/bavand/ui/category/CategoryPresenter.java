package org.bavand.ui.category;

import org.bavand.models.CategoryModel;
import org.bavand.network.NetworkClient;
import org.bavand.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter implements CategoryPresenterInterface {
    CategoryViewInterface cvi;
    private String TAG = "HomePresenter";

    public CategoryPresenter(CategoryViewInterface cvi) {
        this.cvi = cvi;
    }

    @Override
    public void getCategory() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<CategoryModel> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<CategoryModel> getObserver() {
        return new DisposableObserver<CategoryModel>() {
            @Override
            public void onNext(CategoryModel categoryModel) {
                cvi.displayCategory(categoryModel);
            }

            @Override
            public void onError(Throwable e) {
                cvi.displayError("Error fetching ads Data");
            }

            @Override
            public void onComplete() {

            }
        };
    }

}
