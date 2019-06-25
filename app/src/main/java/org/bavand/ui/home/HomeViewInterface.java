package org.bavand.ui.home;

import org.bavand.models.AdsModel;
import org.bavand.models.ShopModel;

public interface HomeViewInterface {

    void showToast(String s);

    void displayAds(AdsModel ads);

    void displayNewShop(ShopModel newShop);

    void displaySuggestionShop(ShopModel suggestionShop);

    void displayError(String s, boolean isShowing);

    void isAllDataLoad(boolean isAllDataLoad);
}
