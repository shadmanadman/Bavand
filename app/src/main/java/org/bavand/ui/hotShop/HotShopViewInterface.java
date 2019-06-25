package org.bavand.ui.hotShop;

import org.bavand.models.ShopModel;

public interface HotShopViewInterface {
    void displayHotShop(ShopModel shopModel);

    void displayError(String s, boolean isShowing);
}
