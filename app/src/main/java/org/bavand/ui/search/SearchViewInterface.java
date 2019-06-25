package org.bavand.ui.search;

import org.bavand.models.ShopModel;

public interface SearchViewInterface {
    void displaySearchResult(ShopModel shopModel);

    void displaySearchError(boolean isFinded);

    void displayError(String s, boolean isShowing);
}
