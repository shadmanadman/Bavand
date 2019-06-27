package org.bavand.network;

import org.bavand.models.AdsModel;
import org.bavand.models.CategoryModel;
import org.bavand.models.ShopModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {
    @GET("get_ads.php")
    Observable<AdsModel> getAds();

    @GET("get_category.php")
    Observable<CategoryModel> getCategory();

    @GET("get_new_shop.php")
    Observable<ShopModel> getNewShop();

    @GET("get_suggestion_shop.php")
    Observable<ShopModel> getSuggestionShop();

    @GET("get_hot_shop.php")
    Observable<ShopModel> getHotShop();

    @GET("search.php")
    Observable<ShopModel> getSearch(@Query("search") String search, @Query("page") String page);

    @GET("get_shop_by_category.php")
    Observable<ShopModel> getShopByCategory(@Query("id") String id);
}
